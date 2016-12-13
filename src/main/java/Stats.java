

/**
 * Created by andreas on 2016-11-30.
 */
public class Stats {
    private int time;
    private int creditsUsed;
    private String playerName;
    private String levelName;


    public Stats() {
        time=0;
        creditsUsed=0;
    }

    public int getCreditsused() {
        return creditsUsed;
    }

    public void setCreditsused(int creditsused) {
        this.creditsUsed = creditsused;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
