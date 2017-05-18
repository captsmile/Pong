import java.awt.*;

/**
 * Created by Vitalii on 18.05.2017.
 */
public class HumanPaddle implements Paddle, GameConstants {
    double y, yVel;
    boolean upAccel, downAccel;
    int player, x;
    final double GRAVITY = 0.94;

    public HumanPaddle(int player){
        this.player = player;
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
        if(upAccel){
            yVel -= 2;
        }
        else if (downAccel){
            yVel += 2;
        }
        else if (!upAccel && !downAccel){
            yVel *= GRAVITY;
        }

        if(yVel >= 5)
            yVel = 5;
        else if (yVel <= -5)
            yVel = -5;

        y += yVel;

        if (y<0)
            y=0;
        if (y>GAME_HEIGHT-PADDLE_HEIGHT)
            y=GAME_HEIGHT-PADDLE_HEIGHT;

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
