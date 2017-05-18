import java.awt.*;

/**
 * Created by Vitalii on 18.05.2017.
 */
public class HumanPaddle implements Paddle {
    double y, yVel;
    boolean upAccel, downAccel;
    int player, x;

    public HumanPaddle(int player){
        upAccel = false; downAccel = false;
        y=210; yVel=0;
        if(player ==1)
            x=20;
        else
            x=660;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,(int)y,20,80);
    }

    @Override
    public void move() {

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
