package game;

public abstract class Coordinate {
    private int x = 0, y = 0;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected void setPosition(int x, int y) 
    { this.x = x; this.y = y; }

    public int getX() {return this.x; }
    public int getY() {return this.y; }
}