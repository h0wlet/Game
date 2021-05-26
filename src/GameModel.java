
import java.awt.event.KeyEvent;
import java.util.*;

class GameModel implements Runnable{

    static final int WIDTH = 800;
    static final int HEIGHT = 400;
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    ArrayList<GameModelObserver> observers = new ArrayList<>();

    GameModel(){
        newPaddles();
        newBall();
        score = new Score(WIDTH,HEIGHT);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        ball = new Ball((WIDTH-BALL_DIAMETER)/2,(HEIGHT-BALL_DIAMETER)/2,BALL_DIAMETER,BALL_DIAMETER);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0,(HEIGHT-PADDLE_HEIGHT)/2,PADDLE_WIDTH,PADDLE_HEIGHT);
        paddle2 = new Paddle(WIDTH-PADDLE_WIDTH,(HEIGHT-PADDLE_HEIGHT)/2,PADDLE_WIDTH,PADDLE_HEIGHT);
    }

    public void addObserver(GameModelObserver observer){
        observers.add(observer);
    }

    public void removeObserver(GameModelObserver observer){
        observers.remove(observer);
    }

    private void notifyObservers(){
        for(GameModelObserver observer : observers){
            observer.update();
        }
    }

    public void checkCollision() {

        if(ball.intersects(paddle1)) {
            ball.speedX++;
            if(ball.speedY > 0){
                ball.speedY++;
            } else {
                ball.speedY--;
            }
            ball.setXDirection(ball.speedX);
            ball.setYDirection(ball.speedY);
        }
        if (ball.intersects(paddle2)) {
            ball.speedX++;
            if(ball.speedY>0){
                ball.speedY++;
            } else {
                ball.speedY--;
            }
            ball.setXDirection(-ball.speedX);
            ball.setYDirection(ball.speedY);
        }


         if((ball.y <= 0) || (ball.y >= HEIGHT-BALL_DIAMETER)) {
            ball.setYDirection(-ball.speedY);
        }


        if(paddle1.y <= 0){
            paddle1.y = 0;
        }
        if(paddle1.y >= (HEIGHT-PADDLE_HEIGHT)) {
            paddle1.y = HEIGHT - PADDLE_HEIGHT;
        }
        if(paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if(paddle2.y >= (HEIGHT-PADDLE_HEIGHT)) {
            paddle2.y = HEIGHT - PADDLE_HEIGHT;
        }


        if(ball.x <= 0) {
            score.score2++;
            newBall();
            newPaddles();
            System.out.println("Player 2: " + score.score2);
            if (score.score2 == 5){
                System.out.println("Player 2 is winner!");
                System.exit(0);
            }
        }
        if(ball.x >= WIDTH-BALL_DIAMETER) {
            score.score1++;
            newBall();
            newPaddles();
            System.out.println("Player 1: " + score.score1);
            if (score.score1 == 5){
                System.out.println("Player 1 is winner!");
                System.exit(0);
            }
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double ticks = 50.0;
        double ns = 1000000000 / ticks;
        double delta = 0;
            while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                //repaint();
                notifyObservers();
                delta--;
            }
        }
    }

    public void move() {
        try {
            paddle1.move();
            paddle2.move();
            ball.move();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void setLeftPaddleDirection(Direction direction){
        if (direction == Direction.DOWN){
            paddle1.setYDirection(-10);
        } else if (direction == Direction.UP){
            paddle1.setYDirection(10);
        } else {
            paddle1.setYDirection(0);
        }
    }

    public void setRightPaddleDirection(Direction direction){
        if (direction == Direction.DOWN){
            paddle2.setYDirection(-10);
        } else if (direction == Direction.UP){
            paddle2.setYDirection(10);
        } else {
            paddle2.setYDirection(0);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            setLeftPaddleDirection(Direction.DOWN);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            setLeftPaddleDirection(Direction.UP);
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            setRightPaddleDirection(Direction.DOWN);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            setRightPaddleDirection(Direction.UP);
        }
    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
            setLeftPaddleDirection(Direction.STOP);
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            setRightPaddleDirection(Direction.STOP);
        }

    }

}
