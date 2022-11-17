package views;

import javax.swing.JOptionPane;

import utils.MessageController;

public class WindowsManager {
    public static void handleLauncher() {
        new Launcher();
    }
    public static void handleGame(MessageController socket) {
        new Game(socket);
    }

    public static void handleMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
