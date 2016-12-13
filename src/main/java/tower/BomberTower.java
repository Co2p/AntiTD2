package tower;

import helpers.Position;
import tile.Tile;
import trooper.Trooper;

import java.util.Hashtable;

/**
 * A tower that bombs tiles in it's range
 * Created by gordon on 2016-12-09.
 */
public class BomberTower extends Tower {

    /**
     * Constructor for a bomber tower
     * @param map_hashTable the active map
     * @param pos a position for the tower
     */
    public BomberTower(Hashtable<Position, Tile> map_hashTable, Position pos) {
        super(100, 1, map_hashTable, pos);
    }

    /**
     * Damages everyone in the target list
     */
    @Override
    public void fire() {
        for (Trooper t: targets) {
            t.receiveDamage(damage);
        }
        super.fire();
    }

}
