import org.junit.Before;
import org.junit.Test;
import trooper.*;

import java.util.ArrayList;
import java.util.Hashtable;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class TestArmy {


    private Army army;

    @Before
    public void createArmy() {
        Hashtable temp = new Hashtable();
        army = new Army(temp);
    }
//
//    @Test
//    public void createPitifulTrooper() {
//        army.createTrooper(TrooperType.PITIFUL);
//        assertTrue(PitifulTrooper.class.isInstance(army.getFromQueue()));
//    }
//
//    @Test
//    public void createTeleportTrooper() {
//        army.createTrooper(TrooperType.TELEPORTER);
//        assertTrue(TeleportTrooper.class.isInstance(army.getFromQueue()));
//    }
//
//    @Test
//    public void createArmoredTrooper() {
//        army.createTrooper(TrooperType.ARMORED);
//        assertTrue(ArmoredTrooper.class.isInstance(army.getFromQueue()));
//    }

//    @Test
//    public void TestGetArmyStartPos() {
//        ArrayList<Level> levelArray = new ArrayList<Level>();
//        LevelBuilder lb = new LevelBuilder();
//
//        for (int i = 0; i < lb.getNoOfLevels(); i++) {
//            Level l = lb.buildLevel(i);
//            levelArray.add(l);
//        }
//
//        Game g = new Game(levelArray.get(0));
//        g.setupMap();
//        Army a = new Army(g.getMap());
//        a.getArmy().add(a.createTrooper(TrooperType.ARMORED));
//        assertEquals(0, a.getArmy().get(0).getPosition().getX());
//        assertEquals(1, a.getArmy().get(0).getPosition().getY());
//    }
}
