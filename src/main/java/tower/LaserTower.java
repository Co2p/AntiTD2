package tower;

import helpers.Position;
import tile.Tile;
import tile.TowerTile;
import trooper.Trooper;

import java.util.Hashtable;

/**
 * A subclass of Tower, shoots one trooper at a time
 * Created by gordon on 2016-11-30.
 */
public class LaserTower extends Tower {
    private Trooper focusTarget;

    public LaserTower(Hashtable<Position, Tile> map_hashTable, Position pos) {
        super(25, 2, map_hashTable, pos);
    }

    /**
     * Prioritizes shooting the focused trooper, otherwise gets the trooper that
     * entered the target list last. Hopefully at the back of the line.
     */
    @Override
    public void fire() {
        System.out.println("Focustarget = " + focusTarget);
        if (focusTarget != null && !super.targets.contains(focusTarget)) {
            focusTarget = targets.get(targets.size() - 1);
        }
        if (focusTarget != null){
            focusTarget.receiveDamage(damage);
        }
    }

    @Override
    public void setFocusedTarget(Trooper focusTarget) {
        this.focusTarget = focusTarget;
    }

    public Trooper getFocusTarget() {
        return focusTarget;
    }
}
