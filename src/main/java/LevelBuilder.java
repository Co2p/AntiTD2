

import helpers.DOMParser;
import helpers.ErrorMessages;
import helpers.ZoneLoader;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class LevelBuilder {

    private DOMParser levelParser;
    private ZoneLoader zoneLoader;
    private ErrorMessages errorMessages;
    private String fileName;
    private static String FILELOCATION = "xml/levels.xml";

    public LevelBuilder (String fileName) {
        this.fileName = fileName;
        errorMessages = new ErrorMessages();
        zoneLoader = new ZoneLoader(errorMessages);
        setupParser(fileName);
        if(levelParser.isError()) {
            //Set error message to view.
            levelParser.parseFile(FILELOCATION);
        }
    }

    public LevelBuilder() {
        errorMessages = new ErrorMessages();
        zoneLoader = new ZoneLoader(errorMessages);
      setupParser(FILELOCATION);
    }

    private void setupParser(String fileName) {
        levelParser = new DOMParser("src/main/resources/xml/levelSchema.xml",errorMessages);
        levelParser.parseFile(fileName);
    }

    public Level buildLevel(int i) {
        Level level = new Level();
        level.setLevelName(levelParser.getLevelName().get(i));
        level.setCredits(levelParser.getCredits().get(i));
        level.setUnitsToWin(levelParser.getUnitsToWin().get(i));
        level.setTowerSpawnRate(levelParser.getTowerSpawnRate().get(i));
        level.setTimeLimit(levelParser.getTimeLimit().get(i));
        if(levelParser.getClassName().get(i) != null) {
            if (zoneLoader.loadZone(levelParser.getClassName().get(i))) {
                level.setZone(zoneLoader.getZone());
                level.setLandOn(zoneLoader.getLandOn());
            }
        }
        level.setMap(stringArrayToString(levelParser.getMap().get(i)));
        return level;
    }

    public String stringArrayToString(String[] sArr) {
        StringBuilder sb = new StringBuilder();
        for (String s: sArr) {
            sb.append(s);
        }
        return sb.toString();
    }

    public int getNoOfLevels() {
       return levelParser.getLevelCount();
    }

    public String getFileName() {
        return fileName;
    }
}
