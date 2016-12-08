import static org.junit.Assert.*;

import main.java.Level;
import org.junit.Test;

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
        Level l = new Level("main.java.Level");
        assertEquals(l.getLevelName(), "main.java.Level");
    }
}