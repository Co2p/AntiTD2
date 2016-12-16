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

    public ErrorWindow(ErrorMessages message, LevelBuilder builder) {
        this.message = message;
        this.builder = builder;
        setUpGui();
        window.pack();
    }

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

    private JButton quit() {
        JButton quit = new JButton("QUIT");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        return quit;
    }

    public void setVisable() {
        window.setVisible(true);
    }
}
