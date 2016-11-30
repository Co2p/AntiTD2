import java.util.Observable;

/**
 * Created by gordon on 2016-11-30.
 */
public class LaserTower extends Tower {

    public LaserTower(TowerTile tile, Tile[] roadtiles) {
        super(100, 2);
        tile.setTower(this);
    }

    @Override
    public int fire() {
        return damage;
    }
}
