package tile;

import helpers.Position;
import tower.Tower;

/**
 * A tile for towers
 */
public class TowerTile extends Tile {
    /**
     * Constructor to create a TowerTile.
     * @param p The mainers.Position that the Game.main will be placed on.
     */
    public TowerTile(Position p){
        super(p);
    }
}
