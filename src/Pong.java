import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Vitalii on 18.05.2017.
 */
public class Pong extends Applet implements Runnable, KeyListener{
    final int WIDTH =700, HEIGHT = 500;
    String font = "PressStart2P.ttf";
    private boolean gameStarted;
    Thread thread;
    HumanPaddle p1;
    AIPaddle p2;
    Ball ball;

    public void init(){
        this.resize(WIDTH,HEIGHT);
        this.addKeyListener(this);
        gameStarted = false;
        ball = new Ball();
        p1= new HumanPaddle(1);
        p2 = new AIPaddle(2,ball);
        thread = new Thread(this);
        thread.start();
    }
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        if (ball.getX()< -10 || ball.getX() > 710){
            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
            gameStarted = false;
            ball.setX(350);
            g.drawString("Game Over", 300,250);
        }
        else{
            p1.draw(g);
            ball.draw(g);
            p2.draw(g);
        }

        if (!gameStarted){
            g.setColor(Color.white);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
            g.drawString("Tennis", 320,100);
            g.drawString("Press Enter to Begin", 260,130);
        }
    }
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void run() {
        while(true){
            if (gameStarted){
                p1.move();
                p2.move();
                ball.move();
                ball.checkPaddleCollision(p1,p2);
                repaint();
            }
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
            p1.setUpAccel(true);
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN){
            p1.setDownAccel(true);
        }
        else if (e.getKeyCode()== KeyEvent.VK_ENTER){
            gameStarted = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP){
            p1.setUpAccel(false);
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN){
            p1.setDownAccel(false);
        }
    }
}
