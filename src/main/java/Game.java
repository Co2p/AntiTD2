

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

    public String mapString =   "00000000000000000000"+
                                "0RR00R00R0R000000000"+
                                "0R00R0R0R0R0T0000000"+
                                "0RR0R0R0R0R0T0000000"+
                                "0R00RRR0R0R0T0000000"+
                                "0R00R0R0R0R0T0000000"+
                                "0R00R0R0R0RRT0000000"+
                                "0R00R0R0R0RR00000000"+
                                "0R00R0R0R0RRRRRG0000"+
                                "00000000000000000000";

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

        //TODO in gamecontainer set -> Rowcount and columncount to
        //TODO level.getRowCount() and level.getColumnCount in order to get dynamic maps
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
                System.out.println("Number of towes: " + defense.getTowerCount());
               if(army.getArmySize()<0) {
                   System.out.println("First trooper pos: " + "x " + army.getArmy().get(0).getPosition().getX() + "y: " + army.getArmy().get(0).getPosition().getX());
               }
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

        //TODO Change the GameContainer.rowcount to be dynamic. This value should be set when map is parsed


        background = new int[GameContainer.columnCount][GameContainer.rowCount];
        air = new int[GameContainer.columnCount][GameContainer.rowCount];

        for (int y = 0; y < background[0].length ; y++)
            for (int x = 0; x < background.length; x++) {

                //Take out the character (String-value) at specific index
                char indexChar = mapString.charAt(
                        //TODO Same thing goes for columncount.
                        (y * GameContainer.columnCount) + x);

                if (Objects.equals(Character.toString(indexChar), Translator.mapGrass)) {
                    background[x][y] = Translator.squareGrass;
                    air[x][y] = Translator.indexBlank;

                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapRoad)) {


                    int randomNum = 1 + (int)(Math.random() * 100);

                    if(randomNum >70 && randomNum<85){
                        randomNum = 3;
                    }else if (randomNum >= 85){
                        randomNum = 2;
                    }else{
                        randomNum = 1;
                    }

                    background[x][y] = randomNum;

                   // background[x][y] = Translator.squareRoad;
                    air[x][y] = Translator.indexBlank;
                    map.put(new Position(x,y), new RoadTile(new Position(x,y)));
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
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapTowerZone)) {
                    background[x][y] = Translator.squareTowerZone;
                    air[x][y] = Translator.indexTowerZone;
                    map.put(new Position(x,y), new TowerTile(new Position(x,y)));
                }
            }
    }
}
