import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.Objects;

/**
 * Created by Simon on 2016-11-30.
 *
 * Contains the game board. extends the JPanel
 */
public class GamePanel extends JPanel implements Runnable {


    public static int width, height;

    public static Image[] square_material = new Image[50];
    public static Image[] square_air = new Image[50];
    public static Image[] button_images = new Image[10];


    public static Frame frame;
    public static Translator translator;
    
    public static Point mousePoint = new Point (0,0);

    public static Shop shop;

    public static GameContainer gameContainer;

    //This is just a test string for the map

    public static int[][]background;
    public static int[][]air;

    public String mapString =   "00TT00000000"+
                                "SRRRR0000000"+
                                "00TTRTTT0000"+
                                "0000R0RRRT00"+
                                "0000RRR0RT00"+
                                "000TTT0TRT00"+
                                "00000000RT00"+
                                "00000000G000";

/*


   // public static Character[][] background;
   // public static Character[][] air;

    public String backgroundString =    "000000000000" +
                                        "111111111110" +
                                        "000000000010" +
                                        "000000000010" +
                                        "000001111110" +
                                        "000001000010" +
                                        "000001111010" +
                                        "000000000010" ;

        public String airString =       "011100000000" +
                                        "200000000000" +
                                        "000000110000" +
                                        "000000000000" +
                                        "000010000000" +
                                        "000010000000" +
                                        "000010000000" +
                                        "000000000130" ;

<<<<<<< HEAD
    private Level level;
=======

*/

    private Thread thread = new Thread(this);//thread that runs the game
    private static boolean isFirst = true; //first time the game opens = true

    public GamePanel(JFrame frame){

        GamePanel.frame = frame;
        GamePanel.frame.addMouseListener(new ClickHandler());
        GamePanel.frame.addMouseMotionListener(new ClickHandler());

        thread.start();
    }

    private void setupImages(){

        for (int i = 0; i <square_material.length ; i++) {
            square_material[i] = new ImageIcon("res/materials.png").getImage();
            square_material[i] = createImage(new FilteredImageSource(
                    square_material[i].getSource(), new CropImageFilter(0, 50*i,50,50)));
        }

        for (int i = 0; i <square_air.length ; i++) {
            square_air[i] = new ImageIcon("res/air.png").getImage();
            square_air[i] = createImage(new FilteredImageSource(
                    square_air[i].getSource(), new CropImageFilter(0, 50*i,50,50)));
        }

        for (int i = 0; i <button_images.length ; i++) {
            button_images[i] = new ImageIcon("res/buttons.png").getImage();
            button_images[i] = createImage(new FilteredImageSource(
                    button_images[i].getSource(), new CropImageFilter(0, 50*i,50,50)));
        }


        setupMap();
        //setupTemporaryMaps();
    }

    private void define(){


        //Define with and height for game plane
        width = getWidth();
        height = getHeight();
        setupImages();

        shop = new Shop();


        gameContainer = new GameContainer();
    }

    //Paints the components in game
    public void paintComponent(Graphics gr){

        if(isFirst) {
            define();   //define the squarearray
            isFirst = false;
        }

        //set background color to gray
        gr.setColor(new Color(149, 149, 149));
        gr.fillRect(0,0, getWidth(), getHeight());

        gameContainer.draw(gr);
        shop.draw(gr);
    }


    @Override
    public void run() {

        while(true){
            if(!isFirst){
                gameContainer.move(); //do something to change the game
            }
            repaint();  // repaint the graphics in the gameframe.
            try{

                thread.sleep(50);

            }catch(Exception e){
                e.printStackTrace();
            }
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
                    air[x][y] = Translator.air;
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapRoad)) {
                    background[x][y] = Translator.squareRoad;
                    air[x][y] = Translator.air;
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapGoal)) {
                    background[x][y] = Translator.squareRoad;
                    air[x][y] = Translator.goal;
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapStart)) {
                    background[x][y] = Translator.squareRoad;
                    air[x][y] = Translator.start;
                }
                if (Objects.equals(Character.toString(indexChar), Translator.mapTowerZone)) {
                    background[x][y] = Translator.squareGrass;
                    air[x][y] = Translator.towerZone;
                }
            }
    }
/*
    private void setupTemporaryMaps(){
        background = new Character[GameContainer.columnCount][GameContainer.rowCount];
        air = new Character[GameContainer.columnCount][GameContainer.rowCount];

        for (int y = 0; y < background[0].length ; y++) {
            for (int x = 0; x < background.length ; x++) {

                //Setup the maps with integers 1,0
                background[x][y] = backgroundString.charAt(((y*GameContainer.columnCount) + x));
                air[x][y] = airString.charAt(((y*GameContainer.columnCount) + x));
            }
        }
    }*/

}
