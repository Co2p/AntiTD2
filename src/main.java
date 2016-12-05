import javax.swing.*;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> clickHandler

/**
 * Created by Daniel on 2016-11-24.
 */
public class main {
<<<<<<< HEAD

    public static void main(String[] args ){
        ArrayList levels = new ArrayList();
        levels.add(1);
        levels.add(2);

        SwingUtilities.invokeLater(() -> {
            Lobby lobby = new Lobby(levels);
            lobby.showGUI();
        });

=======
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                MenuPanels gui = new MenuPanels();
                gui.show();
            }});
>>>>>>> clickHandler
    }
}
