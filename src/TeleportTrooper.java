import java.util.Hashtable;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class TeleportTrooper extends Trooper{

    private Hashtable<Position,Tile> map;

    public TeleportTrooper(int hp, Hashtable<Position,Tile> map) {
        super(hp);
        this.map = map;
    }

    public void placePortal(Direction preferred) {
        RoadTile portalPlacement = (RoadTile) map.get(getPosition());
        int portalsteps = 5;
        for(int i = 0; i < portalsteps; i++) {
            move(map,preferred);
        }
        portalPlacement.setPortal(map.get(getPosition()));
    }


}
