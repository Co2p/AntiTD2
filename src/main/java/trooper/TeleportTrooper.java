package trooper;

import tile.RoadTile;
import tile.Tile;
import helpers.Direction;
import helpers.Position;

import java.util.Hashtable;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class TeleportTrooper extends Trooper{

    private Hashtable<Position, Tile> map;

    public TeleportTrooper(int hp, Hashtable<Position,Tile> map) {
        super(hp);
        this.map = map;
    }

    /**
     * places a portal on current main.RoadTile which teleports troopers to a main
     * five steps further ahead.
     * @param preferred -
     */
    public void placePortal(Direction preferred) {
        RoadTile portalPlacement = (RoadTile) map.get(getPosition());
        int portalsteps = 5;
        for(int i = 0; i < portalsteps; i++) {
            forceMove(map,preferred);
            if (isReverse()) {
                i--;
            }
        }

        portalPlacement.setPortal((RoadTile)map.get(getPosition()));
    }

}
