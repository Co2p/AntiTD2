import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by andreas on 2016-11-30.
 */
public class TestTower {

    @Test
    public void TestBasicsTower(){
        Tower t = new Tower(4,1, new Position());
        assertEquals(t.getRange(),1);
        //assertEquals(t.fire(),4);
    }
    @Test
    public void TestTowerSetPos(){
        Position p = new Position(1,2);
        Tower t = new Tower(4,1, p);
        assertEquals(t.getPos().getY(),2);
        assertEquals(t.getPos().getX(),1);
    }

    @Test
    public void TestAddNeighbours(){
        Position p = new Position(2,2);
        Tower t = new Tower(4,1, p);
        Position ptrue = new Position(1,2);
        Position pfalse = new Position(5,5);
        assertTrue(t.inRange(ptrue));
        assertFalse(t.inRange(pfalse));
    }

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

    @Test
    public void TestPositionOutOfRange1() {
        Position p = new Position(0, 0);
        Tower t = new Tower(4, 1, p);
        assertEquals(t.getNrofNeighbours(), 2);
    }

    @Test
    public void TestPositionOutOfRange() {
        Position p = new Position(3, 3);
        Tower t = new Tower(4, 5, p);
        assertEquals(t.getNrofNeighbours(), 52);
    }

    @Test
    public void TestShootTrooper1() {
        Position ptower = new Position(2,2);
        Position ptrooper1 = new Position(3,3);
        Tower t = new Tower(4,1, ptower);
        t.setPos(ptower);
        t.addNeighbours(1);
        Trooper tr1 = new Trooper(10);
        tr1.setPosition(ptrooper1);
        t.update(null, tr1);
        assertEquals(10, tr1.getHealth());
    }

    @Test
    public void TestShootTrooper2(){
        Position ptower = new Position(2,2);
        Position ptrooper2 = new Position(4,4);
        Tower t = new Tower(4,1, ptower);
        t.setPos(ptower);
        t.addNeighbours(1);
        Trooper tr2 = new Trooper(10);
        tr2.setPosition(ptrooper2);
        t.update(null, tr2);
        assertEquals(10, tr2.getHealth());
    }

}
