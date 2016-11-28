import org.junit.Test;

import static junit.framework.TestCase.*;

public class TestTrooper {

    @Test
    public void TestCreateTrooper(){
        Trooper t = new Trooper(10,1);
        assertEquals(t.getHealth(),10);
        assertEquals(t.getSpeed(), 1);
    }

    @Test
    public void TestCreateTrooperOnlyHp(){
        Trooper t = new Trooper(10);
        assertEquals(t.getHealth(),10);
        assertEquals(t.getSpeed(), 1);
    }

    @Test
    public void TestRecieveHealth(){
        Trooper t = new Trooper(10,1);
        t.receiveDamage(4);
        assertEquals(t.getHealth(),6);
        t.receiveHealth(2);
        assertEquals(t.getHealth(), 8);
        t.receiveHealth(4);
        assertEquals(t.getHealth(), 10);
    }

    @Test
    public void TestDecrementHp(){
        Trooper t = new Trooper(10,1);
        t.receiveDamage(2);
        assertEquals(t.getHealth(), 8);
    }

    @Test
    public void TestSetDirection(){
        Trooper t = new Trooper(10,1);
        t.setDirection("South");
        assertEquals(t.getDirection(), "South");
    }

    @Test
    public void TestSetSpeed(){
        Trooper t = new Trooper(10,1);
        t.setSpeed(2);
        assertEquals(t.getSpeed(), 2);
    }

    @Test
    public void TestIsDead(){
        Trooper t = new Trooper(10,1);
        t.receiveDamage(10);
        assertEquals(t.getHealth(), 0);
        assertTrue(t.isDead());
    }

    @Test
    public void TestSetHp(){
        Trooper t = new Trooper(10,1);
        t.setHealth(-2);
        assertEquals(t.getHealth(), -2);
    }

    @Test
    public void TestTrooperPositionNull(){
        Trooper t = new Trooper(10);
        assertNull(t.getPosition());
    }

    @Test
    public void TestTrooperMoveTo(){
        Position p = new Position(1,2);
        Trooper t = new Trooper(10);
        t.moveTo(p);
        Position p2 = new Position(10,11);
        t.moveTo(p2);
        assertEquals(t.getPosition().getX(),10);
        assertEquals(t.getPosition().getY(),11);
    }

    //Återstår att göra.
    /*@Test
    public void TestPreviosTile(){
        Trooper t = new Trooper(10);

    }*/
}
