/**
 * Created by Alexander Nyström(dv15anm) on 24/11/2016.
 */
public class Results {

    private int time;
    private int creditsUsed;
    private int totalTrooperCount;

    public Results () {
        time = 0;
        creditsUsed = 0;
        totalTrooperCount = 0;
    }

    /**
     * Sets the amount of credits used throughout the game
     * @param creditsUsed The credits used
     */
    public void setCreditsUsed(int creditsUsed) {
        this.creditsUsed = creditsUsed;
    }

    /**
     * Set the time the game lapsed
     * @param time The time the game lapsed
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Set the amount of troopers used to complete the game.
     * @param totalTrooperCount The trooper count
     */
    public void setTotalTrooperCount(int totalTrooperCount) {
        this.totalTrooperCount = totalTrooperCount;
    }

    /**
     *
     * @return The time it took
     */
    public int getTime() {
        return time;
    }

    /**
     *
     * @return The credits used
     */
    public int getCreditsUsed() {
        return creditsUsed;
    }

    /**
     *
     * @return The total amount of troopers used.
     */
    public int getTotalTrooperCount() {
        return totalTrooperCount;
    }
}
