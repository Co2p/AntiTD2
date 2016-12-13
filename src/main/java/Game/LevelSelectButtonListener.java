package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Simon on 2016-12-07.
 * Buttonlistener for levelbuttons.
 * indicates which level a button is assigned to
 *
 */
public class LevelSelectButtonListener implements ActionListener {

    private Frame frame;
    private Level level;
    private Player player;

    public LevelSelectButtonListener(Frame frame, Level level, Player player){
        super();
        this.frame = frame;
        this.level = level;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.add(new Game(level, player), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
