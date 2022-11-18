package views;

import utils.MessageController;
import utils.Window;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game extends Window {
    private GameCanvas gc;
    public Game(MessageController socket) {
        super("Jogo da Cobra");
        try {
            this.gc = new GameCanvas(socket);
        } catch (Exception e) {
            gc = null;
            e.printStackTrace();
            System.err.println("Error in gc");
            System.exit(0);
        }
        super.add(gc);
        pack();
        setSize(gc.getSize());
        this.addWindowListener((new WindowExit()));
        setResizable(false);
        centralize();
        setVisible(true);

        gc.start();
    }

    protected class WindowExit extends WindowAdapter { public void windowClosing (WindowEvent e) { gc.close(); System.exit(0); } }
}
