package Game;

import java.time.LocalTime;

/**
 * Created by Alexander Nyström(dv15anm) on 24/11/2016.
 */
public class Results {

    private LocalTime time;
    private long creditsLeft;
    private String levelName;
    private String stringTime;


    public Results() {
        creditsLeft =0;
        time = LocalTime.of(0,0,0);
    }

    public long getCreditsLeft() {
        return creditsLeft;
    }

    public void setCreditsUsed(long creditsused) {
        this.creditsLeft = creditsused;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }


    public int getTime() {
        return Integer.parseInt(stringTime);
    }

    public void setTime(int time) {
        int minute = time/60;
        int hour = minute/60;
        stringTime = "" + hour + "" + minute;
        time = time - (minute * 60);
        this.time = LocalTime.of(hour,minute,time);
    }

    @Override
    public String toString() {
        return "Results{" +
                "time=" + time +
                ", creditsLeft=" + creditsLeft +
                ", levelName='" + levelName + '\'' +
                '}';
    }
}
