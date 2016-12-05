/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */

/**
 *
 */
public class LevelBuilder {

    private DOMParser levelParser;
    private String fileName;
    private int levelCount;

    public LevelBuilder (String fileName) {
        levelParser = new DOMParser();
        this.fileName = fileName;
        levelParser.parseFile(fileName);
        if(levelParser.isError()) {
            //Set error message to view.
            levelParser.parseFile("xml/levels.xml");
        }
    }

    public Level buildLevel(int i) {

        Level level = new Level();
        level.setLevelName(levelParser.getLevelName().get(i));
        level.setCredits(levelParser.getCredits().get(i));
        level.setUnitsToWin(levelParser.getUnitsToWin().get(i));
        level.setTowerSpawnRate(levelParser.getTowerSpawnRate().get(i));
        level.setTimeLimit(levelParser.getTimeLimit().get(i));
        level.setClassName(levelParser.getClassName().get(i));
        level.setClassPath(levelParser.getClassPath().get(i));
        level.setMap(levelParser.getMap().get(i));
        return level;
    }

    public int getNoOfLevels() {
       return this.levelCount = levelParser.getLevelCount();
    }

    public String getFileName() {
        return fileName;
    }
}
