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

    /**
     * Create a actionlistener for a enter name JTextField
     * @param lobby the lobby giving access to the main window
     * @param input the JTextField where user enters name
     */
    public EnterNameListener(Lobby lobby, JTextField input) {
        this.lobby = lobby;
        this.input = input;
    }

    /**
     * if the player has not entered a name the name will be set to
     * anonymous else the player name will be set to what is in the text field.
     * Then it will build up the level select screen.
     * @param e the event
     */
    public void actionPerformed(ActionEvent e) {
        if (input.getText().equals("Enter name")) {
            lobby.player.setName("Anonymous");
        } else {
            lobby.player.setName(input.getText());
        }
        lobby.buildSelectLevelPanel();
    }
}
