package main.java.tower;

import main.java.helpers.Position;
import main.java.tile.Tile;
import main.java.tile.TowerTile;
import main.java.trooper.Trooper;

/**
 * A subclass of Tower, shoots one trooper at a time
 * Created by gordon on 2016-11-30.
 */
public class LaserTower extends Tower {
    private Trooper focusTarget;

    public LaserTower(TowerTile tile, Tile[] roadtiles, Position pos) {
        super(100, 2, pos);
        tile.setTower(this);
        for (Tile road: roadtiles) {
            road.addObserver(this);
        }
    }

    /**
     * Prioritizes shooting the focused trooper, otherwise gets the trooper that
     * entered the target list last. Hopefully at the back of the line.
     */
    @Override
    public void fire() {
        if (!super.targets.contains(focusTarget)) {
            focusTarget = targets.get(targets.size() - 1);
        }
        focusTarget.receiveDamage(damage);
    }
}
