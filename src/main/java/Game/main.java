package Game;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Initiates a LevelBuilder which will build levels from an XML-file, the main class
 * will also start the process of creating the frame and panels for the game.
 *
 * Created by Daniel on 2016-11-24.
 */
public class main {

    public static SQLiteJDBC slj = new SQLiteJDBC();

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
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            Lobby lobby = new Lobby(levelArray);
            lobby.showGUI();
        });

    }
}
