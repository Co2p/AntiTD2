package Game;

import java.util.ArrayList;

import static Game.main.slj;

public class Player {
    private String name;
    private Results results;

    /**
     * Constructor without name, name will then be set to "Unknown".
     */
    public Player() {
        this.name = "Unknown";
//        results = new ArrayList<>();
    }

    /**
     * Constructor with name
     * @param name, name to use
     */
    public Player(String name){
        this.name = name;
//        results = new ArrayList<>();
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

    public Results getResults() {
        return results;
    }

    public void sendResult() {
//        for (int i = 0; i < results.size(); i++) {
//            slj.postToDb(getName(), getResults().get(i).getLevelName(),
//                    (int) getResults().get(i).getCreditsLeft(),
//                    getResults().get(i).getStringTime());
//        }
        slj.postToDb(getName(),results.getLevelName(),(int)results.getCreditsLeft(),results.getStringTime());

    }
}