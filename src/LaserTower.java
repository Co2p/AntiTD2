import java.util.Observable;

/**
 * Created by gordon on 2016-11-30.
 */
public class LaserTower extends Tower {

    public LaserTower(TowerTile tile, Tile[] roadtiles, Position pos) {
        super(100, 2, pos);
        tile.setTower(this);
        for (Tile road: roadtiles) {
            road.addObserver(this);
        }
    }

    @Override
    public void fire() {
        if (!super.targets.contains(focusTarget)) {
            focusTarget = targets.get(targets.size() - 1);
        }
        focusTarget.receiveDamage(damage);
    }
}
