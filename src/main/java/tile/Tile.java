package tile;

import java.util.Observable;
import helpers.Position;

/**
 * A logical superclass Tile that contains a position,
 * is extended by all Tiles
 */
public abstract class Tile extends Observable {
    private Position position;

    /**
     * Constructor to create a Tile, this will only be used in subclasses.
     * @param p the postion of the Game.main on the board
     */
    public Tile(Position p){
        this.position = p;
    }

    /**
     * @return the position of the Tile as a Position object
     */
    public Position getPosition() {
        return position;
    }
}
