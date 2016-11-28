/**
 * Created by andreas on 2016-11-28.
 */

public abstract class Tile {
    private Position position;
    private Boolean occupied = false;

    /**
     * Constructor to create a Tile, this will only be used in subclasses.
     * @param p
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
}
