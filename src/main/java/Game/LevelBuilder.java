package Game;

import helpers.LevelParser;
import helpers.ErrorMessages;
import helpers.ZoneLoader;

import java.util.Objects;

/**
 * Creates levels from the level parser. Has two constructors if the user inputs a XML-file
 * when starting the game.
 *
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 * Finalized by Daniel Sjöström(id13dsm)
 */
public class LevelBuilder {

    private LevelParser levelParser;
    private ZoneLoader zoneLoader;
    private ErrorMessages errorMessages;
    private String fileName;
    private static String FILELOCATION = "/xml/levels.xml";
    private volatile boolean go;

    /**
     * Creates a level parser which will either return levels or a error message.
     * @param fileName Is a location of a XML-file it is entered into the program through args
     *
     * id13dsm@cs.umu.se Daniel Sjöström
     */
    public LevelBuilder (String fileName) {
        go = false;
        this.fileName = fileName;
        errorMessages = new ErrorMessages();
        zoneLoader = new ZoneLoader(errorMessages);
        setupParser(fileName);
        if(levelParser.isError()) {
            ErrorWindow errorWindow = new ErrorWindow(errorMessages,this);
            errorWindow.setVisible(true);
            pauseBuild();
        } else {
            go = true;
        }
    }

    /**
     * Second constructor which will use the default XML-file located in the resources folder.
     *
     * id13dsm@cs.umu.se
     */
    public LevelBuilder() {
        go = true;
        errorMessages = new ErrorMessages();
        zoneLoader = new ZoneLoader(errorMessages);
      setupParser(FILELOCATION);
    }

    /**
     * Loads the default maps.
     *
     * dv15anm@cs.umu.se Alexander Nyström
     */
    public void defaultMap() {
        zoneLoader = new ZoneLoader(errorMessages);
        setupParser(FILELOCATION);
        if (!levelParser.isError()) {
            go = true;
        }
    }

    /**
     * Creates a new LevelParser
     * @param fileName file location for a the XML-file.
     *
     * id13dsm@cs.umu.se
     */
    private void setupParser(String fileName) {
        levelParser = new LevelParser("/xml/levelSchema.xml",errorMessages);
        levelParser.parseFile(fileName);
    }

    /**
     * Gets the information form the level parser and passes it into set methods for level.
     * Builds several levels at once if there is more than one level in the XML-file.
     * @param i Number of levels.
     * @return level A single level object.
     *
     * id13dsm@cs.umu.se
     */
    public Level buildLevel(int i) {
        pauseBuild();
        Level level = new Level();
        level.setLevelName(levelParser.getLevelName().get(i));
        level.setCredits(levelParser.getCredits().get(i));
        level.setUnitsToWin(levelParser.getUnitsToWin().get(i));
        level.setTowerSpawnRate(levelParser.getTowerSpawnRate().get(i));
        if(!Objects.equals(levelParser.getClassName().get(i), "")) {
            if (zoneLoader.loadZone(levelParser.getClassName().get(i))) {
                level.setZone(zoneLoader.getZone());
                level.setLandOn(zoneLoader.getLandOn());
            }
        }
        level.setMap(stringArrayToString(levelParser.getMap().get(i)));
        level.setColumns(levelParser.getColumns().get(i));
        level.setRows(levelParser.getRows().get(i));
        if(zoneLoader.isError()) {
            go = false;
            ErrorWindow errorWindow = new ErrorWindow(errorMessages,this);
            errorWindow.setVisible(true);
            pauseBuild();
            level = buildLevel(i);
        }
        pauseBuild();
        return level;
    }

    /**
     * Pauses the build
     *
     * dv15anm@cs.umu.se Alexander Nyström
     */
    public void pauseBuild(){
        while (!go) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
        }
    }

    /**
     * Converts a string array to a string.
     * @param sArr args from user input.
     * @return sb.toString A string instead of an array.
     *
     * id13dsm@cs.umu.se
     */
    public static String stringArrayToString(String[] sArr) {
        StringBuilder sb = new StringBuilder();
        for (String s: sArr) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * Returns the number of levels.
     * @return levelParser.getLevelCount The number of levels that has been parsed
     *
     * id13dsm@cs.umu.se
     */
    public int getNoOfLevels() {
       return levelParser.getLevelCount();
    }

    public String getFileName() {
        return fileName;
    }

    public void setGo(boolean go) {
        this.go = go;
    }
}
