package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andreas on 2016-12-15.
 */
public class EndScreen {

    private Lobby lobby;
    private JFrame frame;


    public EndScreen(Lobby lobby){
        this.lobby=lobby;
    }

    private JButton createNewGame(){
        JButton newGame = new JButton("New level");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                lobby.getSelectLevelPanel().setVisible(true);
                lobby.getCurrentGame().setVisible(false);

            }
        });
        return newGame;
    }

    private JButton createQuit(){
        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return quit;
    }

    private JButton createHighscore(){
        JButton highScore = new JButton("See highscores");
        highScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return highScore;

    }

    private JButton createReplay(Game game){
        JButton replay = new JButton("Play level again");
        replay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                game.define(Game.level);



            }
        });
        return replay;
    }

    private JLabel creteTextLabel(Player player, Results results){
        JLabel text = new JLabel("<html><h1 style=\"text-align:center;" +
                " color:green\">YOU WON!</h1> <h3 style=\"text-align:center;" +
                "\">Contratulations "+
                player.getName() +"</h3> You finished with the " +results+
                "</html>");

        return text;
    }

    private JPanel createButtonPanel(Game game){
        JPanel buttonpanel = new JPanel();
        buttonpanel.add(createNewGame());
        buttonpanel.add(createQuit());
        buttonpanel.add(createHighscore());
        buttonpanel.add(createReplay(game));
        return buttonpanel;
    }

    private JPanel createTextPanel(Player player, Results results){
        JPanel textpanel = new JPanel();
        textpanel.add(creteTextLabel(player,results));
        return textpanel;
    }

    public void createWinScrean(Player p, Results r, Game g){
        frame = new JFrame("WELL PLAYED!");
        frame.add(createTextPanel(p,r), BorderLayout.NORTH);
        frame.add(createButtonPanel(g),BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    public void createLooseScreen(Player p, Game g){
        frame = new JFrame("NOT WELL PLAYED!");
        frame.add(createButtonPanel(g),BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}
