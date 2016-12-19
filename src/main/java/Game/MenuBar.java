package Game;

import helpers.Translator;

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
                BufferedImage air = null;
                BufferedImage buttons = null;
                try {
                    air = ImageIO.read(this.getClass().getResource("/img/air.png"));
                    buttons = ImageIO.read(this.getClass().getResource("/img/buttons.png"));

                } catch (IOException e1){
                    e1.printStackTrace();
                }
                Image towerZoneGraphic = air.getSubimage(0, 50 * Translator.indexTowerZone, 50, 50);
                Image startGraphic = air.getSubimage(0, 50 * Translator.indexStart, 50, 50);
                Image goalGraphic = air.getSubimage(0, 50 * Translator.indexGoal, 50, 50);
                Image towerGraphic = air.getSubimage(0, 50 * Translator.indexTower, 50, 50);
                Image armoredTrooperGraphic = air.getSubimage(0, 50 * Translator.indexArmoredTrooper, 50, 50);
                Image pitifulTrooperGraphic = air.getSubimage(0, 50 * Translator.indexTrooper, 50, 50);
                Image teleportTrooperGraphic = air.getSubimage(0, 50 * Translator.indexTeleporter, 50, 50);
                Image zombieTrooperGraphic = air.getSubimage(0, 50 * Translator.indexZombie, 50, 50);
                Image teleportZoneGraphic = air.getSubimage(0, 50 * Translator.indexTeleportZone, 50, 50);

                Image rightArrow = buttons.getSubimage(0, 50 * 5, 50, 50);

                JLabel intro = new JLabel("<html>To complete a level you need to spawn troopers using the " +
                        "buttons below the map. To get points a Trooper must be a <b color=\"green\">zombie</b> " +
                        "when it enters the goal.</html>");
                JLabel towerZoneText = new JLabel("Towers will be placed on this tile by the computer.");
                JLabel startTileText = new JLabel("Troopers spawn on this tile.");
                JLabel goalTileText = new JLabel("This is the goal.");
                JLabel pitifulTroopText = new JLabel("This is the Pitiful Trooper, " +
                        "it costs 20 gold and has no abilities.");
                JLabel armoredTroopText = new JLabel("The Armored Trooper has armor which " +
                        "protects it from enemy fire. Costs 40 gold");
                JLabel teleportTroopText = new JLabel("The Teleport Trooper places a teleport when the " +
                        "teleport-button is pressed.");
                JLabel zombieTrooperText = new JLabel("After being killed the troopers become zombies with " +
                        "reduced stats until they are killed again.");
                JLabel towerText = new JLabel( "Towers are placed by the computer and shoot Troopers");
                JLabel teleportText = new JLabel("This button places the teleport lets all troops that land on " +
                        "it move 5 steps forward.");

                JLabel leftRight = new JLabel("These buttons let you decide which direction the troopers want to take (Left or Right from their perspective)\n");

                JPanel helpPanel = new JPanel(new BorderLayout());
                JPanel helpTextPanel = new JPanel(new GridLayout(10,1));

                helpTextPanel.add(iconList(towerZoneGraphic, towerZoneText));
                helpTextPanel.add(iconList(startGraphic, startTileText));
                helpTextPanel.add(iconList(goalGraphic, goalTileText));
                helpTextPanel.add(iconList(pitifulTrooperGraphic, pitifulTroopText));
                helpTextPanel.add(iconList(armoredTrooperGraphic, armoredTroopText));
                helpTextPanel.add(iconList(teleportTrooperGraphic, teleportTroopText));
                helpTextPanel.add(iconList(zombieTrooperGraphic, zombieTrooperText));
                helpTextPanel.add(iconList(towerGraphic, towerText));
                helpTextPanel.add(iconList(teleportZoneGraphic, teleportText));
                helpTextPanel.add(iconList(rightArrow, leftRight));


                helpPanel.add(BorderLayout.NORTH, intro);
                helpPanel.add(BorderLayout.CENTER, helpTextPanel);

                JOptionPane.showMessageDialog(frame, helpPanel, "Help", JOptionPane.PLAIN_MESSAGE);
            }

            private JPanel iconList(Image i, JLabel l) {
                JPanel jp = new JPanel();
                jp.add(new JLabel(new ImageIcon(i)));
                jp.add(l);
                return jp;
            }
        });
        return help;
    }

    public JMenuBar getMenuBar(){
        return menu;
    }
}
