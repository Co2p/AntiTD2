package trooper;

import Game.GameContainer;
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
     * places a portal on current Game.main.RoadTile which teleports troopers to a Game.main
     * five steps further ahead.
     * @param preferred -
     */
    public void placePortal(Direction preferred) {
        if (hasTeleport) {
            hasTeleport = false;
            RoadTile portalPlacement = (RoadTile) map.get(getPosition());
            //setSemiStep(0);
            RoadTile road;
            int portalsteps = 5;
            for (int i = 0; i < portalsteps; i++) {
                road = forceMove(map, preferred);
                pushToBackTrack(road.getPosition(),getOppociteDirection(getDirection()));
                if (isReverse()) {
                    i--;
                }
            }
            setSemiStep(0);
            //TODO FIX THIS MOVE!
            setGraphicPosition(GameContainer.airSquares[getPosition().getX()][getPosition().getY()].getSquarePosition());
            portalPlacement.setPortal((RoadTile) map.get(getPosition()),map);
        }
    }

    public boolean hasTeleport() {
        return hasTeleport;
    }
}
