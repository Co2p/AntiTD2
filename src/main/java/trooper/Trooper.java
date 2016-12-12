package trooper;

import tile.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;
import helpers.Position;
import helpers.Direction;

import static helpers.Direction.*;

public class Trooper {
    private int maxhealth;
    private int zombiehealth;
    private int health;
    private int stepDelay = 2;
    private int semiStep = 0;
    private int zombiestepDelay;
    private int armor;
    private boolean hasTurned = false;
    private boolean isDead = false;
    private Direction direction;
    private Position position = null;
    private Stack<Position> path = new Stack<>();
    private ArrayList<Position> visited = new ArrayList<>();
    private boolean reverse = false;
    private boolean reachedGoal = false;

    /**
     * Default constructor, crates a mainper.PitifulTrooper
     */
    public Trooper(){
        this.maxhealth=100;
        this.zombiehealth=100;
        this.health=100;
        this.zombiestepDelay=(this.stepDelay/2);
        this.armor=0;
        this.direction = NORTH;
    }

    /**
     * Constructor that takes in a hp value
     * @param hp the defined hp for the mainper
     */
    public Trooper(int hp){
        this.maxhealth=hp;
        this.zombiehealth=hp;
        this.health=hp;
        this.zombiestepDelay=(this.stepDelay/2);
        this.armor=0;
        this.direction = NORTH;
    }

    /**
<<<<<<< HEAD
     * A mainper that takes in hp and speed values
     * @param hp mainper hp
     * @param speed mainper speed
=======
     * A main.java.main.java.trooper that takes in hp and stepDelay values
     * @param hp main.java.main.java.trooper hp
     * @param stepDelay number of cycles before the trooper takes a steptrooper
>>>>>>> master
     */
    public Trooper(int hp, int stepDelay){
        this.maxhealth = hp;
        this.health=hp;
        this.stepDelay=stepDelay;
        this.zombiehealth=hp;
        this.zombiestepDelay=(this.stepDelay/2);
        this.armor=0;
        this.direction = NORTH;
    }

    /**
     * Sets the armour strength
     * @param armor armour strength
     */
    public void setArmor(int armor) {
        this.armor = armor;
    }

    /**
     * Adds health (heals) the mainper
     * @param addHealth amount to heal the mainper by
     */
    public void receiveHealth(int addHealth){
        if(!hasTurned) {
            if ((this.health + addHealth) >= this.maxhealth) {
                this.health = this.maxhealth;
            } else {
                this.health = (this.health + addHealth);
            }
        }
        else{
            if ((this.zombiehealth + addHealth) >= this.maxhealth) {
                this.zombiehealth = this.maxhealth;
            } else {
                this.zombiehealth = (this.health + addHealth);
            }
        }
    }

    /**
     * Receive damage (deduct from  health)
     * @param removeHealth amount of damage to the mainper
     */
    public void receiveDamage(int removeHealth){
        if(!hasTurned) {
            this.health = (this.health - (removeHealth-armor));
            if(this.health < 1 ){
                hasTurned = true;
            }
        }
        else{
            this.zombiehealth = (this.zombiehealth - (removeHealth-(armor/2)));
            if(this.zombiehealth < 1){
                isDead = true;
            }
        }
        //TODO REMOVE SOUT
        System.out.println("Target hp: " + getHealth());
    }

    /**
     * Returns true if the mainper is dead
     * @return true if the mainper is dead
     */
    public boolean isDead(){
        return isDead;
    }

    /**
     * Set the prioritized direction for the mainper
     * @param d a mainers.Direction (North, East, South, West)
     */
    public void setDirection(Direction d){
        if (d == Direction.NORTH || d == Direction.SOUTH || d == Direction.WEST || d == Direction.EAST) {
            this.direction = d;
        }
    }

    /**
     * Get current prioritized direction for the mainper
     * @return prioritized direction
     */
    public Direction getDirection(){
        return direction;
    }

    /**
     * Get the health of the mainper
     * @return health
     */
    public int getHealth(){
        if(!hasTurned) {
            return health;
        }
        else{
            return zombiehealth;
        }
    }

    /**
     * Set the health of the mainper
     * @param health health
     */
    public void setHealth(int health){
        this.health=health;
    }

    public int getstepDelay(){
        if(!hasTurned) {
            return stepDelay;
        }
        else{
            return zombiestepDelay;
        }
    }

    /**
<<<<<<< HEAD
     * Set the speed of the mainper
     * @param speed speed
=======
     * Set the stepDelay of the main.java.main.java.trooper
     * @param stepDelay stepDelay
>>>>>>> master
     */
    public void setstepDelay(int stepDelay){
        this.stepDelay=stepDelay;
        this.zombiestepDelay=(stepDelay/2);
    }

    /**
     * Get mainper position
     * @return mainper position
     */
    public Position getPosition(){
        return position;
    }

    /**
<<<<<<< HEAD
     * Sets the position of the mainper to p
=======
     * Sets the position of the trooper to position
     * Don't touch unless you are testing
>>>>>>> master
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /*
<<<<<<< HEAD:src/main/java/trooper/Trooper.java
     * @return true if the mainper has reached the goal
=======
     * @return true if the trooper has reached the squareGoal
>>>>>>> master:src/Trooper.java
     */
    public boolean getReachedGoal(){
        return reachedGoal;
    }

    /**
<<<<<<< HEAD:src/main/java/trooper/Trooper.java
     * Sets that the mainper has reached the goal
=======
     * Sets that the trooper has reached the squareGoal
>>>>>>> master:src/Trooper.java
     */
    public void setReachedGoal() {
        reachedGoal = true;
    }

    public boolean isReverse() {
        return reverse;
    }

    /**
     * Takes in a Hashtable of positions and returns a table containing possible tiles to move to
     * @param map_hashTable a table of tiles (a map)
     * @return a map of neighbouring roadtiles
     */
    public Hashtable<Position, RoadTile> getPossibleMoves(Hashtable<Position, Tile> map_hashTable){
        Hashtable<Position,RoadTile> possibleMovesTable = new Hashtable<>();
        Position keyPos = position.getPosToEast();
        if (RoadTile.class.isInstance(map_hashTable.get(keyPos))) {
            possibleMovesTable.put(keyPos, (RoadTile) map_hashTable.get(keyPos));
        }
        keyPos = position.getPosToNorth();
        if (RoadTile.class.isInstance(map_hashTable.get(keyPos))) {
            possibleMovesTable.put(keyPos, (RoadTile) map_hashTable.get(keyPos));
        }
        keyPos = position.getPosToSouth();
        if (RoadTile.class.isInstance(map_hashTable.get(keyPos))) {
            possibleMovesTable.put(keyPos, (RoadTile) map_hashTable.get(keyPos));
        }
        keyPos = position.getPosToWest();
        if (RoadTile.class.isInstance(map_hashTable.get(keyPos))) {
            possibleMovesTable.put(keyPos, (RoadTile) map_hashTable.get(keyPos));
        }
        return possibleMovesTable;
    }

    //TODO !
//    private void backTrace(Hashtable<mainers.Position,main.RoadTile> possibleMovesTable){
//        path.pop();
//        position = path.peek();
//        main.RoadTile road = possibleMovesTable.get(position);
//        road.landOn(this);
//    }

    /**
<<<<<<< HEAD
     * Finds the next main to move to and calls landOn for that main
=======
     * Takes a step every semiStep'th of the time that move is called
     * @param map_hashTable a map
     * @param preferred preferred direction for the army
     * @return the RoadTile that the trooper is on
     */
    public RoadTile move(Hashtable<Position, Tile> map_hashTable, Direction preferred) {
        RoadTile road;
        if (semiStep <= stepDelay) {
            road = (RoadTile) map_hashTable.get(position);
            semiStep++;
        } else {
            road = forceMove(map_hashTable, preferred);
            position=road.getPosition();
            semiStep = 0;
        }

        return road;
    }

    public boolean hasTurned() {
        return hasTurned;
    }

    /**
     * Finds the next main.java.main.java.tile to move to and calls landOn for that main.java.main.java.tile
>>>>>>> master
     * @param map_hashTable a map
     * @param preferred preferred direction of the army
     */
    public RoadTile forceMove(Hashtable<Position, Tile> map_hashTable, Direction preferred){
        Position nextPosition;
        Hashtable<Position, RoadTile> possibleMovesTable = getPossibleMoves(map_hashTable);
        for (Object o : possibleMovesTable.values()) {
            RoadTile tile = (RoadTile)o;
        }
        path.add(position);
        visited.add(position);
        if (preferred == direction &&
                possibleMovesTable.containsKey(position.getPosToDirection(direction)) &&
                !visited.contains(position.getPosToDirection(direction))){
            nextPosition = position.getPosToDirection(direction);
            reverse = false;

        } else if(preferred == Direction.RIGHT &&
                possibleMovesTable.containsKey(position.getPosToRight(direction)) &&
                !visited.contains(position.getPosToRight(direction))){
            nextPosition = position.getPosToRight(direction);
            reverse = false;
        }
        else if (preferred == Direction.LEFT &&
                possibleMovesTable.containsKey(position.getPosToLeft(direction)) &&
                !visited.contains(position.getPosToRight(direction))){
            nextPosition = position.getPosToLeft(direction);
            reverse = false;
        }
        else {
            nextPosition = getDefaultNextPosition(possibleMovesTable);
            reverse = false;
        }

        if(nextPosition == null || reverse) {
            System.out.println("Utskrift i Trooper.move: Gjorde ett backtrace steg " + position.toString());
            reverse = true;
            path.pop();
            position = path.peek();
            path.pop();
            System.out.println("Till "+ position.toString());
            nextPosition = position;
//            backTrace(possibleMovesTable);
        }
        RoadTile t = possibleMovesTable.get(nextPosition);
        setPosition(nextPosition);
        return t;
//        t.landOn(this);
    }

    /**
     * Brute force all directions except forwards //TODO this could be so much neater
     * @param possibleMovesTable a table of roadtiles
     * @return the first roadtile that was found clockwise starting in north
     */
    private Position getDefaultNextPosition(Hashtable<Position, RoadTile> possibleMovesTable) {
        Position nextPosition = null;
        if (!visited.contains(position.getPosToNorth()) && possibleMovesTable.containsKey(position.getPosToNorth())) {
            nextPosition = position.getPosToNorth();
        } else if (!visited.contains(position.getPosToEast()) && possibleMovesTable.containsKey(position.getPosToEast())) {
            nextPosition = position.getPosToEast();
        } else if (!visited.contains(position.getPosToSouth()) && possibleMovesTable.containsKey(position.getPosToSouth())) {
            nextPosition = position.getPosToSouth();
        } else if (!visited.contains(position.getPosToWest()) && possibleMovesTable.containsKey(position.getPosToWest())) {
            nextPosition = position.getPosToWest();
        }
        else nextPosition=null;
        return nextPosition;
    }
}
