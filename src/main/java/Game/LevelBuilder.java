package Game;

import helpers.LevelParser;
import helpers.ErrorMessages;
import helpers.ZoneLoader;

import java.util.Objects;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class LevelBuilder {

    private LevelParser levelParser;
    private ZoneLoader zoneLoader;
    private ErrorMessages errorMessages;
    private String fileName;
    private static String FILELOCATION = "xml/levels.xml";
    private volatile boolean go;

    public LevelBuilder (String fileName) {
        go = false;
        this.fileName = fileName;
        errorMessages = new ErrorMessages();
        zoneLoader = new ZoneLoader(errorMessages);
        setupParser(fileName);
        if(levelParser.isError()) {
            ErrorWindow errorWindow = new ErrorWindow(errorMessages,this);
            errorWindow.setVisable();
        } else {
            go = true;
        }
    }

    public LevelBuilder() {
        go = true;
        errorMessages = new ErrorMessages();
        zoneLoader = new ZoneLoader(errorMessages);
      setupParser(FILELOCATION);
    }

    public void defaultMap() {
        zoneLoader = new ZoneLoader(errorMessages);
        setupParser(FILELOCATION);
        go = true;
    }

    private void setupParser(String fileName) {
        levelParser = new LevelParser("src/main/resources/xml/levelSchema.xml",errorMessages);
        levelParser.parseFile(fileName);
    }

    //TODO create a method for the pause instead of using the loop twice.
    public Level buildLevel(int i) {
        while (!go) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
            errorWindow.setVisable();
        } else {
            go = true;
        }
        while (!go) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
        return level;
    }

    public static String stringArrayToString(String[] sArr) {
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

    public void setGo(boolean go) {
        this.go = go;
    }
}
