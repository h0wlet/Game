import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{

    Random random;
    int speedX;
    int speedY;

    Ball(int x, int y, int width, int height){
        super(x, y, width, height);
        random = new Random();
        int randXDirection = random.nextInt(2);
        if(randXDirection == 0){
            randXDirection--;
        }
        setXDirection(randXDirection*2);

        int randYDirection = random.nextInt(2);
        if(randYDirection == 0) {
            randYDirection--;
        }
        setYDirection(randYDirection*2);
    }

    public void setXDirection(int randomXDirection) {
        speedX = randomXDirection;
    }
    public void setYDirection(int randomYDirection) {
        speedY = randomYDirection;
    }

    public void move() {
        x += speedX;
        y += speedY;
    }

    public int getXCoordinate(){
        return x;
    }

    public int getYCoordinate(){
        return y;
    }

}