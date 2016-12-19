package trooper;

import Game.GameContainer;
import tile.RoadTile;
import tile.Tile;
import helpers.Direction;
import helpers.Position;

import java.util.Hashtable;

/**
 * A TeleportTrooper is a trooper that is weak,
 * but can place a teleport on RoadTiles
 * that transports Troopers 5 Tiles forward.
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class TeleportTrooper extends Trooper{

    private Hashtable<Position, Tile> map;
    private boolean hasTeleport;

    /**
     * Constructs a teleporttrooper
     * @param hp trooper hp
     * @param map the active map
     */
    public TeleportTrooper(int hp, Hashtable<Position,Tile> map) {
        super(hp);
        this.map = map;
        hasTeleport = true;
    }

    /**
     * places a portal on current RoadTile which teleports troopers to a main
     * five steps further ahead.
     * @param preferred preferred direction to move in
     */
    public void placePortal(Direction preferred) {
        if (hasTeleport) {
            hasTeleport = false;
            boolean portalPlaced = false;
            RoadTile portalPlacement = null;
            int portalsteps = 5;
            RoadTile road;
            for (int i = 0; i < portalsteps; i++) {
                if (i ==0 && !isReverse() && !portalPlaced){
                    portalPlacement = (RoadTile) map.get(getPosition());
                    portalPlaced = true;
                } else if (isReverse()) {
                    if(!portalPlaced) {
                        portalPlacement = (RoadTile) map.get(getPosition());
                        portalPlaced = true;
                    }
                    i--;
                }
                road = forceMove(map, preferred);
                if (portalPlaced){
                    path.push(road.getPosition());
                    pathDirection.push(getOppociteDirection(getDirection()));
                }
            }
            setSemiStep(0);
            setGraphicPosition(
                    GameContainer.airSquares[getPosition().getX()]
                            [getPosition().getY()].getSquarePosition());
            portalPlacement.setPortal((RoadTile) map.get(getPosition()),map);
            portalPlacement.setExitDirection(getDirection());
        }
    }

    /**
     * @return is false if the TeleportTrooper already has placed a teleport
     */
    public boolean hasTeleport() {
        return hasTeleport;
    }
}
