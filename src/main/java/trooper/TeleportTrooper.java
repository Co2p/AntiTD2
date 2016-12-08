package main.java.trooper;

import main.java.tile.RoadTile;
import main.java.tile.Tile;
import main.java.helpers.Direction;
import main.java.helpers.Position;

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
     * places a portal on current main.java.main.java.tile.RoadTile which teleports troopers to a main.java.main.java.tile
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
