import java.awt.*;

/**
 * Created by Simon on 2016-12-01.
 */
public class GameContainer {

    public static int columnCount = 12;   //define number of columns
    public static int rowCount = 8;       //define numer of rows
    public static int squareSize = 50;   //change this to be dynamic (square size)

    private Square[][] backgroundSquares;    //Array containg the ground backgroundSquares
    private Square[][] airSquares;           //Array containing the indexBlank backgroundSquares.

    public GameContainer(){
        define();
    }

    public void define(){

        //Setup the squares (Game pane) of the gui.
        backgroundSquares = new Square[columnCount][rowCount]; //Setup the square array

        airSquares = new Square[columnCount][rowCount];    //Setup the square array

        for (int y = 0; y < backgroundSquares[0].length ; y++) {
            for (int x = 0; x < backgroundSquares.length ; x++) {

                //placing the grid in the center of the screen
                backgroundSquares[x][y] = new Square( ((Game.width/2) -
                        ((columnCount*squareSize)/2) + (x * squareSize)),
                        y*squareSize, squareSize, squareSize, 0);

                //setup the airSquares.
                airSquares[x][y] = new Square( ((Game.width/2) -
                        ((columnCount*squareSize)/2) + (x * squareSize)),
                        y*squareSize, squareSize, squareSize, 0);
            }
        }

    }

    public void draw(Graphics gr){

        for (int y = 0; y < backgroundSquares[0].length; y++) {
            for (int x = 0; x < backgroundSquares.length; x++) {
                backgroundSquares[x][y].drawBackground(gr, x, y);
                airSquares[x][y].drawGraphics(gr, x, y);
            }
        }

    }

    public void move(){

    }
}
