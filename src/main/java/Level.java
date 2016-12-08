

/**
 * Created by Daniel on 2016-12-05.
 */
public class Level {

    public String levelName;
    public Long credits;
    public int unitsToWin;
    public int towerSpawnRate;
    public int timeLimit;
    public String className;
    public String classPath;
    public String map;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String errorMessage;

    public Level() {
        this.levelName = "Unknown";
    }

    public Level(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public int getUnitsToWin() {
        return unitsToWin;
    }

    public void setUnitsToWin(int unitsToWin) {
        this.unitsToWin = unitsToWin;
    }

    public int getTowerSpawnRate() {
        return towerSpawnRate;
    }

    public void setTowerSpawnRate(int towerSpawnRate) {
        this.towerSpawnRate = towerSpawnRate;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
