package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {
    protected List<Player> players;
    protected List<Apple> apples;

    public final int limit;

    public Grid(int limit) throws Exception {
        if(limit < 10 || limit > 30)
            throw new Exception("the limit is too big");
        this.players = new ArrayList<Player>();
        this.apples = new ArrayList<Apple>();
        this.limit = limit;
    }

    public Grid(Grid grid) throws Exception {
        if(grid == null)
            throw new Exception("The grid is null");
        
        this.limit = grid.limit;
        this.players = grid.players;
        this.apples = grid.apples;
    }

    protected boolean isExistPlayerInPosition(int x, int y) {
        for(Coordinate player : players)
            if(x == player.getX() && y == player.getY()) return true;
        
        return false;
    }
    protected boolean isExistAppleInPosition(int x, int y) {
        for(Coordinate apple : apples)
            if(x == apple.getX() && y == apple.getY()) return true;
        
        return false;
    }
    protected Apple getAppleInPosition(int x, int y) {
        for(Apple apple : apples)
            if(x == apple.getX() && y == apple.getY()) return apple;

        return null;
    }

    public boolean isAteAnApple(int indexPlayer) {
        Player p = players.get(indexPlayer);
        Apple apple = getAppleInPosition(p.getX(), p.getY());
        if(apple != null) {
            players.get(indexPlayer).addPoint(apple.getValue());
            removeApple(apple);
            return true;
        }
        return false;
    }

    public void addPlayer(Player player) { this.players.add(player); }
    public void addApple(Apple apple) { this.apples.add(apple); }
    public void removePlayer(Player player) { this.players.remove(player); }
    public void removeApple(Apple apple) { this.apples.remove(apple); }

    @Override 
    public boolean equals (Object obj) {

        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
    
        Grid g = (Grid)obj;
        if(this.players != g.players) return false;
        if(this.apples != g.apples) return false;
    
        return true;
        }
    @Override   
    public int hashCode() {
        int hash = 2;
        for (Apple apple : apples) {
            hash = 7 * hash + apple.hashCode();
        }

        for (Player player : players) {
            hash = 17 * hash + player.hashCode();
        }
        if(hash<0) hash =- hash;
        return hash;
    }
    @Override
    public String toString() {
        return "player: " + players.toString() + " apples: "+ apples.toString();
    }
}