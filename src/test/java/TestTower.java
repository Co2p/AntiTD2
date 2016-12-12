import helpers.Position;
import org.junit.Before;
import org.junit.Test;
import tile.RoadTile;
import tile.Tile;
import tower.LaserTower;
import tower.Tower;
import trooper.Trooper;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.Assert.*;

/**
 * Created by andreas on 2016-11-30.
 */
public class TestTower {

    Hashtable<Position, Tile> map = new Hashtable<>();

    @Before
    public void makeMap() {
        Position p1 = new Position(0,1);
        RoadTile rt1 = new RoadTile(p1 );
        Position p2 = new Position(1,1);
        RoadTile rt2 = new RoadTile(p2);
        Position p3 = new Position(2,1);
        RoadTile rt3 = new RoadTile(p3 );
        Position p4 = new Position(3,2);
        RoadTile rt4 = new RoadTile(p4, "goal");
        map.put(p1, rt1);
        map.put(p2, rt2);
        map.put(p3, rt3);
        map.put(p4, rt4);
    }

    /**
     * Test that the constructor sets the values correctly
     */
    @Test
    public void TestBasicsTower(){
        Tower t = new Tower(4,1, map, new Position());
        assertEquals(t.getRange(),1);
        //assertEquals(t.fire(),4);
    }

    /**
     * Test that a mainr is placed on the right position.
     */
    @Test
    public void TestTowerSetPos(){
        Position p = new Position(1,2);
        Tower t = new Tower(4,1, map, p);
        assertEquals(t.getPos().getY(),2);
        assertEquals(t.getPos().getX(),1);
    }

    /**
     * Test to see that a mainr adds the correct neighbours.
     */
    @Test
    public void TestAddNeighbours(){
        Position p = new Position(2,2);
        Tower t = new Tower(4,1, map, p);
        Position ptrue = new Position(1,2);
        Position pfalse = new Position(5,5);
        assertTrue(t.inRange(ptrue));
        assertFalse(t.inRange(pfalse));
    }

    /**
     * Test to see that a mainr adds all neigbours in range.
     */
    @Test
    public void TestAddNeighboursRange5() {
        Position p = new Position(6, 6);
        Tower t = new Tower(4, 5, map, p);
        assertEquals(60, t.getNrofNeighbours());
    }

    /**
     * Testing that position outside the map don't get added as neighbours.
     * When a mainr is placed with shorter range to the egde.
     */
    @Test
    public void TestPositionOutOfRange1() {
        Position p = new Position(0, 0);
        Tower t = new Tower(4, 1, map, p);
        assertEquals(2, t.getNrofNeighbours());
    }

    /**
     * Testing that position outside the map don't get added as neighbours.
     * When a mainr is placed with shorter range to the egde.
     */
    @Test
    public void TestPositionOutOfRange5() {
        Position p = new Position(3, 3);
        Tower t = new Tower(4, 5, map, p);
        assertEquals(52, t.getNrofNeighbours());
    }

    /**
     * Test to try and shoot a mainper not in range.
     */
    @Test
    public void TestMissTrooper(){
        Position ptower = new Position(2,2);
        Position ptrooper2 = new Position(4,4);
        Tower t = new Tower(4,3, map, ptower);
        Trooper tr2 = new Trooper(10);
        ArrayList<Trooper> trp = new ArrayList<>();
        trp.add(tr2);
        tr2.setPosition(ptrooper2);
        t.update(null, trp);
        assertEquals(10, tr2.getHealth());
    }

}
