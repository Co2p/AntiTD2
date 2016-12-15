package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JMenuItem newGame = new JMenuItem("New Game / Restart game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getCurrentGame().setVisible(false);
                frame.getCurrentGame().define();
                frame.getCurrentGame().setRunning(false);
                frame.getSelectLevelPanel().setVisible(true);

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
                //TODO update the help text.
                JOptionPane.showMessageDialog(null,
                        "To complete a level you need to spawn " +
                                "troopers with the buttons showing trooper" +
                                " units,\nthe units have different prices and "+
                                "have different specifics. The cheapest one "+
                                "are the pitiful\ntrooper with a cost of 20 " +
                                "golds. This trooper has no special trait. " +
                                "The next\nTrooper are armored trooper wich " +
                                "has an armor making it take less damage " +
                                "from the\nenemy towers. The last trooper are"+
                                " an teleporter that can place a teleport on"+
                                " the\nground that the other troopers can use"+
                                " to skip crucial parts of the road.\nTo place"
                                +" an teleport you press the image containing "+
                                "a teleportplate when you have a teleport " +
                                "trooper active. ");
            }
        });
        return help;
    }

    public JMenuBar getMenuBar(){
        return menu;
    }
}
