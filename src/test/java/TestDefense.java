import helpers.Position;
import org.junit.Test;
import tile.RoadTile;
import tile.Tile;
import tile.TowerTile;
import tower.Defense;
import java.util.Hashtable;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by Alexander Nyström(dv15anm) on 05/12/2016.
 */
public class TestDefense {

    @Test
    public void testSetUpDefense() {
        Hashtable<Position, Tile> map = new Hashtable<>();
        Position pos = new Position(4,5);
        TowerTile t = new TowerTile(pos);
        map.put(pos,t);
        Position p = new Position(2,9);
        map.put(p,new RoadTile(p));
        Defense def = new Defense(map,100);
        assertEquals(1,def.getTowerMap().size());
        assertEquals(t,def.getTowerMap().get(0));
    }

    @Test
    public void testCreateTower() {
        Hashtable<Position,Tile> map = new Hashtable<>();
        Position pos = new Position(4,5);
        TowerTile t = new TowerTile(pos);
        map.put(pos,t);
        Position p = new Position(2,9);
        map.put(p,new RoadTile(p));
        p = new Position(3,7);
        map.put(p,new TowerTile(p));
        Defense def = new Defense(map,100);
        def.createTower();
        assertEquals(1,def.getTowerCount());
        def.createTower();
        assertEquals(2,def.getTowerCount());
    }
}
