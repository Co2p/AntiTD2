package Game;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 2016-11-24.
 */
public class main {

    public static void main(String[] args ){

        ArrayList<Level> levelArray = new ArrayList<Level>();
        LevelBuilder lb;

        if(args.length > 0) {
            lb = new LevelBuilder(args[0]);
        } else {
            lb = new LevelBuilder();
        }

        for (int i = 0; i < lb.getNoOfLevels(); i++) {
            Level l = lb.buildLevel(i);
            levelArray.add(l);
        }

        SwingUtilities.invokeLater(() -> {
            Lobby lobby = new Lobby(levelArray);
            lobby.showGUI();
        });

    }
}
