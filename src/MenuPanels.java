import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Daniel on 2016-11-24.
 */
public class MenuPanels {
    private JFrame frame;

    public MenuPanels() {
        frame = new JFrame("Move shoot kill repeat! Zombie Edition");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar    = buildMenuBar();
        JPanel middlePanel  = buildMiddlePanel();

        frame.add(menuBar, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);

        frame.pack();
    }

    public void show() {
        frame.setVisible(true);
    }

    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Game");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
        fileMenu.add(newMenuItem);

        return menuBar;
    }

    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        return middlePanel;
    }
}
