package Game;

import trooper.Army;
import java.awt.*;

/**
 * Created by Simon on 2016-12-01.
 */
public class GameContainer {


    /**
     *
     * Column and Row count should be dynamic. depending on the map size.
     *
     * */
    public static int columnCount = 12;   //define number of columns
    public static int rowCount = 8;       //define numer of rows
    public static int squareSize = 50;   //change this to be dynamic (square size)

    private Square[][] backgroundSquares; //Array containg the ground backgroundSquares
    public static Square[][] airSquares; //Array containing the indexBlank backgroundSquares.

    public GameContainer(){
    }

    /**
     * Defines the actual game map what image should be at what position by
     * creating square arrays for the air layer and background layer.
     */
    public void define(){

        //Setup the squares (Game.Game pane) of the gui.
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

    /**
     * Paints the ground layer and air layer on the graphics
     * @param gr the graphics where the game will be drawn
     */
    public void draw(Graphics gr){

        for (int y = 0; y < backgroundSquares[0].length; y++) {
            for (int x = 0; x < backgroundSquares.length; x++) {
                backgroundSquares[x][y].drawBackground(gr, x, y);
                airSquares[x][y].drawGraphics(gr, x, y);
            }
        }
    }

    /**
     *
     * @param columnCount The number of columns in the map defined in the xml
     */
    public static void setColumnCount(int columnCount) {
        GameContainer.columnCount = columnCount;
    }

    /**
     *
     * @param rowCount The number of rows in the map defined in the xml
     */
    public static void setRowCount(int rowCount) {
        GameContainer.rowCount = rowCount;
    }


    public Square[][] getAirSquares() {
        return airSquares;
    }
}
