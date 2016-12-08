package tile;

import helpers.Position;
import tower.Tower;

public class TowerTile extends Tile {
    private Tower tower;


    /**
     * Constructor to create a Towertile.
     * @param p The mainers.Position that the main will be placed on.
     */
    public TowerTile(Position p){
        super(p);
    }


    /**
     * Return the mainr on the towertile.
     * @return mainr
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * Add a mainr on the towertile.
     * @param tower the mainr to be placed.
     */
    public void setTower(Tower tower) {
        this.tower = tower;
    }
}
