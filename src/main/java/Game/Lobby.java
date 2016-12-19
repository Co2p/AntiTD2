package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Creates the user lobby which consists of a panel where the user gets to choose their name
 * and select which level that the user wants to play.
 *
 * Created by Daniel on 2016-12-05.
 */
public class Lobby {

    private JFrame mainFrame;
    private JPanel enterNamePanel;
    private JPanel imgPanel;
    private JPanel selectLevelPanel;
    public Player player;
    public ArrayList<Level> levelArray;
    private Level currentLevel;
    private Game currentGame;
    private EndScreen escreen;

    /**
     * Creates the end screen and sets up the GUI.
     * @param levelArray An array of levels
     *
     * id13dsm@cs.umu.se Daniel Sjöström
     */
    public Lobby(ArrayList<Level> levelArray) {
        escreen = new EndScreen(this);
        this.levelArray = levelArray;
        setUpGUI();
    }

    /**
     * Creates the frame and adds mouse listeners to that frame.
     *
     * id13dsm@cs.umu.se Daniel Sjöström
     */
    private void setUpGUI() {
        mainFrame = new JFrame("Move Shoot Kill Repeat");
        addJMenuBar(mainFrame);
        mainFrame.setSize(1080,720);
        buildNamePanel();
        mainFrame.addMouseListener(new ClickHandler());
        mainFrame.addMouseMotionListener(new ClickHandler());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Displays the GUI
     *
     * id13dsm@cs.umu.se Daniel Sjöström
     */
    public void showGUI() {
        mainFrame.setVisible(true);
    }

    /**
     * Adds a menu bar to the frame
     * @param frame The main frame
     *
     * dv15anm@cs.umu.se Alexander Nyström
     */
    private void addJMenuBar(JFrame frame){
        MenuBar menu = new MenuBar(this);
        frame.setJMenuBar(menu.getMenuBar());
    }

    /**
     * Builds the intro panel where the user is prompted to enter their nickname.
     * Creates a new player and adds actionlisteners to the interactive elements in the panel.
     *
     * id13dsm@cs.umu.se Daniel Sjöström
     */
    private void buildNamePanel() {
        enterNamePanel = new JPanel();

        imgPanel = new JPanel();
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("img/mskr_home.jpg"));
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(mainFrame.getWidth(), mainFrame.getHeight() ,  Image.SCALE_DEFAULT); // scale it the smooth way
        icon = new ImageIcon(newimg);
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        imgPanel.add(thumb);

        player = new Player();
        JTextField enterNameField = new JTextField("Enter name", 15);
        JButton nameNextButton = new JButton("Next");
        enterNameField.addActionListener(new EnterNameListener(this,enterNameField));
        nameNextButton.addActionListener(new EnterNameListener(this,enterNameField));

        enterNamePanel.add(enterNameField);
        enterNamePanel.add(nameNextButton);
        mainFrame.add(enterNamePanel, BorderLayout.NORTH);
        mainFrame.add(imgPanel, BorderLayout.CENTER);
        mainFrame.pack();

    }

    /**
     * Build the panel where the user is prompted to choose a level. It'll create X
     * number of level buttons depending on how many levels that exists in the XML-file.
     *
     * id13dsm@cs.umu.se Daniel Sjöström
     */
    public void buildSelectLevelPanel() {
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
        mainFrame.add(selectLevelPanel,BorderLayout.NORTH);
        mainFrame.pack();
    }

    /**
     * Adds the game panel to the main frame.
     * @param game A game object.
     *
     * dv15anm@cs.umu.se Alexander Nyström
     */
    public void setMainFrameGame(Game game) {
        selectLevelPanel.setVisible(false);
        imgPanel.setVisible(false);
        mainFrame.add(game, BorderLayout.CENTER);
        mainFrame.setVisible(true);

    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public JPanel getSelectLevelPanel(){
        return selectLevelPanel;
    }

    public void setCurrentLevel(Level level) {
        currentLevel = level;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setMainFrameVisible(boolean visible) {
        mainFrame.setVisible(visible);
    }

    public EndScreen getEscreen() {
        return escreen;
    }

    public JPanel getImgPanel(){
        return imgPanel;
    }
}
