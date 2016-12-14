package Game;

import java.lang.reflect.Method;

/**
 * Created by Daniel on 2016-12-05.
 */
public class Level {

    private String levelName;
    private Long credits;
    private int unitsToWin;
    int towerSpawnRate;
    private int timeLimit;
    private String className;
    private String classPath;
    public String map;
    private Method landOn;
    private Object zone;
    private int rows;
    private int columns;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

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

    public Method getLandOn() {
        return landOn;
    }

    public void setLandOn(Method landOn) {
        this.landOn = landOn;
    }

    public Object getZone() {
        return zone;
    }

    public void setZone(Object zone) {
        this.zone = zone;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
