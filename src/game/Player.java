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
    
    public int getPoints() {return this.points; }
}