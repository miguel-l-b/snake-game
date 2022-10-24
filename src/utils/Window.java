package utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends JFrame {
    public final String name;
    public Window(String title, String name) 
    { super(title); this.name = name; }
    public Window(String name) { this("", name); }
    
    public void centralize() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation(((d.width - this.getWidth())/2), ((d.height - this.getHeight())/2));
    }

    public void closeProgramOnClose() 
    { super.addWindowListener(new WindowExit()); }

    protected void start() {
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
    protected void exit() { super.setVisible(false); }
}
