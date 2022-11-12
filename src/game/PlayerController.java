package game;

public class PlayerController extends Grid {

    public PlayerController(int limitGrid) throws Exception 
    { super(limitGrid); }
    public PlayerController(Grid grid) throws Exception 
    { super(grid); }

    public void handleCollision(int indexPlayer) { super.isAteAnApple(indexPlayer); }

    public boolean movingVertical(int y, int indexPlayer) {
        Player p = super.players.get(indexPlayer);
        if(y > 0 && p.getY()+1 == super.limit) return false;
        if(y < 0 && p.getY()-1 == -1) return false;

        if(
            (y > 0 && !super.isExistPlayerInPosition(p.getX(), p.getY()+1)) ||
            y < 0 && !super.isExistPlayerInPosition(p.getX(), p.getY()-1)
        ) {
            super.players.get(0).movingVertical(y);
            handleCollision(indexPlayer);
            return true;
        }

        return false;
    }
    public boolean movingHorizontal(int x, int indexPlayer) {
        Player p = super.players.get(indexPlayer);
        if(x > 0 && p.getX()+1 == super.limit) return false;
        if(x < 0 && p.getX()-1 == -1) return false;

        if(
            (x > 0 && !super.isExistPlayerInPosition(p.getX()+1, p.getY())) ||
            (x < 0 && !super.isExistPlayerInPosition(p.getX()-1, p.getY()))
        ) {
            super.players.get(0).movingHorizontal(x);
            handleCollision(indexPlayer);
            return true;
        }

        return false;
    }
}
