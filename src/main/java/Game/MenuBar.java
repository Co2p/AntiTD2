package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by andreas on 2016-12-13.
 */
public class MenuBar {

    private JMenuBar menu;
    private Lobby frame;
    private Thread t;

    public MenuBar(Lobby frame){
        menu = new JMenuBar();
        this.frame = frame;
        addMenus(createGameMenu(),createInfoMenu());
    }
    private void addMenus(JMenu gameMenu, JMenu infoMenu){
        menu.add(gameMenu);
        menu.add(infoMenu);

    }

    private JMenu createGameMenu(){
        //Game menu
        JMenu gameMenu = new JMenu("Game");
        gameMenu.add(createNewGameItem());
        gameMenu.add(createRestartItem());
        gameMenu.add(createPauseItem());
        gameMenu.add(createQuitItem());
        return gameMenu;
    }

    //TODO
    private JMenuItem createNewGameItem(){
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getCurrentGame().setVisible(false);
                frame.getCurrentGame().define();
                frame.getCurrentGame().setRunning(false);
                frame.getSelectLevelPanel().setVisible(true);
                frame.getImgPanel().setVisible(true);

            }
        });
        return newGame;
    }

    //TODO
    private JMenuItem createRestartItem(){
        JMenuItem restart = new JMenuItem("Restart level");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getCurrentGame().setPause(false);
                frame.getCurrentGame().define();
            }
        });
        return restart;
    }

    //TODO
    private JMenuItem createPauseItem(){
        JMenuItem pause = new JMenuItem("Pause");
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = pause.getText();
                if(s.equals("Pause")){
                    pause.setText("Resume");
                    if(frame.getCurrentGame() != null) {
                        frame.getCurrentGame().setPause(true);
                    }
                }
                else if (s.equals("Resume")){
                    pause.setText("Pause");
                    if(frame.getCurrentGame() != null) {
                        frame.getCurrentGame().setPause(false);
                    }

                }
            }
        });
        return pause;
    }

    private JMenuItem createQuitItem(){
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return quit;
    }

    private JMenu createInfoMenu(){
        JMenu info = new JMenu("info");
        info.add(createAboutItem());
        info.add(createHelpItem());
        return info;
    }

    private JMenuItem createAboutItem(){
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"" +
                        "Move shoot kill repeat was created by Alexander" +
                        " Nyström(dv15Aln), Andreas Hellman(dv15ahn), " +
                        "Simon Ekdahl(id13Sel), Gordon Cooper(id13Gcr) and " +
                        "Daniel Sjöström(id13Dsm) \nThe game were made " +
                        "in December 2016 for an obligatory assignment " +
                        "in the course 'Applicationsutveckling i java'" );

            }
        });
        return about;
    }

    private JMenuItem createHelpItem(){
        JMenuItem help = new JMenuItem("Help");

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("JOptionPane showMessageDialog");

                //TODO update the help text
                JLabel intro = new JLabel("To complete a level you need to spawn troopers using the buttons " +
                        " below the map,\n units have different prices and abilities.");

                JLabel towerTile = new JLabel("Towers will be placed on this tile by the computer.");

                JLabel startTile = new JLabel("Troopers spawn on this tile.");

                JLabel goalTile = new JLabel("This is the goal.");

                JLabel pitifulTroop = new JLabel("This is the Pitiful Trooper, it costs 20 gold and has no abilities.\n");

                JLabel armoredTroop = new JLabel("The Armored Trooper which has armor which protects it from enemy fire.\n");

                JLabel teleportTroop = new JLabel("The Teleport Trooper places a teleport when the teleport-button is pressed.\n");

                JLabel zombieTroop = new JLabel("After being killed the troopers become zombies with reduced stats until they are killed again.");

                JLabel tower = new JLabel("Towers are placed by the computer and shoot Troopers");

                JLabel placeTeleport = new JLabel("This button places the teleport lets all troops that land on it move 5 steps forward\n");

                JLabel leftRight = new JLabel("These buttons let you decide which direction the troopers want to take (Left or Right from their perspective)\n");

                String image = "<html><img src=\"" + ClassLoader.getSystemClassLoader().getResource("img/air.png") + "\"></img></html>";

                //ImageIcon infographic = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("img/air.png"));
                BufferedImage bi = null;
                try {
                    bi = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("img/air.png").toURI()));
                } catch (IOException | URISyntaxException e1){
                    e1.printStackTrace();
                }
                Image croppedImage = bi.getSubimage(0, 50, 50, 450);
                
                JPanel helpPanel = new JPanel(new BorderLayout());
                JPanel helpTextPanel = new JPanel(new GridLayout(9,1));

                helpTextPanel.add(towerTile);
                helpTextPanel.add(startTile);
                helpTextPanel.add(goalTile);
                helpTextPanel.add(pitifulTroop);
                helpTextPanel.add(armoredTroop);
                helpTextPanel.add(teleportTroop);
                helpTextPanel.add(zombieTroop);
                helpTextPanel.add(tower);
                helpTextPanel.add(placeTeleport);

                helpPanel.add(BorderLayout.NORTH, intro);
                helpPanel.add(BorderLayout.WEST, new JLabel(new ImageIcon(croppedImage)));
                helpPanel.add(BorderLayout.CENTER, helpTextPanel);

                JOptionPane.showMessageDialog(frame, helpPanel, "Help", JOptionPane.PLAIN_MESSAGE);
            }
        });
        return help;
    }

    public JMenuBar getMenuBar(){
        return menu;
    }
}
