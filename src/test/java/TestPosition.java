import static org.junit.Assert.*;

import helpers.Position;
import org.junit.Test;
import helpers.*;
/**
 * Created by andreas on 2016-05-20.
 * Testprogram to see that the mainers.Position class act like it should.
 */
public class TestPosition {

    /**
     * Test to see that a new created position with parameters are correct.
     */
    @Test
    public void testPosition(){
        int x;
        int y;
        Position p = new Position(2,3);
        x= p.getX();
        y= p.getY();
        assertEquals(x,2);
        assertEquals(y,3);
    }

    /**
     * Test to see that a new created position without parameters are
     * set correctly.
     */
    @Test
    public void testPositionWoValues(){
        int x;
        int y;
        Position p = new Position();
        x= p.getX();
        y= p.getY();
        assertEquals(x,0);
        assertEquals(y,0);
    }

    /**
     *
     */
    @Test
    public void testGetPosToDirection() {
        Position p = new Position(1, 1);
        assertEquals(new Position(2,1), p.getPosToDirection(Direction.SOUTH));
        assertEquals(new Position(0,1), p.getPosToDirection(Direction.NORTH));
        assertEquals(new Position(1,0), p.getPosToDirection(Direction.WEST));
        assertEquals(new Position(1,2), p.getPosToDirection(Direction.EAST));
    }

    /**
     * Test so getPosToWest gives the west position.
     */
    @Test
    public void testPositiontoWest(){
        int x;
        int y;
        Position p = new Position(1,1);
        p = p.getPosToWest();
        x=p.getX();
        y=p.getY();
        assertEquals(x,1);
        assertEquals(y,0);
    }

    /**
     * Test so getPosToEast gives the east position.
     */
    @Test
    public void testPositiontoEast(){
        int x;
        int y;
        Position p = new Position(1,1);
        p = p.getPosToEast();
        x=p.getX();
        y=p.getY();
        assertEquals(1,x);
        assertEquals(2,y);
    }

    @Test
    public void TestDistanceStraight() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(5,0);
        assertTrue(5 == p1.distance(p2));
    }

    @Test
    public void TestDistance() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(5,5);
        assertTrue(7.0710678118654755 == p1.distance(p2));
    }

    /**
     * Test so getPosToNorth gives the north position.
     */
    @Test
    public void testPositiontoNorth(){
        int x;
        int y;
        Position p = new Position(1,1);
        p = p.getPosToNorth();
        x=p.getX();
        y=p.getY();
        assertEquals(x,0);
        assertEquals(y,1);
    }

    /**
     * Test so getPosToSouth gives the south position.
     */
    @Test
    public void testPositiontoSouth(){
        int x;
        int y;
        Position p = new Position(1,1);
        p = p.getPosToSouth();
        x=p.getX();
        y=p.getY();
        assertEquals(x,2);
        assertEquals(y,1);
    }

    /**
     * Test so you can manually set x any y values by usint setX/Y.
     */
    @Test
    public void testSetXandSetY(){
        int x;
        int y;
        Position p = new Position(1,1);
        p.setX(2);
        p.setY(2);
        x=p.getX();
        y=p.getY();
        assertEquals(x,2);
        assertEquals(y,2);
    }

    /**
     * Equaltest  NYTT EFTER ÅTERLÄMNING
     */
    @Test
    public void equalTest(){
        Position node1 = new Position(1,1);
        Position node2 = new Position(1,1);
        Position node3 = new Position(0,0);

        assertTrue(node1.equals(node2) && node2.equals(node1));
        assertFalse(node1.equals(node3) && node2.equals(node3));
    }

    /**
     * NYTT HASHCODE TEST!
     */
    @Test
    public void hashCodeTest(){
        Position node1 = new Position(1,1);
        Position node2 = new Position(1,1);
        Position node3 = new Position(0,0);

        assertTrue(node1.hashCode() == node2.hashCode());
        assertFalse(node1.hashCode() == node3.hashCode());
    }
}


