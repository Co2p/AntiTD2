package tower;

import helpers.Position;
import tile.RoadTile;
import tile.Tile;
import trooper.Trooper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

/**
 * Superclass for any tower class
 */
public class Tower implements Observer {
    protected int damage;
    protected int range;
    protected Position pos;
    protected ArrayList<Position> neighbours = new ArrayList<>(); // a array of ALL tiles within the range of the tower
    protected ArrayList<Trooper> targets;
    public int observedTiles=0; // number of tiles with observers attached by the tower

    /**
     * Super tower constructor, called by the sub-tower classes
     * @param damage tower damage
     * @param range tower range
     */
    public Tower(int damage, int range, Hashtable<Position, Tile> map_hashTable, Position pos){
        this.damage=damage;
        this.range=range;    //Ska vi ens ha range?
        targets = new ArrayList<>();
        setPos(pos);
        addNeighbours();
        for ( int i = 0; i < neighbours.size(); i++) {
            Tile road = map_hashTable.get(neighbours.get(i));
            if (RoadTile.class.isInstance(road)) {
                observedTiles++;
                road.addObserver(this);
            }
        }
    }

    /**
     * fires the tower, override by subclasses
     */
    public void fire(){
        targets.clear();
    }

    /**
     * Get tower range
     * @return tower range
     */
    public int getRange() {
        return range;
    }

    /**
     * Position of the tower
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
     * @param pos the tile position that will be added
     * @return the position
     */
    private Position addNeighbour(Position pos) {
        if (pos.inRange()) {
            neighbours.add(pos);
        }
        return pos;
    }

    /**
     * Gets the total of the tiles in the tower range (including non RoadTiles), used in tests
     * @return number of tiles innrange
     */
    public int getNrofNeighbours() {
        return neighbours.size();
    }

    /**
     * Number of tiles with observers attached (RoadTiles in range)
     * @return RoadTiles in the tower range
     */
    public int getObservedTiles() {
        return observedTiles;
    }

    /**
     * Called by the Observed Observable RoadTile in landOn
     * @param o the Observable
     * @param arg the Trooper
     */
    @Override
    public void update(Observable o, Object arg) {
        //TODO Alla torn skjuter inte om de bortkommenterade körs ist för addall
        //ArrayList<Trooper> tmp = (ArrayList<Trooper>) arg;
        //targets.addAll(tmp);
        //tmp.clear();
        targets.addAll((ArrayList<Trooper>) arg);
    }

    /**
     * Returns if the requested position is in range
     * @param position requested position
     * @return if the position is in range
     */
    public boolean inRange(Position position) {
        return neighbours.contains(position);
    }

}
