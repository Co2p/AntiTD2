import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Results> result;

    /**
     * Constructor without name, name will then be set to "Unknown".
     */
    public Player() {
        this.name = "Unknown";
        result = new ArrayList<>();
    }

    /**
     * Constructor with name
     * @param name, name to use
     */
    public Player(String name){
        this.name = name;
        result = new ArrayList<>();
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
     * Adds result to the resultlist.
     * @param r the results.
     */
    public void setResult(Results r) {
        result.add(r);

    }

    public ArrayList<Results> getResult() {
        return result;
    }
}