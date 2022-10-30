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

    @Override
    public String toString() {
        return String.format(
            "value: %d, minValue: %d, timeout: %d, Coordinate: %s",
            this.value,
            this.minValue,
            this.timeout,
            super.toString()
        );
    }

    @Override
    public boolean equals (Object obj) {

        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;

        Apple a = (Apple)obj;
        if(this.handleValue != a.handleValue) return false;
        if(this.minValue != a.minValue) return false;
        if(this.timeout != a.timeout) return false;
        if(this.value != a.value) return false;


        return super.equals((Coordinate)obj);
    }

    @Override
    public int hashCode() {
        int hash = 2;
        hash = 3 * hash + super.hashCode();

        if(hash<0) hash =- hash;
        return hash;
    } 
}