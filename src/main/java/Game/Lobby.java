package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Daniel on 2016-12-05.
 */
public class Lobby {

    private JFrame mainFrame;
    private JPanel enterNamePanel;
    private JPanel selectLevelPanel;
    public Player player;
    public ArrayList<Level> levelArray;
    private Level currentLevel;

    public Lobby(ArrayList<Level> levelArray) {
        this.levelArray = levelArray;
        setUpGUI();
    }

    private void setUpGUI() {
        mainFrame = new JFrame("Move Shoot Kill Repeat");
        addJMenuBar(mainFrame);
        mainFrame.setSize(1080,720);
        buildNamePanel();
        mainFrame.addMouseListener(new ClickHandler());
        mainFrame.addMouseMotionListener(new ClickHandler());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showGUI() {
        mainFrame.setVisible(true);
    }

    private void addJMenuBar(JFrame frame){
        MenuBar menu = new MenuBar(this);
        frame.setJMenuBar(menu.getMenuBar());
    }

    private void buildNamePanel() {
        enterNamePanel = new JPanel();
        player = new Player();
        JTextField enterNameField = new JTextField("Enter name", 15);
        JButton nameNextButton = new JButton("Next");

        nameNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setName(enterNameField.getText());
                buildSelectLevelPanel();
            }
        });

        enterNamePanel.add(enterNameField);
        enterNamePanel.add(nameNextButton);
        mainFrame.add(enterNamePanel);
    }

    private void buildSelectLevelPanel() {
        enterNamePanel.setVisible(false);
        selectLevelPanel = new JPanel();
        JLabel instructionLabel = new JLabel("Select your level "
        + player.getName());

        int noOfLevels = 0;
        for (int i = 0; i < levelArray.size(); i++) {
            noOfLevels++;
            JButton levelButton = new JButton(levelArray.get(i).getLevelName());

            selectLevelPanel.add(levelButton);

            levelButton.addActionListener(
                    new LevelSelectButtonListener(this,levelArray.get(i),player));
        }

        selectLevelPanel.add(instructionLabel);
        mainFrame.add(selectLevelPanel);
    }

    public void setMainFrameGame(Game game) {
        selectLevelPanel.setVisible(false);
        mainFrame.add(game, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    public void setCurrentLevel(Level level) {
        currentLevel = level;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
