

public class Player {
    private String name;
    private Results result;

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
     * Adds result to the resultlist.
     * @param r the results.
     */
    public void setResult(Results r) {
        this.result = r;

    }

    public Results getResult() {
        return result;
    }
}