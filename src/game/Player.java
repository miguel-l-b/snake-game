package game;

public class Player extends Coordinate {
    public final String username;
    public final String color;
    private int points;

    public Player(String username, String color, int x, int y) throws Exception {
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
}