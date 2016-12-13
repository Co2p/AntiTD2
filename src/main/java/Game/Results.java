package Game;
import java.time.LocalTime;

/**
 * Created by Alexander Nyström(dv15anm) on 24/11/2016.
 */
public class Results {

    private LocalTime time;
    private long creditsLeft;
    private String levelName;


    public Results() {
        creditsLeft =0;
    }

    public long getCreditsused() {
        return creditsLeft;
    }

    public void setCreditsused(long creditsused) {
        this.creditsLeft = creditsused;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }


    public LocalTime getTime() {
        return time;
    }

    public void setTime(int time) {
        int minute = time/60;
        int hour = minute/60;
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
