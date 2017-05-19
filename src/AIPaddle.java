import java.awt.*;

/**
 * Created by Vitalii on 18.05.2017.
 */
public class AIPaddle implements Paddle,GameConstants {
    double y, yVel;
    boolean upAccel, downAccel;
    int player, x;
    int score = 0;


    public void setBall(Ball ball) {
        this.ball = ball;
    }

    Ball ball;

    public AIPaddle(int player, Ball b){
        this.player = player;
        this.ball = b;
        upAccel = false; downAccel = false;
        y=GAME_HEIGHT/2-PADDLE_HEIGHT/2; yVel=0;
        if(player ==1)
            x=PADDLE_WIDTH;
        else
            x=GAME_WIDTH-PADDLE_WIDTH*2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,(int)y,PADDLE_WIDTH,PADDLE_HEIGHT);
    }

    @Override
    public void move() {
        y = ball.getY() - PADDLE_HEIGHT / 2;
        if (y<0)
            y=0;
        if (y>GAME_HEIGHT-PADDLE_HEIGHT)
            y=GAME_HEIGHT-PADDLE_HEIGHT;

    }

    @Override
    public int getY() {
        return (int)y;
    }
    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void incrementScore() {
        score++;
    }
}
