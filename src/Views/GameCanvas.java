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

public class GameCanvas extends Canvas implements Runnable, KeyListener{
    public GameController game;
    private char directions = ' ';
    private boolean running = false;
    private Thread handleFrames;
    
    public GameCanvas() throws Exception {
        game = new GameController(20);
        game.addPlayer(new Player("id","vedras",Color.blue, 0, 0));
        game.addApple(new Apple("id",5,8,10,-10,500));
        this.setPreferredSize(new Dimension(600,440));
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
        g.setColor(Color.black);
        g.fillRect(0,0,401,440);
        g.setColor(Color.gray);
        g.fillRect(401, 0, 200, 440);
        
        
        for (Player p : game.getPlayers()) {
            g.setColor(p.color);
            g.fillRect(p.getX()*20, p.getY()*20, 20, 20);
            g.setColor(Color.white);
            g.drawString(""+p.getPoints(), p.getX()*20+5, p.getY()*20+15);
        }
        
        for (Apple a : game.getApples()) {
            g.setColor(Color.red);
            g.fillRect(a.getX()*20, a.getY()*20, 20, 20);
            g.setColor(Color.white);
            g.drawString(""+a.getValue(), a.getX()*20+5, a.getY()*20+15);

        }

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
        // TODO Auto-generated method stub
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
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

   
    
}
