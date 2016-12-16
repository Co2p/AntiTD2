package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alexander Nyström(dv15anm) on 16/12/2016.
 */
public class EnterNameListener implements ActionListener {

    private Lobby lobby;
    private JTextField input;


    public EnterNameListener(Lobby lobby, JTextField input) {
        this.lobby = lobby;
        this.input = input;
    }

    public void actionPerformed(ActionEvent e) {
        if (input.getText().equals("Enter name")) {
            lobby.player.setName("Anonymous");
        } else {
            lobby.player.setName(input.getText());
        }
        lobby.buildSelectLevelPanel();
    }
}
