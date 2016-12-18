package Game;

import helpers.ErrorMessages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alexander Nyström(dv15anm) on 15/12/2016.
 */
public class ErrorWindow {
    private JFrame window;
    private ErrorMessages message;
    private LevelBuilder builder;

    /**
     * Creates a error window
     * @param message the error message class containing different
     *                error messages
     * @param builder the level builder
     */
    public ErrorWindow(ErrorMessages message, LevelBuilder builder) {
        this.message = message;
        this.builder = builder;
        setUpGui();
        window.pack();
    }

    /**
     * Will build the window, with a message explaining the options
     */
    private void setUpGui(){
        window = new JFrame("Error");
        window.add(new JPanel().add(new JLabel("<html><div " +
                "style=\"margin:10px\"><h1 style=\"color:red; " +
                "text-align:center\">An error occurred!</h1> <p " +
                "style=\"font-size:18\">press " +
                "Error message to view the message or press default to use " +
                "the games default maps</p></div></html>")));
        JPanel bottom = new JPanel();
        bottom.add(defaultMap(),BorderLayout.SOUTH);
        bottom.add(messageButton(), BorderLayout.SOUTH);
        bottom.add(quit(),BorderLayout.SOUTH);
        window.add(bottom,BorderLayout.SOUTH);
        window.setLocationRelativeTo(null);
    }

    /**
     * Create a button for showing the error messages
     * @return the message button
     */
    private JButton messageButton() {
        JButton messageButton = new JButton("Error message");
        messageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,message);

            }

        });
        return messageButton;
    }

    /**
     * Create a button to load in the default maps
     * @return the default map button
     */
    private JButton defaultMap() {
        JButton defaultMap = new JButton("Default maps");
        defaultMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                builder.defaultMap();
                window.setVisible(false);
            }
        });
        return defaultMap;
    }

    /**
     * Create a button to exit the program
     * @return the quit button
     */
    private JButton quit() {
        JButton quit = new JButton("QUIT");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return quit;
    }

    /**
     * set the window visable
     */
    public void setVisable() {
        window.setVisible(true);
    }
}
