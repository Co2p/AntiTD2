package trooper;

import Game.*;
import Game.GameContainer;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import helpers.Translator;
import javafx.geometry.Pos;
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
    private int stepDelay = 25;
    private int semiStep = 0;
    private int zombiestepDelay;
    private boolean hasTurned = false;
    private boolean isDead = false;
    private Direction direction;
    private Position position = null;
    private Position nextPosition = null;
    public  Position graphicPosition = new Position(0,0);
    private Stack<Direction> pathDirection = new Stack<>();
    private Stack<Position> path = new Stack<>();
    private ArrayList<Position> visited = new ArrayList<>();
    private boolean reverse = false;
    private boolean reachedGoal = false;
    private boolean firstStep = true;

    /**
     * Default constructor, crates a mainper.PitifulTrooper
     */
    public Trooper(){
        this.maxhealth=1000;
        this.zombiehealth=1000;
        this.health=1000;
        this.zombiestepDelay=(this.stepDelay);
        this.direction = EAST;
    }

    /**
     * Constructor that takes in a hp value
     * @param hp the defined hp for the mainper
     */
    public Trooper(int hp){
        this.maxhealth=hp;
        this.zombiehealth=hp;
        this.health=hp;
        this.zombiestepDelay=(this.stepDelay);
        this.direction = EAST;
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
            this.health = this.health - removeHealth;
            if(this.health < 1 ){
                hasTurned = true;
            }
        }
        else{
            this.zombiehealth = this.zombiehealth - removeHealth;
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

    public int getMaxhealth(){
        return maxhealth;
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

    public int getSemiStep(){
        return semiStep;
    }

    public void setSemiStep(int semiStep) {
        this.semiStep = semiStep;
    }

    /**
<<<<<<< HEAD
     * Set the speed of the mainper
     * @param speed speed
=======
     * Set the stepDelay of the Game.main.java.Game.main.java.trooper
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


    //TODO dessa två är nya. skapa tester
    public Position getGraphicPosition(){
        return graphicPosition;
    }

    public void setGraphicPosition(Position position){
        graphicPosition = position;
    }

    public Position getNextPosition(){
        return  nextPosition;
    }

    public void setNextPosition(Position p){
        nextPosition = p;
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
<<<<<<< HEAD:src/Game.main/java/trooper/Trooper.java
     * @return true if the mainper has reached the goal
=======
     * @return true if the trooper has reached the squareGoal
>>>>>>> master:src/Trooper.java
     */
    public boolean getReachedGoal(){
        return reachedGoal;
    }

    /**
<<<<<<< HEAD:src/Game.main/java/trooper/Trooper.java
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


    /**
<<<<<<< HEAD
     * Finds the next Game.main to move to and calls landOn for that Game.main
=======
     * Takes a step every semiStep'th of the time that move is called
     * @param map_hashTable a map
     * @param preferred preferred direction for the army
     * @return the RoadTile that the trooper is on
     */
    public RoadTile move(Hashtable<Position, Tile> map_hashTable, Direction preferred) {
        RoadTile road, road2;

        if (semiStep < stepDelay-1) {
            road = (RoadTile) map_hashTable.get(position);

            //Find out nexposition
            if(semiStep == 0) {
                road2 = (RoadTile)forceMove(map_hashTable, preferred);
                nextPosition = road2.getPosition();
            }
            if(!firstStep) {
                semiStep++;
            }else{
                firstStep = false;
            }
            return road;
        } else {
            System.out.println("forcemove");
             road = forceMove(map_hashTable, preferred);
            //position=road.getPosition();
            position = nextPosition;
            semiStep = 0;
        }
        return road;
    }

    public boolean hasTurned() {
        return hasTurned;
    }

    /**
     * Finds the next Game.main.java.Game.main.java.tile to move to and calls landOn for that Game.main.java.Game.main.java.tile
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
        pathDirection.add(getOppociteDirection(direction));
        System.out.println("path size: "+path.size()+" direction size: "+pathDirection.size());
        if(nextPosition == null || reverse) {
            System.out.println("Utskrift i Trooper.move: Gjorde ett backtrace steg " + position.toString());

            path.pop();
            pathDirection.pop();
            direction = pathDirection.peek();
            position = path.peek();
            path.pop();

            System.out.println("Till "+ position.toString());
            nextPosition = position;

            if(!reverse){
                pathDirection.pop();
            }
            reverse = true;

        }
        RoadTile t = possibleMovesTable.get(nextPosition);
        setPosition(nextPosition);
        return t;
    }

    private Direction getOppociteDirection(Direction direction){

        Direction d = null;

        switch (direction){
            case NORTH:
                d = SOUTH;
                break;
            case SOUTH:
                d = NORTH;
                break;
            case EAST:
                d = WEST;
                break;
            case WEST:
                d = EAST;
                break;
        }
        return d;
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
            direction = NORTH;
        } else if (!visited.contains(position.getPosToEast()) && possibleMovesTable.containsKey(position.getPosToEast())) {
            nextPosition = position.getPosToEast();
            direction = EAST;
        } else if (!visited.contains(position.getPosToSouth()) && possibleMovesTable.containsKey(position.getPosToSouth())) {
            nextPosition = position.getPosToSouth();
            direction = SOUTH;
        } else if (!visited.contains(position.getPosToWest()) && possibleMovesTable.containsKey(position.getPosToWest())) {
            nextPosition = position.getPosToWest();
            direction = WEST;
        }

        else nextPosition=null;

        return nextPosition;
    }
}
