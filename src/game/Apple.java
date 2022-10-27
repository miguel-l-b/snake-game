package game;

public class Apple extends Coordinate {
    private int value;
    private final int minValue;
    private final long timeout;
    private Thread handleValue;

    public Apple(int x, int y, int value, int minValue, long timeout) {
        super(x, y);
        this.value = value;
        this.minValue = minValue;
        this.timeout = timeout;

        this.handleValue = new Thread(() -> { handleValue(); });

        this.handleValue.start();
    }

    private void handleValue() {
        try { this.handleValue.sleep(this.timeout); } catch(InterruptedException e) {}

        if(value == minValue) this.handleValue.interrupt();
        
        value--;
    }

    public int getValue() 
    { return this.value; }
}