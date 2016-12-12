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
    private Object zone;
    private Method landOn;
    public String map;
    private boolean gotLandOn;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    public Level() {
        this.levelName = "Unknown";
        gotLandOn = false;
    }

    public Level(String levelName) {
        this.levelName = levelName;
        gotLandOn = false;
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

    public Object getZone() {
        return zone;
    }

    public void setZone(Object zone) {
        this.zone = zone;
        gotLandOn = true;
    }

    public Method getLandOn() {
        return landOn;
    }

    public void setLandOn(Method landOn) {
        this.landOn = landOn;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public boolean gotLandon() {
        return gotLandOn;
    }
}
