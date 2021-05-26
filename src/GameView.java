
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameView extends JFrame implements GameModelObserver{

    GameModel model;
    JPanel drawPanel;
    JPanel contentPanel;

    GameView(){
        model = new GameModel();
        model.addObserver(this);

        setTitle("Ping Pong");
        setResizable(false);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.DARK_GRAY);
        setContentPane(contentPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(getClass().getResource("img.png"));
        setIconImage(icon.getImage());

        drawPanel = new GamePanel(model);
        contentPanel.add(drawPanel, BorderLayout.CENTER);

        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                model.keyPressed(e);
            }
            public void keyReleased(KeyEvent e) {
                model.keyReleased(e);
            }

            public void keyTyped(KeyEvent e) {}
        });

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void update() {
        SwingUtilities.invokeLater(() -> repaint());
    }
}

