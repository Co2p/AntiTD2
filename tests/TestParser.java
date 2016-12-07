import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
import main.java.helpers.DOMParser;

/**
 * Created by Alexander Nyström(dv15anm) on 30/11/2016.
 */
public class TestParser {

    private DOMParser parser;
    private String xmlFile = "xml/test.xml";

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

    @Test
    public void getLevelNameAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals("Level 1",parser.getLevelName().get(0));
    }

    @Test
    public void getCreditsAfterParse() {
        parser.parseFile(xmlFile);
        long cred = 1000;
        assertEquals((Long)cred,parser.getCredits().get(0));
    }

    @Test
    public void getUnitsAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals((Integer) 30,parser.getUnitsToWin().get(0));
    }

    @Test
    public void getTowersAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals((Integer) 25,parser.getTowerSpawnRate().get(0));
    }

    @Test
    public void getTimeAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals((Integer) 2,parser.getTimeLimit().get(0));
    }

    @Test
    public void getClassNameAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals("className",parser.getClassName().get(0));
    }

    @Test
    public void getClassPathAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals("imhere",parser.getClassPath().get(0));
    }


}
