package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

import helpers.Translator;
import helpers.Position;
import tower.Defense;
import tower.LaserTower;
import trooper.*;
import tile.*;


/**
 * Created by Simon on 2016-11-30.
 *
 * Contains the game board. extends the JPanel
 */
public class Game extends JPanel implements Runnable {

    private String RESPATH = "src/main/resources/img";

    private Army army;
    public Position startPosition;
    private Defense defense;
    private Hashtable<Position,Tile> map = new Hashtable<>();
    private Player player;
    private Results results;

    public static int width, height;
    public static Image[] square_material = new Image[50];
    public static Image[] square_air = new Image[50];
    public static Image[] button_images = new Image[10];

    public static int[][]background;
    public static int[][]air;

    public static Point mousePoint = new Point (0,0);

    public static Shop shop;
    public static Level level;
    public static GameContainer gameContainer;

    //DRAW UNIT
    private int x, y;

    private final int GAMETIMER = 50;

    public String mapString =   "000000000000"+
                                "0RR00R00R0R0"+
                                "0R00R0R0R0R0"+
                                "0RR0R0R0R0R0"+
                                "0R00RRR0R0R0"+
                                "0R00R0R0R0R0"+
                                "0R00R0R0R0RR"+
                                "000000000000";

    private Thread thread = new Thread(this);//thread that runs the game
    private static boolean isFirst = true; //first time the game opens = true

    public Game(Level level, Player player){
        this.level = level;
        thread.start();
        this.player = player;
        results = new Results();
    }

    private void define(){

        width = getWidth();
        height = getHeight();
        mapString = level.getMap();
        setupImages();
        startPosition = new Position();
        setupMap();
        this.army = new Army(map, startPosition);
        this.defense = new Defense(map,level.towerSpawnRate);
        shop = new Shop(army);
        gameContainer = new GameContainer();
        gameContainer.setColumnCount(level.getColumns());
        gameContainer.setRowCount(level.getRows());
        x = startPosition.getX();
        y = startPosition.getY();
    }

    //Paints the components in game
    public void paintComponent(Graphics gr){

        if(isFirst) {
            define();   //define the squarearray
            isFirst = false;
            //set background color to gray
        }

        gr.setColor(new Color(149, 149, 149));
        gr.fillRect(0,0, getWidth(), getHeight());
        gameContainer.draw(gr);
        shop.draw(gr);
        army.draw(gr, square_air, gameContainer.getAirSquares());
        defense.draw(gr, gameContainer.getAirSquares());
    }

    @Override
    public void run() {
        int totalReached = 0;
        ArrayList<Trooper> refunds;
        while(totalReached < level.getUnitsToWin()){
            if(!isFirst){
                army.updateArmy();
                refunds = army.getFinished();
                for(Trooper trooper: refunds) {
                    shop.refund(trooper);
                }
                shop.subtractUnitsToWin(army.getReachedGoal());
                totalReached += army.getReachedGoal();
                Position towerPosition = defense.createTower();
                if(towerPosition != null){
                    buildTowers(towerPosition);
                }
                defense.update();

                //gameContainer.move(army); //do something to change the game
            }
            repaint();  // repaint the graphics in the gameframe.
            try{

                thread.sleep(GAMETIMER);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        shop.stopTime();
        results.setCreditsused(shop.getNoOfCredits());
        results.setLevelName(level.getLevelName());
        results.setTime(shop.getTime());
        player.setResult(results);
        System.out.println(results);
    }


    private void cycle(Trooper t) {

    }


    private void buildTowers(Position towerPosition) {
        air[towerPosition.getX()][towerPosition.getY()] = Translator.indexTower;
    }

    private int updateZombieCounter(int totalReached) {
        shop.subtractUnitsToWin(army.getReachedGoal());
        return army.getReachedGoal();
    }

    private void setupImages(){

        for (int i = 0; i <square_material.length ; i++) {
            square_material[i] = new ImageIcon(RESPATH + "/materials.png").getImage();
            square_material[i] = createImage(new FilteredImageSource(
                    square_material[i].getSource(), new CropImageFilter(0, 50*i,50,50)
            ));
        }

        for (int i = 0; i <square_air.length ; i++) {
            square_air[i] = new ImageIcon(RESPATH + "/air.png").getImage();
            square_air[i] = createImage(new FilteredImageSource(
                    square_air[i].getSource(), new CropImageFilter(0, 50*i,50,50)
            ));
        }

        for (int i = 0; i <button_images.length ; i++) {
            button_images[i] = new ImageIcon(RESPATH + "/buttons.png").getImage();
            button_images[i] = createImage(new FilteredImageSource(
                    button_images[i].getSource(), new CropImageFilter(0, 50*i,50,50)
            ));
        }
    }

    public void setupMap(){
        background = new int[GameContainer.columnCount][GameContainer.rowCount];
        air = new int[GameContainer.columnCount][GameContainer.rowCount];

        for (int y = 0; y < background[0].length ; y++)
            for (int x = 0; x < background.length; x++) {

                //Take out the character (String-value) at specific index
                char indexChar = mapString.charAt(
                        (y * GameContainer.columnCount) + x);

                if (Objects.equals(Character.toString(indexChar), Translator.mapGrass)) {
                    background[x][y] = Translator.squareGrass;
                    air[x][y] = Translator.indexBlank;

                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapRoad)) {
                    background[x][y] = Translator.squareRoad;
                    air[x][y] = Translator.indexBlank;
                    map.put(new Position(x,y), new RoadTile(new Position(x,y)));
                    //TODO REMOVE SOUTS
                    System.out.println("Utskrift i Game.Game.setupMap: Väg = X: " + x + " Y: " + y);
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapGoal)) {
                    background[x][y] = Translator.indexGoal;
                    air[x][y] = Translator.indexGoal;
                    map.put(new Position(x,y), new RoadTile(new Position(x,y), "goal"));
                    System.out.println("Utskrift i Game.Game.setupMap: MÅL = X: " + x + " Y: " + y);
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapStart)) {
                    background[x][y] = Translator.indexStart;
                    air[x][y] = Translator.indexStart;
                    map.put(new Position(x,y), new RoadTile(new Position(x,y), "start"));
                    System.out.println("Utskrift i Game.Game.setupMap: Start = X: " + x + " Y: " + y);
                    startPosition.setX(x);
                    startPosition.setY(y);
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapTowerZone)) {
                    background[x][y] = Translator.squareTowerZone;
                    air[x][y] = Translator.indexTowerZone;
                    map.put(new Position(x,y), new TowerTile(new Position(x,y)));
                }
            }
    }

    public Hashtable<Position,Tile>  getMap() {
        return map;
    }
}
