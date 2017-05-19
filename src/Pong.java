import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Vitalii on 18.05.2017.
 */
public class Pong extends Applet implements Runnable, KeyListener, GameConstants, ActionListener {

    private boolean gameStarted, gameType;
    Graphics gfx;
    Image img;

    Thread thread;
    Paddle player1, player2;
    Ball ball;
    Button btn1 = new Button("one player");
    Button btn2 = new Button("two player");

    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println(e.getSource());
        if (e.getSource() == btn1) {
            player2 = new AIPaddle(2, ball);
        } else {
            player2 = new HumanPaddle(2, ball);
        }
        gameType = true;
        btn1.setVisible(false);
        btn2.setVisible(false);
    }

    public void init() {
        this.resize(GAME_WIDTH, GAME_HEIGHT);
        this.addKeyListener(this);
        setFocusable(true);
        gameStarted = gameType = false;
        img = createImage(GAME_WIDTH, GAME_HEIGHT);
        gfx = img.getGraphics();
        ball = new Ball();
        add(btn1);
        add(btn2);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        addKeyListener(this);
        player1 = new HumanPaddle(1, ball);
        player2 = new HumanPaddle(2, ball);
        thread = new Thread(this);
        thread.start();

    }

    public void paint(Graphics g) {
        Paddle checkPaddle;
        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        checkPaddle = ball.checkPaddleCollision(player1, player2);
        if (checkPaddle != null) {
            checkPaddle.incrementScore();
            gfx.setColor(Color.red);
            gfx.setFont(new Font("TimesRoman", Font.PLAIN, 22));
            gameStarted = false;
            ball = new Ball();
            player2.setBall(ball);
            gfx.drawString("Game Over", GAME_WIDTH / 2, 250);
        } else {
            player1.draw(gfx);
            ball.draw(gfx);
            player2.draw(gfx);
        }
        if (!gameStarted) {
            gfx.setColor(Color.white);
            gfx.setFont(new Font("TimesRoman", Font.PLAIN, 22));
            gfx.drawString("Tennis", (GAME_WIDTH / 2), 100);
            gfx.drawString("Press Enter to Begin", (GAME_WIDTH / 2), 130);
        }
        gfx.setColor(Color.red);
        gfx.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        gfx.drawString(String.valueOf(player1.getScore()), 50, 50);
        gfx.drawString(String.valueOf(player2.getScore()), GAME_WIDTH - 80, 50);

        g.drawImage(img, 0, 0, this);
    }

    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void run() {
        while (true) {
            if (gameStarted && gameType) {
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
        System.out.println("pres");
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player1.setUpAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player1.setDownAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            player2.setUpAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player2.setDownAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameStarted = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player1.setUpAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player1.setDownAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            player2.setUpAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player2.setDownAccel(false);
        }
    }
}
