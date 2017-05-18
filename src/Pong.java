import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Vitalii on 18.05.2017.
 */
public class Pong extends Applet implements Runnable, KeyListener, GameConstants{

    private boolean gameStarted;
    Thread thread;
    HumanPaddle player1;
    AIPaddle player2;
    Ball ball;

    public void init(){
        this.resize(GAME_WIDTH,GAME_HEIGHT);
        this.addKeyListener(this);
        gameStarted = false;
        ball = new Ball();
        player1 = new HumanPaddle(1);
        player2 = new AIPaddle(2,ball);
        thread = new Thread(this);
        thread.start();
    }
    public void paint(Graphics g){
        Paddle checkPaddle;
        g.setColor(Color.black);
        g.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        checkPaddle = ball.checkPaddleCollision(player1, player2);
        if (checkPaddle != null){
            checkPaddle.setScore();
            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
            gameStarted = false;
            ball = new Ball();
            player2.setBall(ball);
            g.drawString("Game Over", GAME_WIDTH/2,250);
        }
        else{
            player1.draw(g);
            ball.draw(g);
            player2.draw(g);
        }
        if (!gameStarted){
            g.setColor(Color.white);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
            g.drawString("Tennis", (GAME_WIDTH/2),100);
            g.drawString("Press Enter to Begin", (GAME_WIDTH/2),130);
        }
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        g.drawString(String.valueOf(player1.getScore()), 50,50);
        g.drawString(String.valueOf(player2.getScore()), GAME_WIDTH-80,50);
    }
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void run() {
        while(true){
            if (gameStarted){
                player1.move();
                player2.move();
                ball.move();
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
            player1.setUpAccel(true);
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN){
            player1.setDownAccel(true);
        }
        else if (e.getKeyCode()== KeyEvent.VK_ENTER){
            gameStarted = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP){
            player1.setUpAccel(false);
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN){
            player1.setDownAccel(false);
        }
    }
}
