package Views;

import java.awt.Canvas;
import java.lang.Thread;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import game.*;
import utils.Colors;

public class GameCanvas extends Canvas implements Runnable, KeyListener {
    public GameController game;
    private char directions = ' ';
    private boolean running = false;
    private Thread handleFrames;
    
    public GameCanvas() throws Exception {
        game = new GameController(20);
        this.setPreferredSize(new Dimension(401,540));
        this.addKeyListener(this);
        handleFrames = new Thread(this);
    }

    public void tick(){
        switch(directions){
            case 'r':
               game.movingHorizontal(+1, 0);
            break;
            case 'l':
                game.movingHorizontal(-1, 0);
            break;
            case 'u':
                game.movingVertical(-1, 0);
            break;
            case 'd':
                game.movingVertical(+1, 0);
            break;
        }

        directions = ' ';
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Colors.BLACK);
        g.fillRect(0,0,401,402);
        g.setColor(Colors.WHITE);
        g.fillRect(0, 402, 401, 138);
        

        var players = game.getPlayers();
        
        for (int i = 0; i < players.length; i++) {
            var p = players[i];
            g.setColor(p.color);
            g.fillRect(p.getX()*20, p.getY()*20, 20, 20);
            g.setColor(Color.white);
            g.drawString(""+p.getPoints(), p.getX()*20+5, p.getY()*20+15);

            g.setColor(p.color);
            g.drawString(p.username+":  "+p.getPoints(), 10, 420+(20*i));

        }
        
        for (Apple a : game.getApples()) {
            int level = a.getLevel();

            g.setColor(Colors.WHITE);
            g.drawRect(a.getX()*20+8, a.getY()*20+3, 3, 4);

            switch(level) {
                case 2: g.setColor(Colors.RED); break;
                case 1: g.setColor(Colors.ORANGE); break;
                default: g.setColor(Colors.LIGHT_RED); break;
            }
            
            g.fillRect(a.getX()*20+5, a.getY()*20+6, 10, 10);
            g.setColor(Colors.WHITE);
            g.drawRect(a.getX()*20+4, a.getY()*20+5, 11, 11);

            switch(level) {
                case 2: g.setColor(Colors.GREEN); break;
                case 1: g.setColor(Colors.DARK_GREEN); break;
                default: g.setColor(Colors.BROW); break;
            }
            g.fillRect(a.getX()*20+9, a.getY()*20+4, 2, 6);
        }

        g.setColor(Colors.BLACK);

        g.drawRect(370, 480, 20, 20);
        g.drawString("↦", 375, 495);
        g.drawRect(340, 480, 20, 20);
        g.drawString("↧", 345, 495);
        g.drawRect(310, 480, 20, 20);
        g.drawString("↤", 315, 495);
        g.drawRect(340, 450, 20, 20);
        g.drawString("↥", 345, 465);

        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        running = true;
        handleFrames.start();
    }


    public synchronized void stop() {
        running = false;
        try { handleFrames.join(); } 
        catch (InterruptedException e) { 
            System.err.println("error in stop()"); 
            System.exit(0); 
        }
    }

    @Override
    public void run() {
        while(running){
            tick();
            render();
            try{
                    Thread.sleep(1000/60);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            directions = 'r';
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            directions = 'l';
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP){
            directions = 'u';
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            directions = 'd';
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
