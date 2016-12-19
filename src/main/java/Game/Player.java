package Game;
import static Game.main.slj;

/**
 * Created by Andreas on 2016-12-01
 *
 * Playes is a class that stores an name and a result object.
 * It has methods to set and get results and name and also send results to an
 * sql database.
 */
public class Player {
    private String name;
    private Results results;

    /**
     * Constructor without name, name will then be set to "Unknown".
     */
    public Player() {
        this.name = "Unknown";
    }

    /**
     * Constructor with name
     * @param name, name to use
     */
    public Player(String name){
        this.name = name;
    }

    /**
     * Setter for name
     * @param name, the new name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Getter for the name
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Adds results to the resultlist.
     * @param r the results.
     */
    public void setResults(Results r) {
        results = r;

    }

    /**
     *
     * @return Players results
     */
    public Results getResults() {
        return results;
    }

    /**
     * Send the results to the database
     */
    public void sendResult() {
        slj.postToDb(getName(),results.getLevelName(),
                (int)results.getCreditsLeft(),results.getStringTime());
    }
}