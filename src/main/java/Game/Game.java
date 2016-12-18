package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

import helpers.Translator;
import helpers.Position;
import tower.Defense;
import trooper.*;
import tile.*;


/**
 * Created by Simon on 2016-11-30.
 *
 * Contains the game board. extends the JPanel
 */
public class Game extends JPanel implements Runnable {

    private String RESPATH = "/img";

    private Army army;
    public Position startPosition;
    private Defense defense;
    private Hashtable<Position,Tile> map = new Hashtable<>();
    private Player player;
    private Results results;
    private volatile boolean pause, running = true;
    private EndScreen endScreen=null;

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
    private static volatile boolean isFirst = true; //first time the game opens = true

    public Game(Level level, Player player, EndScreen endScreen){
        this.level = level;
        this.player = player;
        results = new Results();
        this.endScreen = endScreen;
        thread.start();

    }

    public void define(){

        width = getWidth();
        height = getHeight();
        mapString = level.getMap();
        setupImages();
        startPosition = new Position();
        setupMap();
        this.army = new Army(map, startPosition);
        this.defense = new Defense(map,level.towerSpawnRate);
        gameContainer = new GameContainer();
        GameContainer.setColumnCount(level.getColumns());
        GameContainer.setRowCount(level.getRows());
        gameContainer.define();
        shop = new Shop(army);

        x = startPosition.getX();
        y = startPosition.getY();
    }

    public void define(Level level){
        running = true;
        isFirst = true;
        this.map = new Hashtable<>();
        this.level = level;
        width = getWidth();
        height = getHeight();
        mapString = level.getMap();
        setupImages();
        startPosition = new Position();
        setupMap();
        this.army = new Army(map, startPosition);
        this.defense = new Defense(map,level.towerSpawnRate);
        gameContainer = new GameContainer();
        GameContainer.setColumnCount(level.getColumns());
        GameContainer.setRowCount(level.getRows());
        gameContainer.define();
        shop = new Shop(army);

        x = startPosition.getX();
        y = startPosition.getY();
        thread = new Thread(this);
        thread.start();
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
        while(!isWin(totalReached) && running && !isDefeat()){
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
            }
            try{

                thread.sleep(GAMETIMER);

            }catch(Exception e){
                e.printStackTrace();
            }
            while(pause){
                try {
                    thread.sleep(100);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            repaint();  // repaint the graphics in the gameframe.
        }
        shop.stopTime();
        if (isWin(totalReached)) {
            results.setCreditsUsed(shop.getNoOfCredits());
            results.setLevelName(level.getLevelName());
            results.setTime(shop.getTime());
            player.setResults(results);
            endScreen.createWinScrean(player,results,this);
            //TODO do this a better way
            player.sendResult();
        } else if (isDefeat()) {
            endScreen.createLooseScreen(player,this);
        }

    }

    private boolean isWin(int unitsReachedGoal) {
        return (unitsReachedGoal == level.getUnitsToWin());
    }

    private boolean isDefeat() {
        if (isFirst) {
            return false;
        }
        return ((army.getArmy().size() == 0) && (shop.getNoOfCredits() == 0));
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
            square_material[i] = new ImageIcon(this.getClass().getResource(RESPATH+"/materials.png")).getImage();
//            square_material[i] = new ImageIcon(RESPATH + "/materials.png").getImage();
            square_material[i] = createImage(new FilteredImageSource(
                    square_material[i].getSource(), new CropImageFilter(0, 50*i,50,50)
            ));
        }

        for (int i = 0; i <square_air.length ; i++) {
            square_air[i] = new ImageIcon(this.getClass().getResource(RESPATH+"/air.png")).getImage();
//            square_air[i] = new ImageIcon(RESPATH + "/air.png").getImage();
            square_air[i] = createImage(new FilteredImageSource(
                    square_air[i].getSource(), new CropImageFilter(0, 50*i,50,50)
            ));
        }

        for (int i = 0; i <button_images.length ; i++) {
            button_images[i] = new ImageIcon(this.getClass().getResource(RESPATH+"/buttons.png")).getImage();
//            button_images[i] = new ImageIcon(RESPATH + "/buttons.png").getImage();
            button_images[i] = createImage(new FilteredImageSource(
                    button_images[i].getSource(), new CropImageFilter(0, 50*i,50,50)
            ));
        }
    }


    /**
     * Method to setup the map images in the arrays Background & Air
     *
     * */
    public void setupMap(){
        background = new int[level.getColumns()][level.getRows()];
        air = new int[level.getColumns()][level.getRows()];

        for (int y = 0; y < background[0].length ; y++)
            for (int x = 0; x < background.length; x++) {

                //Take out the character (String-value) at specific index
                char indexChar = mapString.charAt(
                        (y * level.getColumns()) + x);

                if (Objects.equals(Character.toString(indexChar), Translator.mapGrass)) {
                    background[x][y] = Translator.squareGrass;
                    air[x][y] = Translator.indexBlank;
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapRoad)) {
                    background[x][y] = Translator.squareRoad;
                    air[x][y] = Translator.indexBlank;
                    RoadTile road = new RoadTile(new Position(x,y));
                    if (level.getLandOn() != null && level.getZone() != null) {
                        road.setLandOnModifier(level.getZone(),level.getLandOn());
                    }
                    map.put(new Position(x,y), road);
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapGoal)) {
                    background[x][y] = Translator.indexGoal;
                    air[x][y] = Translator.indexGoal;
                    map.put(new Position(x,y), new RoadTile(new Position(x,y), "goal"));
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapStart)) {
                    background[x][y] = Translator.indexStart;
                    air[x][y] = Translator.indexStart;
                    map.put(new Position(x,y), new RoadTile(new Position(x,y), "start"));
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

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public Thread getThread() {
        return thread;
    }

    public void setRunning(boolean b){
        running = b;
    }

    public Hashtable<Position,Tile>  getMap() {
        return map;
    }
}
