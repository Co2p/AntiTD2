package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Simon on 2016-12-07.
 * Buttonlistener for levelbuttons.
 * indicates which level a button is assigned to
 *
 */
public class LevelSelectButtonListener implements ActionListener {

    private Lobby frame;
    private Level level;
    private Player player;
    private Game g;

    public LevelSelectButtonListener(Lobby frame, Level level, Player player){
        super();
        this.frame = frame;
        this.level = level;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(frame.getCurrentGame() == null) {
            g = new Game(level, player);
            frame.setMainFrameGame(g);
            frame.setCurrentGame(g);
        } else {
            g = frame.getCurrentGame();
            g.define(level);
            frame.setMainFrameGame(g);
            g.setVisible(true);
        }
        System.out.println("Thread i LevelSBlist: " + g.getThread());
        frame.setCurrentLevel(level);
    }
}
