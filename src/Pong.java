import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Vitalii on 18.05.2017.
 */
public class Pong extends Applet implements Runnable, KeyListener{
    final int WIDTH =700, HEIGHT = 500;
    Thread thread;
    
    public void init(){
        this.resize(WIDTH,HEIGHT);
        this.addKeyListener(this);
        Thread thread = new Thread(this);
        thread.start();
    }
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
    }
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void run() {
        for(;;){

            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP){
            System.out.println("3");
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN){
            System.out.println("4");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP){
            System.out.println("1");
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN){
            System.out.println("2");
        }
    }
}
