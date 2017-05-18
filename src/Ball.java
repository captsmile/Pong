import java.awt.*;

/**
 * Created by Vitalii on 18.05.2017.
 */
public class Ball implements GameConstants {
    double xVel, yVel, x, y;

    public Ball(){
        x=GAME_WIDTH/2;
        y=GAME_HEIGHT/2;
        xVel = getRandomDirection()*getRandomSpeed();
        yVel = getRandomDirection()*getRandomSpeed();
    }

    public double getRandomSpeed(){
        return (Math.random()*3 +2);
    }
    public int getRandomDirection(){
        int rand = (int)(Math.random()*2);
        if (rand==1){
            return 1;
        }
        else
        {
            return -1;
        }
    }

    public void draw (Graphics g){
        g.setColor(Color.white);
        g.fillOval((int)x-BALL_RADIUS/2,(int)y-BALL_RADIUS/2,BALL_RADIUS,BALL_RADIUS);
    }

    public void checkPaddleCollision(Paddle p1, Paddle p2){
        if (x <= PADDLE_WIDTH*2+BALL_RADIUS/2){
            if (y >= p1.getY() && y<= p1.getY()+PADDLE_HEIGHT){
                xVel = -xVel;
            }
        }
        else if (x>=GAME_WIDTH-PADDLE_WIDTH*2-BALL_RADIUS/2){
            if (y >= p2.getY() && y<= p2.getY()+PADDLE_HEIGHT){
                xVel = -xVel;
            }
        }
    }

    public void move(){
        x += xVel;
        y += yVel;

        if(y < BALL_RADIUS || y > GAME_HEIGHT-BALL_RADIUS)
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
