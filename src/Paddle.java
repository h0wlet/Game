import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{

    int id;
    int speedY;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1 -> {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-10);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(10);
                }
            }
            case 2 -> {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-10);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(10);
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1 -> {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                }
            }
            case 2 -> {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + id);
        }
    }
    public void setYDirection(int yDirection) {
        speedY = yDirection;
    }
    public void move() {
        y += speedY;
    }
    public void draw(Graphics g) {
        if(id==1)
            g.setColor(Color.CYAN);
        else
            g.setColor(Color.PINK);
        g.fillRect(x, y, width, height);
    }
}