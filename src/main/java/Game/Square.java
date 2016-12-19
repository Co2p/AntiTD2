package Game;

import helpers.Translator;

import java.awt.*;
import helpers.Position;


/**
 * Created by Simon on 2016-11-30.
 *
 * this class defines how a square on the gameboard looks for the player.
 */


public class Square extends Rectangle {

    public int id;
    public Position p;

    /**
     *
     * @param x index x
     * @param y index y
     * @param width width of the square
     * @param height height of the square
     * @param id id of the square
     */
    public Square(int x, int y, int width, int height, int id){
        setBounds(x,y,width,height);
        this.id = id;
        p = new Position(x,y);
    }

    /**
     * Draws the background squares. indexX and indexY tells the square to look
     * in the  arrays of square_material i Game class.
     * Square_material has index's that defines which material should be drawn
     *
     * @param gr Graphics element
     * @param indexX tileIndex X
     * @param indexY tileindex Y
     */
    public void drawBackground(Graphics gr, int indexX, int indexY){

        if((Game.background[indexX][indexY])
                == Translator.squareGrass ){
            gr.drawImage(Game.square_material[Translator.squareGrass]
                    , x,y, width, height, null);
        }else if((Game.background[indexX][indexY])
                == Translator.squareRoad){
            gr.drawImage(Game.square_material[Translator.squareRoad]
                    , x,y, width, height, null);
        }else if((Game.background[indexX][indexY])
                == Translator.squareRoad2){
            gr.drawImage(Game.square_material[Translator.squareRoad2]
                    , x,y, width, height, null);
        }else if((Game.background[indexX][indexY])
                == Translator.squareRoad3){
            gr.drawImage(Game.square_material[Translator.squareRoad3]
                    , x,y, width, height, null);
        }else if((Game.background[indexX][indexY])
                == Translator.indexGoal) {
            gr.drawImage(Game.square_material[Translator.squareRoad]
                    , x, y, width, height, null);
        }else if((Game.background[indexX][indexY])
                == Translator.indexStart) {
            gr.drawImage(Game.square_material[Translator.squareRoad]
                    , x, y, width, height, null);
        }else if ((Game.background[indexX][indexY])
                == Translator.squareTowerZone) {
            gr.drawImage(Game.square_material[Translator.squareGrass]
                    , x, y, width, height, null);
        }
    }

    /**
     * Draw the graphics defined in the air[][] from Game.
     * Goal image, blank squares and start image
     *
     * @param gr Graphics element
     * @param indexX index X for the air-map
     * @param indexY index Y for the air-map
     */
    public void drawGraphics(Graphics gr, int indexX, int indexY){
        gr.drawImage(Game.square_air[(Game.air[indexX][indexY])]
                , x,y, width, height, null);

    }

    /**
     * Returns a specific square position
     * @return p
     */
    public Position getSquarePosition() {
        return p;
    }

}
