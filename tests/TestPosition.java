import static org.junit.Assert.*;
import org.junit.Test;
/**
 * Created by andreas on 2016-05-20.
 * Testprogram to see that the Position class act like it should.
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
        assertEquals(x,1);
        assertEquals(y,2);
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


