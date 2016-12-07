package tower;

import helpers.Position;
import trooper.Trooper;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Superclass for any tower class
 */
public class Tower implements Observer {
    protected int damage;
    protected int range;
    protected Position pos;
    protected ArrayList<Position> neighbours = new ArrayList<>();
    protected ArrayList<Trooper> targets;
    protected Trooper focusTarget;

    /**
     * Super tower constructor, called by the sub-tower classes
     * @param damage tower damage
     * @param range tower range
     */
    public Tower(int damage, int range, Position pos){
        this.damage=damage;
        this.range=range;    //Ska vi ens ha range?
        targets = new ArrayList<>();
        setPos(pos);
        addNeighbours();
    }

    /**
     * fires the tower, override by subclasses
     * @return tower damage
     */
    public void fire(){}

    /**
     * Get tower range
     * @return tower range
     */
    public int getRange() {
        return range;
    }

    /**
     * helpers.Position of the tower
     * @return tower position
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Set the tower position
     * @param pos a new tower position
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
        //North South West East * range
            //for each step in range:
                //North step range - 1:
                    //SW
                    //SE
                //South step range - 1:
                    //NW
                    //NE

    }

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
