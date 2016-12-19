package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Simon & Alexander on 2016-12-07.
 * Buttonlistener for levelbuttons.
 * indicates which level a button is assigned to
 *
 */
public class LevelSelectButtonListener implements ActionListener {

    private Lobby frame;
    private Level level;
    private Player player;
    private Game g;


    /**
     *  Crate a level button listener
     *
     * @param frame Lobby containing the main window
     * @param level The level this button represents
     * @param player Current player
     *
     * @see Lobby
     */
    public LevelSelectButtonListener(Lobby frame, Level level, Player player){
        super();
        this.frame = frame;
        this.level = level;
        this.player = player;
    }

    /**
     * If triggered it will see if the lobby already has a game if so it will
     * use that game object to start this level, else it will create a new game
     * @param e The event
     *
     * Finalized by Alexander (dv15anm)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(frame.getCurrentGame() == null) {
            g = new Game(level, player, frame.getEscreen());
            frame.setMainFrameGame(g);
            frame.setCurrentGame(g);
        } else {
            g = frame.getCurrentGame();
            g.define(level);
            frame.setMainFrameGame(g);
            g.setVisible(true);
        }
        frame.setCurrentLevel(level);
    }
}
