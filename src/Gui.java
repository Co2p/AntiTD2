import javax.swing.*;
import java.awt.*;

/**
 * Created by Simon on 2016-11-30.
 */
public class Gui {
    private JFrame frame;
    private GamePanel gamePanel;
    public static Dimension windowSize =  new Dimension (1080 , 720);

    public Gui() {
        frame = new JFrame("Move shoot kill repeat! Zombie Edition");
        frame.setPreferredSize(windowSize);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(false);
       // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setupGamePanel();

        frame.pack();

    }

    public void show(){
        frame.setVisible(true);
    }

    public void setupGamePanel(){
        gamePanel = new GamePanel(frame);
        frame.add(gamePanel, BorderLayout.CENTER);
    }


}
