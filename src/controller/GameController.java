package controller;

import utils.MessageController;
import views.WindowsManager;

public class GameController extends Grid implements Runnable {
    private MessageController server;
    private String IDPlayer;
    private int indexPlayer;

    public GameController(int limitGrid, MessageController server) throws Exception 
    { 
        super(20);
        Object gameData = server.getObject();
        System.out.println(gameData);
        if(gameData instanceof Game)
        { 
            this.server = server;
            for (Player p : ((Game)gameData).PLAYERS) this.players.add(p);
            for (Apple a : ((Game)gameData).APPLES) this.apples.add(a);

            this.IDPlayer = ((controller.Game)gameData).ID;
            this.indexPlayer = indexOfPlayerByID(this.IDPlayer);
            new Thread(this).start();
        } else if(gameData instanceof Kick) 
        {
            if(((Kick)gameData).REASON == 2)
                WindowsManager.handleMessage("Servidor Cheio");
        }
        else throw new Exception("error in connection to server");
    }

    public GameController(Grid grid) throws Exception 
    { super(grid); }

    public void run() {
        while(true) {
            Communicate data = server.getObject();
        
            if(data == null) {
                WindowsManager.handleMessage("Erro ao receber dados do servidor");
                System.exit(0);
            }
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
                    if(((AlterPosition)data).TYPE == 'a')
                        super.apples.get(indexOfAppleByID(((AlterPosition)data).ID)).setPosition(((AlterPosition)data).coords);
                    
                    else if (((AlterPosition)data).TYPE == 'p')
                        super.players.get(indexOfPlayerByID(((AlterPosition)data).ID)).setPosition(((AlterPosition)data).coords);
                }
                catch(Exception e) { }
            }
            
            else if(data instanceof Kick) {
                if(((Kick)data).REASON == 4) {
                    try { 
                        server.close();
                        WindowsManager.handleMessage("Servidor Caiu");
                        System.exit(0);
                    }
                    catch (Exception e) { }
                }
                if(((Kick)data).ID != null || !((Kick)data).ID.equals(IDPlayer)) {
                    try {
                        removePlayerByID(((Kick)data).ID);
                        this.indexPlayer = indexOfPlayerByID(this.IDPlayer);
                    } catch (Exception e) { }
                } else {
                    WindowsManager.handleMessage("Você foi desconectado por:"+((Kick)data).getReason());
                    System.exit(0);
                }
            }
        }
    }

    public void handleCollision(int indexPlayer) { super.isAteAnApple(indexPlayer); }

    public boolean movingVerticalPlayer(int y) {
        server.sendObject(new AlterPosition(IDPlayer, 'p', 0, y));
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
        server.sendObject(new AlterPosition(IDPlayer, 'p', x, 0));
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

    public void close() {
        server.sendObject(new Kick(IDPlayer, 0));
    }
}
