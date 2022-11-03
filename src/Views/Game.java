package Views;

import utils.Window;

public class Game extends Window {
    public Game() {
        super("Jogo da Cobra");
        GameCanvas gc;
        try {
            gc = new GameCanvas();
        } catch (Exception e) {
            gc = null;
            e.printStackTrace();
            System.err.println("Error in gc");
            System.exit(0);
        }
        super.add(gc);
        pack();
        setSize(gc.getSize());
        closeProgramOnClose();
        setResizable(false);
        centralize();
        setVisible(true);

        gc.start();
    }
}
