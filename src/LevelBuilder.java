import helpers.DOMParser;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class LevelBuilder {

    private DOMParser levelParser;
    private String fileName;
<<<<<<< HEAD
    private static String FILELOCATION = "../xml/levels.xml";
=======
    private static String FILELOCATION = "xml/levels.xml";
>>>>>>> master

    public LevelBuilder (String fileName) {
        this.fileName = fileName;
        setupParser(fileName);
        if(levelParser.isError()) {
            //Set error message to view.
            levelParser.parseFile(FILELOCATION);
        }
    }

    public LevelBuilder() {
<<<<<<< HEAD
      setupParser(FILELOCATION);
=======
        setupParser(FILELOCATION);
>>>>>>> master
    }

    private void setupParser(String fileName) {
        levelParser = new DOMParser();
        levelParser.parseFile(fileName);
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
