package tower;

import helpers.Position;
import trooper.Trooper;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
<<<<<<< HEAD
 * Superclass for any mainr class
=======
 * Superclass for any tower class
>>>>>>> master
 */
public class Tower implements Observer {
    protected int damage;
    protected int range;
    protected Position pos;
    protected ArrayList<Position> neighbours = new ArrayList<>();
    protected ArrayList<Trooper> targets;

    /**
<<<<<<< HEAD
     * Super mainr constructor, called by the sub-mainr classes
     * @param damage mainr damage
     * @param range mainr range
=======
     * Super tower constructor, called by the sub-tower classes
     * @param damage tower damage
     * @param range tower range
>>>>>>> master
     */
    public Tower(int damage, int range, Position pos){
        this.damage=damage;
        this.range=range;    //Ska vi ens ha range?
        targets = new ArrayList<>();
        setPos(pos);
        addNeighbours();
    }

    /**
<<<<<<< HEAD
     * fires the mainr, override by subclasses
     * @return mainr damage
=======
     * fires the tower, override by subclasses
     * @return tower damage
>>>>>>> master
     */
    public void fire(){}

    /**
<<<<<<< HEAD
     * Get mainr range
     * @return mainr range
=======
     * Get tower range
     * @return tower range
>>>>>>> master
     */
    public int getRange() {
        return range;
    }

    /**
<<<<<<< HEAD
     * mainers.Position of the mainr
     * @return mainr position
=======
     * Position of the tower
     * @return tower position
>>>>>>> master
     */
    public Position getPos() {
        return pos;
    }

    /**
<<<<<<< HEAD
     * Set the mainr position
     * @param pos a new mainr position
=======
     * Set the tower position
     * @param pos a new tower position
>>>>>>> master
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     * Adds neighbouring tiles to the neighbours array
     *
     */
    public void addNeighbours() {
        Position NPos = pos;
        Position SPos = pos;
        Position WPos = pos;
        Position EPos = pos;
        for(int step = 1; step <= range; step++){
            NPos = addNeighbour(NPos.getPosToNorth());
            SPos = addNeighbour(SPos.getPosToSouth());
            WPos = addNeighbour(WPos.getPosToWest());
            EPos = addNeighbour(EPos.getPosToEast());
            Position SWPos = NPos;
            Position SEPos = NPos;
            Position NWPos = SPos;
            Position NEPos = SPos;
            for (int j = 0; j < step - 1; j++) {
                SWPos = addNeighbour(SWPos.getPosToSouthWest());
                SEPos = addNeighbour(SEPos.getPosToSouthEast());
                NWPos = addNeighbour(NWPos.getPosToNorthWest());
                NEPos = addNeighbour(NEPos.getPosToNorthEast());
            }
        }
    }

    /**
     * Adds the position to the neighbours list if it is in range
     * @param pos the position that will be added
     * @return the position
     */
    private Position addNeighbour(Position pos) {
        if (!pos.outOfRange()) {
            neighbours.add(pos);
        }
        return pos;
    }

    public int getNrofNeighbours() {
        return neighbours.size();
    }

    @Override
    public void update(Observable o, Object arg) {
        targets.add((Trooper) arg);
    }

    public boolean inRange(Position position) {
        return neighbours.contains(position);
    }

}
