

import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
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

    private String RESPATH = "src/main/resources/img";

    private Army army;
    private Defense defense;
    private Hashtable<Position,Tile> map = new Hashtable<>();

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

    public Game(Level level){
        this.level = level;
        thread.start();
    }

    private void define(){

        width = getWidth();
        height = getHeight();
        mapString = level.getMap();
        setupImages();
        setupMap();
        this.army=new Army(map);
        this.defense = new Defense(map,level.towerSpawnRate);
        shop = new Shop(army);
        gameContainer = new GameContainer();
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
    }


    @Override
    public void run() {

        while(true){
            if(!isFirst){
                army.updateArmy();
                defense.createTower();
                defense.update();
                System.out.println(defense.getTowerCount());

                //gameContainer.move(army); //do something to change the game
            }
            repaint();  // repaint the graphics in the gameframe.
            try{

                thread.sleep(50);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
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

    private void setupMap(){
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
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapGoal)) {
                    background[x][y] = Translator.squareGoal;
                    air[x][y] = Translator.indexGoal;
                    map.put(new Position(x,y), new RoadTile(new Position(x,y), "goal"));
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapStart)) {
                    background[x][y] = Translator.squareStart;
                    air[x][y] = Translator.indexStart;
                    map.put(new Position(x,y), new RoadTile(new Position(x,y), "start"));
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapTowerZone)) {
                    background[x][y] = Translator.squareTowerZone;
                    air[x][y] = Translator.indexTowerZone;
                    map.put(new Position(x,y), new TowerTile(new Position(x,y)));
                }
            }
    }
}
