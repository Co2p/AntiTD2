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
    private String className;
    public String map;
    private Method landOn;
    private Object zone;
    private int rows;
    private int columns;

    public Level() {
        this.levelName = "Unknown";
    }

    public Level(String levelName) {
        this.levelName = levelName;
    }

    /**
     *
     * @return Name of the level
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     *
     * @param levelName Name of the level
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     *
     * @return Number of credits in the start of this level
     */
    public Long getCredits() {
        return credits;
    }

    /**
     *
     * @param credits Number of credits in the start of this level
     */
    public void setCredits(Long credits) {
        this.credits = credits;
    }

    /**
     *
     * @return Units required to win this level
     */
    public int getUnitsToWin() {
        return unitsToWin;
    }

    /**
     *
     * @param unitsToWin Units required to win this level
     */
    public void setUnitsToWin(int unitsToWin) {
        this.unitsToWin = unitsToWin;
    }

    /**
     *
     * @return The chance that a tower should spawn
     */
    public int getTowerSpawnRate() {
        return towerSpawnRate;
    }

    /**
     *
     * @param towerSpawnRate The chance that a tower should spawn
     */
    public void setTowerSpawnRate(int towerSpawnRate) {
        this.towerSpawnRate = towerSpawnRate;
    }

    /**
     *
     * @return Name of external Zone class
     *
     * @see tile.Zone
     * @see helpers.ZoneLoader
     */
    public String getClassName() {
        return className;
    }

    /**
     *
     * @param className Name of external Zone class
     *
     * @see tile.Zone
     * @see helpers.ZoneLoader
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     *
     * @return The map
     */
    public String getMap() {
        return map;
    }

    /**
     *
     * @param map The map
     */
    public void setMap(String map) {
        this.map = map;
    }

    /**
     *
     * @return Landon modifier that has been loaded through reflection
     *
     * @see helpers.ZoneLoader
     * @see tile.RoadTile
     */
    public Method getLandOn() {
        return landOn;
    }

    /**
     *
     * @param landOn LandOn modifier that has been loaded through reflection
     *
     * @see helpers.ZoneLoader
     * @see tile.RoadTile
     */
    public void setLandOn(Method landOn) {
        this.landOn = landOn;
    }

    /**
     *
     * @return LoandOn modifier object that has been loaded through reflection
     *
     * @see helpers.ZoneLoader
     * @see tile.RoadTile
     */
    public Object getZone() {
        return zone;
    }

    /**
     *
     * @param zone LandOn modifier object that has been loaded through
     *             reflection
     * @see helpers.ZoneLoader
     * @see tile.RoadTile
     */
    public void setZone(Object zone) {
        this.zone = zone;
    }

    /**
     *
     * @return Number of rows in the map
     */
    public int getRows() {
        return rows;
    }

    /**
     *
     * @param rows Number of rows in the map
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     *
     * @return Number of columns in the map
     */
    public int getColumns() {
        return columns;
    }

    /**
     *
     * @param columns Number of columns in the map
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

}
