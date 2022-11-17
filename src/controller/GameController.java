package controller;

import utils.MessageController;

public class GameController extends Grid implements Runnable {
    private MessageController server;
    private int indexPlayer;

    public GameController(int limitGrid, MessageController server) throws Exception 
    { 
        super(20);
        Object gameData = server.getObject();
        if(gameData instanceof controller.Game)
        { 
            this.server = server;
            for (Player p : ((controller.Game)gameData).PLAYERS) this.players.add(p);
            for (Apple a : ((controller.Game)gameData).APPLES) this.apples.add(a);
            this.indexPlayer = indexOfPlayerByID(((controller.Game)gameData).ID);
            System.out.println(indexPlayer);
            this.addApple(new Apple("idghc", 1, 7, 10, 0, 1000));
            new Thread(this).start();
        } else throw new Exception("error in connection to server");
    }

    public GameController(Grid grid) throws Exception 
    { super(grid); }

    public void run() {
        int count = 0;
        while(true) {
            System.out.println("handle request: "+count++);
            Object data = server.getObject();
        
            if(data == null)
                return;
            if(data instanceof Player) {
                try { addPlayer((Player)data); }
                catch (Exception e) { }
            }
        
            else if(data instanceof Apple) {
                try { addApple((Apple)data); }
                catch (Exception e) { }
            }
        
            else if(data instanceof AlterPosition) {
                try{
                    if(((AlterPosition)data).TYPE == 'a') {
                        super.apples.get(indexOfAppleByID(((AlterPosition)data).ID)).setPosition(((AlterPosition)data).coords);
                    }
                    else if (((AlterPosition)data).TYPE == 'p') {
                        super.players.get(indexOfPlayerByID(((AlterPosition)data).ID)).setPosition(((AlterPosition)data).coords);
                    }
                }
                catch(Exception e) { }
            }
            
            else if(data instanceof Kick) {
                if(((Kick)data).ID != null) {
                    try {
                        removePlayerByID(((Kick)data).ID);
                    } catch (Exception e) { }
                }
            }
        }
    }

    public void handleCollision(int indexPlayer) { super.isAteAnApple(indexPlayer); }

    public boolean movingVerticalPlayer(int y) {
        Player p = super.players.get(indexPlayer);
        if(y > 0 && p.getY()+1 == super.limit) return false;
        if(y < 0 && p.getY()-1 == -1) return false;
        if(
            (y > 0 && !super.isPlayerInPosition(p.getX(), p.getY()+1)) ||
            y < 0 && !super.isPlayerInPosition(p.getX(), p.getY()-1)
        ) {
            super.players.get(indexPlayer).movingVertical(y);
            handleCollision(indexPlayer);
            return true;
        }

        return false;
    }
    public boolean movingHorizontalPlayer(int x) {
        Player p = super.players.get(indexPlayer);
        if(x > 0 && p.getX()+1 == super.limit) return false;
        if(x < 0 && p.getX()-1 == -1) return false;

        if(
            (x > 0 && !super.isPlayerInPosition(p.getX()+1, p.getY())) ||
            (x < 0 && !super.isPlayerInPosition(p.getX()-1, p.getY()))
        ) {
            super.players.get(indexPlayer).movingHorizontal(x);
            handleCollision(indexPlayer);
            return true;
        }

        return false;
    }
}
