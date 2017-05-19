import java.awt.*;

/**
 * Created by Vitalii on 18.05.2017.
 */
public interface Paddle {
    public void draw(Graphics g);
    public void move();
    public int getY();
    public int getScore();
    public void incrementScore();
    public void setUpAccel(boolean input);
    public void setDownAccel(boolean input);
    public void setBall(Ball ball);
}
