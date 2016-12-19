package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static Game.main.slj;

/**
 * Created by Andreas on 2016-12-15.
 *
 * This class is a JFrame that comes up when a player has played the game.
 * Depending if the player did succeed the map or not a Winscreen or a
 * Losescreen pops up.
 */
public class EndScreen {

    private Lobby lobby;
    private JFrame frame;

    /**
     * Class to create end screens, either lose or winning screen.
     * @param lobby gives the window access to the main window
     */
    public EndScreen(Lobby lobby){
        this.lobby=lobby;
    }

    /**
     * Create button for starting a new game, the action listner will open
     * the level select screen.
     * @return The new game button
     */
    private JButton createNewGame(){
        JButton newGame = new JButton("New level");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                lobby.getSelectLevelPanel().setVisible(true);
                lobby.getCurrentGame().setVisible(false);
                lobby.getImgPanel().setVisible(true);

            }
        });
        return newGame;
    }

    /**
     * Crete a button to exit the program
     * @return the quit button
     */
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

    /**
     * Create a button to show highscores for the current level
     * @return the highscore button
     */
    private JButton createHighscore(){
        JButton highScore = new JButton("See highscores");
        highScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Player> highScores =
                        slj.getFromDb(lobby.getCurrentLevel().getLevelName());
                HighScoreWindow highScoreWindow =
                        new HighScoreWindow(highScores);
            }
        });
        return highScore;

    }

    /**
     * Create a button to restart the current game
     * @param game the game to restart
     * @return the restart button
     */
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

    /**
     * Create a label with text after a win
     * @param player the player that played
     * @param results the results from the game
     * @return the victory label
     */
    private JLabel createWinLabel(Player player, Results results){
        JLabel text = new JLabel("<html><h1 style=\"text-align:center;" +
                " color:green\">YOU WON!</h1> <h3 style=\"text-align:center;" +
                "\">Contratulations "+
                player.getName() +"</h3> You finished with the " +results+
                "</html>");

        return text;
    }

    /**
     * Create a panel with buttons
     * @param game the current game
     * @return the button panel
     */
    private JPanel createButtonPanel(Game game){
        JPanel buttonpanel = new JPanel();
        buttonpanel.add(createNewGame());
        buttonpanel.add(createQuit());
        buttonpanel.add(createHighscore());
        buttonpanel.add(createReplay(game));
        return buttonpanel;
    }

    /**
     * Create text panel
     * @param text A label with text will either be winning label or losing
     *              label
     * @return the panel
     */
    private JPanel createTextPanel(JLabel text){
        JPanel textpanel = new JPanel();
        textpanel.add(text);
        return textpanel;
    }

    /**
     * Create the winning screen
     * @param p the current player
     * @param r the results from the last game
     * @param g the current game
     */
    public void createWinScrean(Player p, Results r, Game g){
        frame = new JFrame("WELL PLAYED!");
        frame.add(createTextPanel(createWinLabel(p,r)), BorderLayout.NORTH);
        frame.add(createButtonPanel(g),BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Create the loosing screen
     * @param p the current player
     * @param g the current game
     */
    public void createLooseScreen(Player p, Game g){
        frame = new JFrame("DEFEAT!");
        frame.add(createTextPanel(createDefeatLable(p)));
        frame.add(createButtonPanel(g),BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Create the label with text after a loss.
     * @param player the current player
     * @return the loosing label.
     */
    private JLabel createDefeatLable(Player player) {
        JLabel text = new JLabel("<html><h1 style=\"text-align:center;" +
                " color:red\">"+player.getName()+" YOU GOT DEFEATED!</h1> " +
                "<h3 style=\"text-align:center;\">Your zombie invasion " +
                "failed and you ran out of credits </h3></html>");

        return text;
    }
}
