package main.java;

/**
 * Created by andreas on 2016-11-30.
 */
public class Stats {
    private int time;
    private int creditsUsed;
    private int totalTrooperCont;


    public Stats() {
        time=0;
        creditsUsed=0;
        totalTrooperCont=0;
    }

    public int getCreditsused() {
        return creditsUsed;
    }

    public void setCreditsused(int creditsused) {
        this.creditsUsed = creditsused;
    }

    public int getTotalTrooperCont() {
        return totalTrooperCont;
    }

    public void setTotalTrooperCont(int totalTrooperCont) {
        this.totalTrooperCont = totalTrooperCont;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
