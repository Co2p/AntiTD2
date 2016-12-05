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
        t.setDirection(Direction.SOUTH);
        assertEquals(Direction.SOUTH, t.getDirection());
    }

    @Test
    public void TestSetSpeed(){
        Trooper t = new Trooper(10,1);
        t.setSpeed(2);
        assertEquals(2, t.getSpeed());
    }

    @Test
    public void TestTakingFatalDamage(){
        Trooper t = new Trooper(10,1);
        t.receiveDamage(11);
        t.receiveDamage(11);
        assertTrue(t.isDead());
    }

    @Test
    public void TestIsZombieWithFullHealth(){
        Trooper t = new Trooper(10,1);
        t.receiveDamage(11);
        assertFalse(t.isDead());
        assertEquals(10,t.getHealth());
    }

    @Test
    public void TestArmorSaveHuman(){
        Trooper t = new Trooper(10,1);
        t.setArmor(2);
        t.receiveDamage(11);
        assertFalse(t.isDead());
        assertEquals(1,t.getHealth());
    }


    @Test
    public void TestIsDeadWithArmor(){
        Trooper t = new Trooper(10,1);
        t.setArmor(4);
        t.receiveDamage(20);
        t.receiveDamage(11);
        assertEquals(1,t.getHealth());
        t.receiveDamage(4);
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


    //Återstår att göra.
    /*@Test
    public void TestPreviosTile(){
        Trooper t = new Trooper(10);

    }*/
}
