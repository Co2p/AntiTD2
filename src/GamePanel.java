import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

/**
 * Created by Simon on 2016-11-30.
 *
 * Contains the game board. extends the JPanel
 */
public class GamePanel extends JPanel implements Runnable {

    private int columnCount = 12;   //define number of columns
    private int rowCount = 8;       //define numer of rows
    private int squareSize = 50;   //change this to be dynamic (square size)
    private static int width, height;

    public static Image[] square_material = new Image[50];
    public static Image[] square_air = new Image[50];

    //This is just a test string for the map
    public static Character[][] background;
    public static Character[][] air;
    public String backgroundString =    "000000000000" +
                                        "111111111110" +
                                        "000000000010" +
                                        "000000000010" +
                                        "000001111110" +
                                        "000001000010" +
                                        "000001111010" +
                                        "000000000010" ;

        public String airString =       "011100000000" +
                                        "000000000000" +
                                        "000000110000" +
                                        "000000000000" +
                                        "000010000000" +
                                        "000010000000" +
                                        "000010000000" +
                                        "000000000100" ;


    private Square[][] backgroundSquares;    //Array containg the ground backgroundSquares
    private Square[][] airSquares;           //Array containing the air backgroundSquares.

    private Thread thread = new Thread(this);//thread that runs the game
   // public static GameBoard gameBoard;    //GameBoard is the game JPanel
    private static boolean isFirst = true; //first time the game opens = true

    public GamePanel(){
        thread.start();
    }

    public void setupImages(){
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
    }

    public void define(){
        //Define with and height for game plane
        width = getWidth();
        height = getHeight();
        setupImages();
        //setup the backgroundSquares containing the graphics
        setupSquares();
    }

    //Paints the components in game
    public void paintComponent(Graphics gr){

        if(isFirst) {
            define();   //define the squarearray
            isFirst = false;
        }
       // gr.clearRect(0,0, getWidth(), getHeight());
        draw(gr);
    }


    @Override
    public void run() {

        while(true){
            if(!isFirst){
                move(); //do something to change the game
            }
            repaint();  // repaint the graphics in the gameframe.
            try{
                thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * Move should determine how the graphics should be updated.
     * */
    public void move(){


    }

    /**
     * Funtion for setting upp all of the backgroundSquares in game and placing them out
     * */
    public void setupSquares(){
        backgroundSquares = new Square[columnCount][rowCount];    //Setup the square array
        airSquares = new Square[columnCount][rowCount];    //Setup the square array
        background = new Character[columnCount][rowCount];
        air = new Character[columnCount][rowCount];

        for (int y = 0; y < backgroundSquares[0].length ; y++) {
            for (int x = 0; x < backgroundSquares.length ; x++) {

                //placing the grid in the center of the screen
                backgroundSquares[x][y] = new Square( ((width/2) -
                        ((columnCount*squareSize)/2) + (x * squareSize)),
                        y*squareSize, squareSize, squareSize, 0);

                //setup the airSquares.
                airSquares[x][y] = new Square( ((width/2) -
                        ((columnCount*squareSize)/2) + (x * squareSize)),
                        y*squareSize, squareSize, squareSize, 0);


                //Setup the maps with integers 1,0
                background[x][y] = backgroundString.charAt(((y*columnCount) + x));
                air[x][y] = airString.charAt(((y*columnCount) + x));

            }
        }
    }

    /**
     *
     * Funciton for drawing out the graphics for each of the Squares
     *
     * */
    public void draw(Graphics gr) {

            for (int y = 0; y < backgroundSquares[0].length; y++) {
                for (int x = 0; x < backgroundSquares.length; x++) {
                    backgroundSquares[x][y].drawBackground(gr, x, y);
                    airSquares[x][y].drawGraphics(gr, x, y);
                }
            }
        }
}
