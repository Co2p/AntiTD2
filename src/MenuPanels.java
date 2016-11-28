import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 2016-11-24.
 */
public class MenuPanels {
    private JFrame frame;
    private JTextField textField;

    public MenuPanels() {
        frame = new JFrame("Move shoot kill repeat! Zombie Edition");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel upperPanel   = buildUpperPanel();
        JPanel middlePanel  = buildMiddlePanel();

        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);

        frame.pack();
    }

    public void show() {
        frame.setVisible(true);
    }

    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();
        upperPanel.setBorder(BorderFactory.createTitledBorder("Zombies"));

        return upperPanel;
    }

    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        return middlePanel;
    }
}
