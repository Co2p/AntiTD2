import javax.swing.*;
import java.awt.*;

/**
 * Created by Simon on 2016-11-30.
 *
 * Contains the game board. extends the JPanel
 */
public class GamePanel extends JPanel implements Runnable {

    private int columnCount = 12;   //define number of columns
    private int rowCount = 8;       //define numer of rows
    private int squareSize = 50;   //change this to be dynamic (square size)
    public static int width, height;

    public Square[][] squares;      //Array containg the squares

    public Thread thread = new Thread(this);//thread that runs the game
   // public static GameBoard gameBoard;    //GameBoard is the game JPanel
    public static boolean isFirst = true; //first time the game opens = true


    public GamePanel(){
        thread.start();
    }

    public void define(){

        width = getWidth();
        height = getHeight();

        setupSquares();
    }

    //Paints the components in game
    public void paintComponent(Graphics gr){

        if(isFirst) {
            define();   //define the squarearray
            isFirst = false;
        }

        gr.clearRect(0,0, getWidth(), getHeight());
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
                thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void move(){

    }

    /**
     * Funtion for setting upp all of the squares in game and placing them out
     * */
    public void setupSquares(){
        squares = new Square[columnCount][rowCount];    //Setup the square array
        for (int y = 0; y <squares[0].length ; y++) {
            for (int x = 0; x <squares.length ; x++) {

                //placing the grid in the center of the screen
                squares[x][y] = new Square( ((width/2) -
                        ((columnCount*squareSize)/2) + (x * squareSize)),
                        y*squareSize, squareSize, squareSize, 0);
            }
        }
    }

    /**
     *
     * Funciton for drawing out the graphics for each of the squares
     *
     * */
    public void draw(Graphics gr){
        for (int y = 0; y < squares[0].length ; y++) {
            for (int x = 0; x <squares.length ; x++) {
                squares[x][y].draw(gr);
            }
        }
    }
}
