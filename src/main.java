import javax.swing.*;
<<<<<<<<< Temporary merge branch 1
=========
import java.util.ArrayList;
>>>>>>>>> Temporary merge branch 2

/**
 * Created by Daniel on 2016-11-24.
 */
public class main {
<<<<<<<<< Temporary merge branch 1
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                MenuPanels gui = new MenuPanels();
                gui.show();
            }});
=========

    public static void main(String[] args ){
        ArrayList levels = new ArrayList();
        levels.add(1);
        levels.add(2);

        SwingUtilities.invokeLater(() -> {
            Lobby lobby = new Lobby(levels);
            lobby.showGUI();
        });

>>>>>>>>> Temporary merge branch 2
    }
}
