package views;

import utils.MessageController;
import utils.Window;

public class Game extends Window {
    public Game(MessageController socket) {
        super("Jogo da Cobra");
        GameCanvas gc;
        try {
            gc = new GameCanvas(socket);
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
