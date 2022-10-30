package game;
import java.awt.Color;

public class Player extends Coordinate {
    public final String username;
    public final Color color;
    private int points;

    public Player(String username, Color color, int x, int y) throws Exception {
        super(x, y);
        if(username.length() > 10 || username.contains(" "))
            throw new Exception("invalid username");
        this.username = username;
        this.color = color;
        this.points = 0;
    }

    protected void setPoints(int points)
    { this.points = points; }
    protected void addPoint(int points)
    { this.points += points; }

    protected boolean movingHorizontal(int x) {
        if(x == 0) return false;

        if(x > 0) super.setPosition(super.getX()+1, super.getY());
        if(x < 0) super.setPosition(super.getX()-1, super.getY());

        return true;
    }
    protected boolean movingVertical(int y) {
        if(y == 0) return false;

        if(y > 0) super.setPosition(super.getX(), super.getY()+1);
        if(y < 0) super.setPosition(super.getX(), super.getY()-1);

        return true;
    }
    
    public int getPoints() {return this.points; }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(obj.getClass() != this.getClass()) return false;
        if(this.username != ((Player)obj).username) return false;
        if(this.points != ((Player)obj).points) return false;
        if(this.color != ((Player)obj).color) return false;

        return true;
    }
}