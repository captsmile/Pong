import java.awt.*;

/**
 * Created by Vitalii on 18.05.2017.
 */
public class Ball {
    double xVel, yVel, x, y;

    public Ball(){
        x=350;
        y=250;
        xVel = -2;
        yVel =0.5;
    }

    public void draw (Graphics g){
        g.setColor(Color.white);
        g.fillOval((int)x-10,(int)y-10,20,20);
    }

    public void checkPaddleCollision(Paddle p1, Paddle p2){
        if (x <= 50){
            if (y >= p1.getY() && y<= p1.getY()+80){
                xVel = -xVel;
            }
        }
        else if (x>=650){
            if (y >= p2.getY() && y<= p2.getY()+80){
                xVel = -xVel;
            }
        }
    }

    public void move(){
        x += xVel;
        y += yVel;

        if(y < 10 || y > 490)
            yVel = -yVel;

    }
    public double getxVel() {
        return xVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


}
