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
        super(12, 2, map_hashTable, pos);
    }

    /**
     * Prioritizes shooting the focused trooper, otherwise gets the trooper that
     * entered the target list last. Hopefully at the back of the line.
     */
    @Override
    public void fire() {
        setFocusedTarget();
        if (focusTarget != null && super.targets.contains(focusTarget) &&
                !focusTarget.isDead()) {
            //TODO REMOVE SOUT!
            System.out.println("Fired!");
            focusTarget.receiveDamage(damage);
        }
        else{
            focusTarget = null;
        }
        super.fire();
    }

    public void setFocusedTarget() {
        if(!targets.isEmpty() && focusTarget == null){
            focusTarget = targets.get(targets.size() - 1);
        }
    }

    public Trooper getFocusTarget() {
        return focusTarget;
    }
}
