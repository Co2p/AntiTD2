import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by Alexander Nyström(dv15anm) on 30/11/2016.
 */
public class TestParser {

    private DOMParser parser;

    @Before
    public void createParser() {
        parser = new DOMParser();
    }

    @Test
    public void falseError() {
        assertFalse(parser.isError());
    }

    @Test
    public void zeroLevelCount() {
        assertEquals(0,parser.getLevelCount());
    }

    @Test
    public void emptyLevelName() {
        assertEquals(0,parser.getLevelName().size());
    }

    @Test
    public void emptyCredits() {
        assertEquals(0,parser.getCredits().size());
    }

    @Test
    public void emptyUnits() {
        assertEquals(0,parser.getUnitsToWin().size());
    }

    @Test
    public void emptyTowers() {
        assertEquals(0,parser.getTowerSpawnRate().size());
    }

    @Test
    public void emptyTime() {
        assertEquals(0,parser.getTimeLimit().size());
    }

    @Test
    public void emptyClassName() {
        assertEquals(0,parser.getClassName().size());
    }

    @Test
    public void emptyClassPath() {
        assertEquals(0,parser.getClassPath().size());
    }

    @Test
    public void emptyMap() {
        assertEquals(0,parser.getMap().size());
    }

    @Test
    public void emptyErrorMessage() {
        assertEquals(0,parser.getErrorMessage().length());
    }

    @Test
    public void parseEmptyName() {
        parser.parseFile("");
        assertEquals("Could not find file: ",parser.getErrorMessage());
    }

    @Test
    public void parseNonExistingXml() {
        parser.parseFile("notExisting.xml");
        assertEquals("Could not find file: notExisting.xml",parser.getErrorMessage());
    }



}