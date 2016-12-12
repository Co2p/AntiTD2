import org.junit.Before;
import org.junit.Test;

import helpers.DOMParser;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Alexander Nyström(dv15anm) on 30/11/2016.
 */
public class TestParser {

    private DOMParser parser;
    private String xmlFile = "test.xml";

    @Before
    public void createParser() {
        parser = new DOMParser("src/Game.main/resources/xml/levelSchema.xml");
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
        assertEquals(0,parser.getErrorMessage().getSize());
    }

    @Test
    public void parseEmptyName() {
        parser.parseFile("");
        assertEquals("Could not find file: ",parser.getErrorMessage().getFileError());
    }

    @Test
    public void parseNonExistingXml() {
        parser.parseFile("notExisting.xml");
        assertEquals("Could not find file: notExisting.xml",parser.getErrorMessage().getFileError());
    }

    @Test
    public void getLevelNameAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals(2,parser.getLevelName().size());
        assertEquals("Game.Level 1",parser.getLevelName().get(0));
    }

    @Test
    public void getCreditsAfterParse() {
        parser.parseFile(xmlFile);
        long cred = 1000;
        assertEquals(2,parser.getCredits().size());
        assertEquals((Long)cred,parser.getCredits().get(0));
    }

    @Test
    public void getUnitsAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals(2,parser.getUnitsToWin().size());
        assertEquals((Integer) 30,parser.getUnitsToWin().get(0));
    }

    @Test
    public void getTowersAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals(2,parser.getTowerSpawnRate().size());
        assertEquals((Integer) 25,parser.getTowerSpawnRate().get(0));
    }

    @Test
    public void getTimeAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals(2,parser.getTimeLimit().size());
        assertEquals((Integer) 2,parser.getTimeLimit().get(0));
    }

    @Test
    public void getRowsAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals(2,parser.getRows().size());
        assertEquals((Integer)8,parser.getRows().get(0));
    }

    @Test
    public void getColumnsAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals(2,parser.getColumns().size());
        assertEquals((Integer)12,parser.getColumns().get(0));
    }

    @Test
    public void getClassNameAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals(2,parser.getClassName().size());
        assertEquals("className",parser.getClassName().get(0));
    }

    @Test
    public void getClassPathAfterParse() {
        parser.parseFile(xmlFile);
        assertEquals(2,parser.getClassPath().size());
        assertEquals("imhere",parser.getClassPath().get(0));
    }


}
