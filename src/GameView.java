
import java.awt.*;
import javax.swing.*;

public class GameView extends JFrame{

    GameModel model;

    GameView(){
        model = new GameModel();
        add(model);
        setTitle("Ping Pong");
        setResizable(false);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(getClass().getResource("img.png"));
        setIconImage(icon.getImage());

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}

