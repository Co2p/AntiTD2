import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static junit.framework.TestCase.*;

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

    @Test
    public void createPitifulTrooper() {
        army.createTrooper(TrooperType.PITIFUL);
        assertTrue(PitifulTrooper.class.isInstance(army.getFromQueue()));
    }

    @Test
    public void createTeleportTrooper() {
        army.createTrooper(TrooperType.TELEPORTER);
        assertTrue(TeleportTrooper.class.isInstance(army.getFromQueue()));
    }

    @Test
    public void createArmoredTrooper() {
        army.createTrooper(TrooperType.ARMORED);
        assertTrue(ArmoredTrooper.class.isInstance(army.getFromQueue()));
    }
}
