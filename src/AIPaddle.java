import java.awt.*;

/**
 * Created by Vitalii on 18.05.2017.
 */
public class AIPaddle implements Paddle,GameConstants {
    double y, yVel;
    boolean upAccel, downAccel;
    int player, x;
    Ball ball;
    final double GRAVITY = 0.94;

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
        g.fillRect(x,(int)y,20,80);
    }

    @Override
    public void move() {
        y = ball.getY() - 40;

        if (y<0)
            y=0;
        if (y>420)
            y=420;

    }

    public void setUpAccel(boolean input){
        upAccel = input;
    }

    public void setDownAccel(boolean input){
        downAccel = input;
    }

    @Override
    public int getY() {
        return (int)y;
    }
}
