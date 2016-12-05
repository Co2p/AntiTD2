import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 2016-11-24.
 */
public class main {

    public static void main(String[] args ){
        ArrayList levels = new ArrayList();
        levels.add(1);
        levels.add(2);

        SwingUtilities.invokeLater(() -> {
            Lobby lobby = new Lobby(levels);
            lobby.showGUI();
        });
    }
}
