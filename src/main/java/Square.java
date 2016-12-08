package main.java;

import main.java.helpers.Translator;

import java.awt.*;
import main.java.helpers.Position;
import main.java.tile.Tile;
import main.java.tile.RoadTile;


/**
 * Created by Simon on 2016-11-30.
 *
 * this class defines how a square on the gameboard looks for the player.
 */


public class Square extends Rectangle {

    public int id;
    public Tile tile;
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
            tile = new RoadTile(p);
        }else if((Game.background[indexX][indexY])
                == Translator.squareGoal) {
            gr.drawImage(Game.square_material[Translator.squareRoad]
                    , x, y, width, height, null);
            tile = new RoadTile(p, "goal");
        }else if((Game.background[indexX][indexY])
                == Translator.squareStart) {
            gr.drawImage(Game.square_material[Translator.squareRoad]
                    , x, y, width, height, null);
            tile = new RoadTile(p, "start");
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
