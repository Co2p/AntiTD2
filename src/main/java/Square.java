

import helpers.Translator;

import java.awt.*;
import helpers.Position;
import tile.Tile;
import tile.RoadTile;


/**
 * Created by Simon on 2016-11-30.
 *
 * this class defines how a square on the gameboard looks for the player.
 */


public class Square extends Rectangle {

    public int id;
    public Position p;

    public Square(int x, int y, int width, int height, int id){
        setBounds(x,y,width,height);
        this.id = id;
        p = new Position(x,y);
    }

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

    public void drawGraphics(Graphics gr, int indexX, int indexY){
        gr.drawImage(Game.square_air[(Game.air[indexX][indexY])]
                , x,y, width, height, null);

    }

}
