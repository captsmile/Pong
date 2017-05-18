import java.awt.*;

/**
 * Created by Vitalii on 18.05.2017.
 */
public interface Paddle {
    public void draw(Graphics g);
    public void move();
    public int getY();
}
