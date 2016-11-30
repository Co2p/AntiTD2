import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by andreas on 2016-11-30.
 */
public class TestTower {

    @Test
    public void TestBasicsTower(){
        Tower t = new Tower(4,1);
        assertEquals(t.getRange(),1);
        assertEquals(t.fire(),4);
    }
    @Test
    public void TestTowerSetPos(){
        Position p = new Position(1,2);
        Tower t = new Tower(4,1);
        t.setPos(p);
        assertEquals(t.getPos().getY(),2);
        assertEquals(t.getPos().getX(),1);
    }

    @Test
    public void TestAddNeighbours(){
        Position p = new Position(2,2);
        Tower t = new Tower(4,1);
        t.setPos(p);
        t.addNeighbours();
        Position ptrue = new Position(1,2);
        Position pfalse = new Position(5,5);
        assertTrue(t.inRange(ptrue));
        assertFalse(t.inRange(pfalse));
    }

    @Test
    public void TestShootTrooper(){
        Position ptower = new Position(2,2);
        Position ptrooper1 = new Position(3,3);
        Position ptrooper2 = new Position(4,4);
        Tower t = new Tower(4,1);
        t.setPos(ptower);
        t.addNeighbours();
        Trooper tr1 = new Trooper(10);
        Trooper tr2 = new Trooper(10);
        tr1.setPosition(ptrooper1);
        tr2.setPosition(ptrooper2);
        t.update(null, tr1);
        t.update(null, tr2);
        assertEquals(tr1.getHealth(),6);
        assertEquals(tr2.getHealth(),10);


    }

}
