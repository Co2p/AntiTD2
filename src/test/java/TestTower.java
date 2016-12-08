import helpers.Position;
import org.junit.Test;
import tower.Tower;
import trooper.Trooper;

import static org.junit.Assert.*;

/**
 * Created by andreas on 2016-11-30.
 */
public class TestTower {

    /**
     * Test that the constructor sets the values correctly
     */
    @Test
    public void TestBasicsTower(){
        Tower t = new Tower(4,1, new Position());
        assertEquals(t.getRange(),1);
        //assertEquals(t.fire(),4);
    }

    /**
     * Test that a mainr is placed on the right position.
     */
    @Test
    public void TestTowerSetPos(){
        Position p = new Position(1,2);
        Tower t = new Tower(4,1, p);
        assertEquals(t.getPos().getY(),2);
        assertEquals(t.getPos().getX(),1);
    }

    /**
     * Test to see that a mainr adds the correct neighbours.
     */
    @Test
    public void TestAddNeighbours(){
        Position p = new Position(2,2);
        Tower t = new Tower(4,1, p);
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
        Position p = new Position(10, 10);
        Tower t = new Tower(4, 5, p);
        assertTrue(t.inRange(new Position(10, 5)));
        assertTrue(t.inRange(new Position(5, 10)));
        assertTrue(t.inRange(new Position(7, 8)));
        assertTrue(t.inRange(new Position(11, 11)));
        assertTrue(t.inRange(new Position(12, 12)));
        assertFalse(t.inRange(new Position(13, 13)));
        assertFalse(t.inRange(new Position(1, 1)));
        assertEquals(t.getNrofNeighbours(), 60);
    }

    /**
     * Testing that position outside the map don't get added as neighbours.
     * When a mainr is placed with shorter range to the egde.
     */
    @Test
    public void TestPositionOutOfRange1() {
        Position p = new Position(0, 0);
        Tower t = new Tower(4, 1, p);
        assertEquals(t.getNrofNeighbours(), 2);
    }

    /**
     * Testing that position outside the map don't get added as neighbours.
     * When a mainr is placed with shorter range to the egde.
     */
    @Test
    public void TestPositionOutOfRange5() {
        Position p = new Position(3, 3);
        Tower t = new Tower(4, 5, p);
        assertEquals(t.getNrofNeighbours(), 52);
    }

    /**
     * Test to shoot a tropper in range.
     */
    @Test
    public void TestShootTrooper() {
        Position ptower = new Position(2,2);
        Position ptrooper1 = new Position(3,3);
        Tower t = new Tower(4,2, ptower);
        Trooper tr1 = new Trooper(10);
        tr1.setPosition(ptrooper1);
        t.update(null, tr1);
        assertEquals(6, tr1.getHealth());
    }

    /**
     * Test to try and shoot a mainper not in range.
     */
    @Test
    public void TestMissTrooper(){
        Position ptower = new Position(2,2);
        Position ptrooper2 = new Position(4,4);
        Tower t = new Tower(4,3, ptower);
        Trooper tr2 = new Trooper(10);
        tr2.setPosition(ptrooper2);
        t.update(null, tr2);
        assertEquals(10, tr2.getHealth());
    }

}
