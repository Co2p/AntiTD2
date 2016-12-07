import javax.swing.border.Border;
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

    public LevelSelectButtonListener(Frame frame, Level level){
        super();
        this.frame = frame;
        this.level = level;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.add(new GamePanel(level), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
