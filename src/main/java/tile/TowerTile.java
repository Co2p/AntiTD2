package main.java.tile;

import main.java.helpers.Position;
import main.java.tower.Tower;

public class TowerTile extends Tile {
    private Tower tower;


    /**
     * Constructor to create a Towertile.
     * @param p The main.java.main.java.helpers.Position that the main.java.main.java.tile will be placed on.
     */
    public TowerTile(Position p){
        super(p);
    }


    /**
     * Return the main.java.main.java.tower on the towertile.
     * @return main.java.main.java.tower
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * Add a main.java.main.java.tower on the towertile.
     * @param tower the main.java.main.java.tower to be placed.
     */
    public void setTower(Tower tower) {
        this.tower = tower;
    }
}
