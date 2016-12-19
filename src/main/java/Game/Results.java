package Game;

import java.time.LocalTime;

/**
 * Created by Alexander Nyström(dv15anm) on 24/11/2016.
 *
 * Class to store results after a game
 */
public class Results {

    private LocalTime time;
    private long creditsLeft;
    private String levelName;
    private String stringTime;

    /**
     * Create a rsuluts object
     */
    public Results() {
        creditsLeft =0;
        time = LocalTime.of(0,0,0);
    }

    /**
     *
     * @return Number of credits after the game
     */
    public long getCreditsLeft() {
        return creditsLeft;
    }

    /**
     *
     * @param creditsused Number of credits after the game
     */
    public void setCreditsUsed(long creditsused) {
        this.creditsLeft = creditsused;
    }

    /**
     *
     * @param levelName Name of the game played
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     *
     * @return Name of the level played
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     *
     * @return String format of the time
     */
    public String getStringTime() {
        return stringTime;
    }

    /**
     *
     * @param stringTime Set the time as a String
     */
    public void setStringTime(String stringTime) {
        this.stringTime = stringTime;
    }

    /**
     *
     * @return Time played
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Takes the time as an int and converts it to the format HH:MM:SS
     * @param time The time played
     */
    public void setTime(int time) {
        int minute = time/60;
        int hour = minute/60;
        time = time - (minute * 60);
        stringTime = hour+":"+minute+":"+time;
        this.time = LocalTime.of(hour,minute,time);
    }

    /**
     *
     * @return A formatted output string
     */
    @Override
    public String toString() {
        return "Results{" +
                "time=" + time +
                ", creditsLeft=" + creditsLeft +
                ", levelName='" + levelName + '\'' +
                '}';
    }
}
