import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class GameModel extends JPanel implements Runnable{

    static final int WIDTH = 800;
    static final int HEIGHT = 400;
    static final Dimension SCREEN_SIZE = new Dimension(WIDTH,HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    GameModel(){
        newPaddles();
        newBall();
        score = new Score(WIDTH,HEIGHT);
        setFocusable(true);
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                paddle1.keyPressed(e);
                paddle2.keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                paddle1.keyReleased(e);
                paddle2.keyReleased(e);
            }

            public void keyTyped(KeyEvent e) {}

        });

        setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((WIDTH-BALL_DIAMETER)/2,random.nextInt(HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0,(HEIGHT-PADDLE_HEIGHT)/2,PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle(WIDTH-PADDLE_WIDTH,(HEIGHT-PADDLE_HEIGHT)/2,PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
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


        if(ball.y <= 0) {
            ball.setYDirection(-ball.speedY);
        }
        if(ball.y >= HEIGHT-BALL_DIAMETER) {
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
                repaint();
                delta--;
            }
        }
    }

    public void draw(Graphics g) {
        try {
            paddle1.draw(g);
            paddle2.draw(g);
            ball.draw(g);
            score.draw(g);
        } catch (Exception ex){
            ex.printStackTrace();
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

}
