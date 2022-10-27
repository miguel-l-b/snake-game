package game;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<Player> players;
    private List<Apple> apples;

    public Grid() {
        this.players = new ArrayList<Player>();
        this.apples = new ArrayList<Apple>();
    }

    private boolean isExistPlayerInPosition(int x, int y) {
        for(Player player : players)
            if(x == player.getX() && y == player.getY()) return true;
        
        return false;
    }
    private Apple getAppleInPosition(int x, int y) {
        for(Apple apple : apples)
            if(x == apple.getX() && y == apple.getY()) return apple;

        return null;
    }

    public boolean isAteAnApple(int indexPlayer) {
        Player p = players.get(indexPlayer);
        Apple apple = getAppleInPosition(p.getX(), p.getY());
        if(apple != null)
        {
            players.get(indexPlayer).addPoint(apple.getValue());
            return true;
        }
        return false;
    }

    public void addPlayer(Player player) { this.players.add(player); }
    public void addApple(Apple apple) { this.apples.add(apple); }
    public void removePlayer(Player player) { this.players.remove(player); }
    public void removeAllApple() { this.apples.removeAll(apples); }

    public boolean movingVertical(int y, int index) {
        Player p = players.get(index);
        if(y > 0 && this.isExistPlayerInPosition(p.getX(), p.getY()+1))
        {
            players.get(0).setPosition(p.getX(), p.getY()+1);
            return true;
        }
        if(y < 0 && this.isExistPlayerInPosition(p.getX(), p.getY()-1))
        {
            players.get(0).setPosition(p.getX(), p.getY()-1);
            return true;
        }

        return false;
    }
    public boolean movingHorizontal(int x, int index) {
        Player p = players.get(index);
        if(x > 0 && this.isExistPlayerInPosition(p.getX()+1, p.getY()))
        {
            players.get(0).setPosition(p.getX()+1, p.getY());
            return true;
        }
        if(x < 0 && this.isExistPlayerInPosition(p.getX()-1, p.getY()))
        {
            players.get(0).setPosition(p.getX()-1, p.getY());
            return true;
        }

        return false;
    }
}