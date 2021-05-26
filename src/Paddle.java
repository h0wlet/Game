import java.awt.*;

public class Paddle extends Rectangle{
    int speedY;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
    }

    public void setYDirection(int yDirection) {
        speedY = yDirection;
    }

    public void move() {
        y += speedY;
    }

    public int getXCoordinate(){
        return x;
    }

    public int getYCoordinate(){
        return y;
    }

}