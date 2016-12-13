package tile;

import java.util.Observable;
import helpers.Position;

public abstract class Tile extends Observable {
    private Position position;
    private Boolean occupied = false;

    /**
     * Constructor to create a Game.main.Tile, this will only be used in subclasses.
     * @param p the postion of the Game.main on the board
     */
    public Tile(Position p){
        this.position = p;
    }

    /**
     * Getter for isOccupied
     * @return true if occupied, false if not.
     */
    public boolean isOccupied(){
        return occupied;
    }

    /**
     * Set occupied.
     * @param b the value to set occupied to,
     */
    private void setOccupied(boolean b){
        occupied = b;
    }

    /**
     * @return the position of the tile as a Position object
     */
    public Position getPosition() {
        return position;
    }
}
