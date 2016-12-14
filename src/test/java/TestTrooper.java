import helpers.Direction;
import helpers.Position;
import org.junit.Test;
import tile.RoadTile;
import tile.Tile;
import trooper.ArmoredTrooper;
import trooper.PitifulTrooper;
import trooper.Trooper;

import java.util.Hashtable;

import static junit.framework.TestCase.*;

public class TestTrooper {
    Hashtable<Position, Tile> map = new Hashtable<>();
    PitifulTrooper pt;


    @Test
    public void TestCreateTrooper(){
        Trooper t = new Trooper();
        assertEquals(1000, t.getHealth());
        assertEquals(25, t.getstepDelay());
    }

    @Test
    public void TestCreateTrooperOnlyHp(){
        Trooper t = new Trooper(10);
        assertEquals(10, t.getHealth());
        assertEquals(25, t.getstepDelay());
    }

    @Test
    public void TestRecieveHealth(){
        Trooper t = new Trooper();
        t.receiveDamage(4);
        assertEquals(996, t.getHealth());
        t.receiveHealth(2);
        assertEquals(998, t.getHealth());
        t.receiveHealth(4);
        assertEquals(1000, t.getHealth());
    }

    @Test
    public void TestDecrementHp(){
        Trooper t = new Trooper();
        t.receiveDamage(2);
        assertEquals(t.getHealth(), 998);
    }

    @Test
    public void TestSetDirection(){
        Trooper t = new Trooper();
        t.setDirection(Direction.SOUTH);
        assertEquals(Direction.SOUTH, t.getDirection());
    }

    @Test
    public void TestSetSpeed(){
        Trooper t = new Trooper();
        t.setstepDelay(2);
        assertEquals(2, t.getstepDelay());
    }

    @Test
    public void TestTakingFatalDamage(){
        Trooper t = new Trooper();
        t.receiveDamage(1001);
        t.receiveDamage(1001);
        assertTrue(t.isDead());
    }

    @Test
    public void TestIsZombieWithFullHealth(){
        Trooper t = new Trooper();
        t.receiveDamage(11);
        assertFalse(t.isDead());
        assertEquals(989,t.getHealth());
    }

    //TODO redo the tests
    @Test
    public void TestArmorSaveHuman(){
        Trooper t = new Trooper();
        t.receiveDamage(11);
        assertFalse(t.isDead());
        assertEquals(989,t.getHealth());
    }

//TODO removed setArmor that does not exist anymore
    @Test
    public void TestIsDeadWithArmor(){
        Trooper t = new ArmoredTrooper(10, 2);
        t.receiveDamage(12);
        assertTrue(t.hasTurned());
        t.receiveDamage(10);
        assertEquals(0, t.getHealth());
        t.receiveDamage(4);
        assertTrue(t.isDead());
    }

    @Test
    public void TestSetHp(){
        Trooper t = new Trooper();
        t.setHealth(-2);
        assertEquals(t.getHealth(), -2);
    }

    @Test
    public void TestTrooperPositionNull(){
        Trooper t = new Trooper(10);
        assertNull(t.getPosition());
    }

    private void makeMap() {
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


    @Test
    public void TestTrooperGetPossibleMoves() {
        pt = new PitifulTrooper();
        pt.setPosition(new Position(0,1));
        makeMap();
        Hashtable<Position, RoadTile> pm = pt.getPossibleMoves(map);
        assertEquals(1, pm.size());
    }

    @Test
    public void testMove() {
        pt = new PitifulTrooper();
        pt.setPosition(new Position(0,1));
        makeMap();
        RoadTile rt = pt.move(map, Direction.NORTH);
        assertEquals(new Position(0,1), rt.getPosition());
        rt = pt.move(map, Direction.NORTH);
        assertEquals(new Position(1,1), rt.getPosition());
        rt = pt.move(map, Direction.NORTH);
        assertEquals(new Position(2,1), rt.getPosition());
        rt = pt.move(map, Direction.NORTH);
        assertEquals(new Position(2,1), rt.getPosition());
    }
}
