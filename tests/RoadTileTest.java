import org.junit.Before;
import org.junit.Test;

import java.util.Observer;

import static org.junit.Assert.*;

/**
 * Created by gordon on 2016-11-28.
 */
public class RoadTileTest {

    private RoadTile roadTile;

    @Before
    public void createRoadTile() {
        roadTile = new RoadTile(new Position(), true);
    }

    @Test
    public void landOnWithTrooper() throws Exception {
        //temporary observer o
        Observer o = (o1, arg) -> {
            Trooper t = (Trooper) arg;
            assertEquals(100, t.getHealth());
        };
        roadTile.addObserver(o);
        roadTile.landOn(new Trooper(100, 100));
    }

    @Test
    public void landOnNull() throws Exception {
        //temporary observer o
        Observer o = (o1, arg) -> {
            Trooper t = (Trooper) arg;
            assertEquals(100, t.getHealth());
        };
        roadTile.addObserver(o);
        roadTile.landOn(null);
    }

    @Test
    public void hasPortal() throws Exception {
        assertEquals(false, roadTile.hasPortal());
    }


    @Test
    public void setPortal() throws Exception {
        roadTile.setPortal(roadTile);
        assertEquals(true, roadTile.hasPortal());
    }

    @Test
    public void getPortalExit() throws Exception {
        roadTile.setPortal(roadTile);
        assertEquals(roadTile, roadTile.getPortalExit());
    }

    @Test
    public void isGoal() throws Exception {
        assertTrue(roadTile.isGoal());
    }

}