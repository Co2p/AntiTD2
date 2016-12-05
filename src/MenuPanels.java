import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Daniel on 2016-11-24.
 */
public class MenuPanels {
    private JFrame frame;

    public MenuPanels() {

        //Setting the name of the game
        frame = new JFrame("Move shoot kill repeat! Zombie Edition");
        //Setting up the game frame, full size window
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Setting up the menu bar and the middle panel
        JMenuBar menuBar    = buildMenuBar();
       // JPanel middlePanel  = buildMiddlePanel();
        buildGridLayout();

        //Adding menu and panel to game frame
        frame.add(menuBar, BorderLayout.NORTH);
        //frame.add(middlePanel, BorderLayout.CENTER);

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

    private void buildGridLayout(){
        frame.add(new Game());

    }
}
