import javax.swing.*;

/**
 * Created by Daniel on 2016-11-24.
 */
public class main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                Gui gui = new Gui();
                gui.show();

            }});

    }
}
