import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Daniel on 2016-12-05.
 */
public class TestLevel {

    @Test
    public void TestLevelNoName() {
        Level l = new Level();
        assertEquals(l.getLevelName(), "Unknown");
    }

    @Test
    public void TestLevelWithName() {
        Level l = new Level("Level");
        assertEquals(l.getLevelName(), "Level");
    }
}