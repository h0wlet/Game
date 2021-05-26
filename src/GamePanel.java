import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final GameModel model;

    static final int WIDTH = 800;
    static final int HEIGHT = 400;

    public GamePanel(GameModel model){
        this.model = model;
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        setBackground(Color.DARK_GRAY);

        g.setColor(Color.CYAN);
        g.fillRect(model.paddle1.getXCoordinate(), model.paddle1.getYCoordinate(), WIDTH, HEIGHT);

        g.setColor(Color.PINK);
        g.fillRect(model.paddle2.getXCoordinate(), model.paddle2.getYCoordinate(), WIDTH, HEIGHT);

        try {
            g.setColor(Color.WHITE);
            g.fillOval(model.ball.getXCoordinate(), model.ball.getYCoordinate(), WIDTH, HEIGHT);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD,70));

        g.drawLine(WIDTH/2 - 1, 0, WIDTH/2 - 1, HEIGHT);
        g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
        g.drawLine(WIDTH/2 + 1, 0, WIDTH/2 + 1, HEIGHT);

        g.drawString(String.valueOf(model.score.score1), (WIDTH/2) - 55, 50);
        g.drawString(String.valueOf(model.score.score2), (WIDTH/2) + 17, 50);

    }
}
