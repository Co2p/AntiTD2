import org.junit.Test;
import static junit.framework.TestCase.*;

/**
 * Created by andreas on 2016-12-01.
 */
public class TestRoadTile {

    @Test
    public void TestRoadTile(){
        Position p = new Position(1,1);
        RoadTile t = new RoadTile(p);
        assertFalse(t.isGoal());

    }
}
