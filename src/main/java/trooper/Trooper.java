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
     * Default constructor, crates a Trooper
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
     * @param hp the defined hp for the trooper
     */
    public Trooper(int hp){
        this.maxhealth=hp;
        this.zombiehealth=hp;
        this.health=hp;
        this.zombiestepDelay=(this.stepDelay);
        this.direction = EAST;
    }

    /**
     * Adds health (heals) the trooper
     * @param addHealth amount to heal the trooper with
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
     * @param removeHealth amount of damage to the trooper
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
    }

    /**
     * Returns true if the trooper is dead
     * @return true if the trooper is dead
     */
    public boolean isDead(){
        return isDead;
    }

    /**
     * Set the prioritized direction for the trooper
     * @param d a Direction (North, East, South, West)
     */
    public void setDirection(Direction d){
        if (d == Direction.NORTH || d == Direction.SOUTH || d == Direction.WEST || d == Direction.EAST) {
            this.direction = d;
        }
    }

    /**
     * Get current prioritized direction for the trooper
     * @return prioritized direction
     */
    public Direction getDirection(){
        return direction;
    }

    /**
     * Get the health of the trooper
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
     * method to get the maximum health of a trooper
     * @return maxhealth
     */
    public int getMaxhealth(){
        return maxhealth;
    }


    /**
     * Get the current stepdelay.
     * @return stepDelay if human, zombiestepDelay if zombie.
     */
    public int getstepDelay(){
        if(!hasTurned) {
            return stepDelay;
        }
        else{
            return zombiestepDelay;
        }
    }

    /**
     * Getter for semiStep
     * @return semiStep
     */
    public int getSemiStep(){
        return semiStep;
    }

    public void setSemiStep(int semiStep) {
        this.semiStep = semiStep;
    }

    /**
     * Set the stepDelay of the trooper
     * @param stepDelay new stepDelay
     */
    public void setstepDelay(int stepDelay){
        this.stepDelay=stepDelay;
        this.zombiestepDelay=(stepDelay/2);
    }

    /**
     * Get the troopers position
     * @return troopers position
     */
    public Position getPosition(){
        return position;
    }


    //TODO dessa två är nya. skapa tester

    /**
     * Returns the graphical position of the trooper.
     * @return graphicPosition.
     */
    public Position getGraphicPosition(){
        return graphicPosition;
    }

    /**
     * Set the graphical position of a trooper.
     * @param position
     */
    public void setGraphicPosition(Position position){
        graphicPosition = position;
    }

    //Todo ta bort?
    /**
     * Get what position is next.
     * @return nextPosition.
     */
    public Position getNextPosition(){
        return  nextPosition;
    }

    //Todo ta bort?
    /**
     * Set the nextPosition for a trooper.
     * @param p, next position.
     */
    public void setNextPosition(Position p){
        nextPosition = p;
    }

    /**
     * Sets the position of the trooper to position
     * Don't touch unless you are testing
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return true if the trooper has reached the goal
     */
    public boolean getReachedGoal(){
        return reachedGoal;
    }

    /**
     * Sets that the trooper has reached the goal
     */
    public void setReachedGoal() {
        reachedGoal = true;
    }

    /**
     * Method to get the boolean reverse
     * @return reverse
     */
    public boolean isReverse() {
        return reverse;
    }

    /**
     * Takes in a Hashtable of positions and returns a table containing
     * possible tiles to move to.
     * @param map_hashTable a table of tiles (a map)
     * @return a map of neighbouring roadtiles
     */
    public Hashtable<Position, RoadTile> getPossibleMoves(Hashtable<Position,
            Tile> map_hashTable){
        Hashtable<Position,RoadTile> possibleMovesTable = new Hashtable<>();
        Position keyPos = position.getPosToEast();
        if (RoadTile.class.isInstance(map_hashTable.get(keyPos))) {
            possibleMovesTable.put(keyPos,
                    (RoadTile) map_hashTable.get(keyPos));
        }
        keyPos = position.getPosToNorth();
        if (RoadTile.class.isInstance(map_hashTable.get(keyPos))) {
            possibleMovesTable.put(keyPos,
                    (RoadTile) map_hashTable.get(keyPos));
        }
        keyPos = position.getPosToSouth();
        if (RoadTile.class.isInstance(map_hashTable.get(keyPos))) {
            possibleMovesTable.put(keyPos,
                    (RoadTile) map_hashTable.get(keyPos));
        }
        keyPos = position.getPosToWest();
        if (RoadTile.class.isInstance(map_hashTable.get(keyPos))) {
            possibleMovesTable.put(keyPos,
                    (RoadTile) map_hashTable.get(keyPos));
        }
        return possibleMovesTable;
    }


    /**
     * Finds the next RoadTile to move to and calls landOn for that RoadTile
     * Takes a step every semiStep'th of the time that move is called
     * @param map_hashTable a map
     * @param preferred preferred direction for the army
     * @return the RoadTile that the trooper is on
     */
    public RoadTile move(Hashtable<Position, Tile> map_hashTable,
                         Direction preferred) {
        RoadTile road, road2=null;

        if (semiStep < stepDelay-1) {
            road = (RoadTile) map_hashTable.get(position);

            //Find out nexposition
            if(semiStep == 0) {
                road2 = (RoadTile)forceMove(map_hashTable, preferred);
                try {
                    nextPosition = road2.getPosition();
                }catch (NullPointerException e){
                    System.out.println("Trooper on position: X" +
                            position.getX() + "Y:" + position.getY()
                            +"threw a nullpointerexception");
                }
            }
           semiStep++;
            if(road2!=null) {
                return road2;
            }
        } else {
            road = (RoadTile) map_hashTable.get(position);
            nextPosition = road.getPosition();
            semiStep = 0;
        }
        return road;
    }

    /**
     * Method to see if a trooper has turned to a zombie.
     * @return hasTurned, true if zombie, false if human.
     */
    public boolean hasTurned() {
        return hasTurned;
    }

    /**
     * Finds the next RoadTile to move to and calls landOn for that RoadTile
     * @param map_hashTable a map
     * @param preferred preferred direction of the army
     */
    public RoadTile forceMove(Hashtable<Position, Tile> map_hashTable,
                              Direction preferred){
        Position nextPosition;
        Hashtable<Position, RoadTile> possibleMovesTable =
                getPossibleMoves(map_hashTable);

        if (preferred == null &&
                possibleMovesTable.containsKey(
                        position.getPosToDirection(direction)) &&
                        !visited.contains(position.getPosToDirection(
                                direction))){
            nextPosition = position.getPosToDirection(direction);
            reverse = false;

        } else if(preferred == Direction.RIGHT &&
                possibleMovesTable.containsKey(
                        position.getPosToRight(direction)) &&
                        !visited.contains(position.getPosToRight(
                                direction))){
            nextPosition = position.getPosToRight(direction);
            direction = getDirectionToRight(direction);
            reverse = false;
        }
        else if (preferred == Direction.LEFT &&
                possibleMovesTable.containsKey(
                        position.getPosToLeft(direction)) &&
                        !visited.contains(position.getPosToLeft(
                                direction))){

            nextPosition = position.getPosToLeft(direction);
            direction = getDirectionToLeft(direction);
            reverse = false;
        }
        else {
            nextPosition = getDefaultNextPosition(possibleMovesTable);
            reverse = false;
        }
        path.add(position);
        visited.add(position);
        pathDirection.add(getOppociteDirection(direction));

        if(nextPosition == null || reverse) {

            path.pop();
            pathDirection.pop();
            direction = pathDirection.peek();
            position = path.peek();
            path.pop();
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

    /**
     * Method to get the position to left according to
     * the direction youre facing.
     * @param direction
     * @return mainers.Position to the left.
     */
    public Direction getDirectionToLeft(Direction direction) {
        switch (direction) {
            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            case EAST:
                return NORTH;
        }
        return null;
    }

    /**
     * Method to get the position to right according to
     * the direction youre facing.
     * @param direction
     * @return mainers.Position to the right.
     */
    public Direction getDirectionToRight(Direction direction) {

        switch (direction) {
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            case EAST:
                return SOUTH;
        }
        return null;
    }

    /**
     * Method to get the opposite direction from where a trooper moves.
     * @param direction current direction
     * @return opposite direction.
     */
    public Direction getOppociteDirection(Direction direction){

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
    private Position getDefaultNextPosition(Hashtable<Position, RoadTile>
                                                    possibleMovesTable) {
        Position nextPosition = null;

        if(!visited.contains(position.getPosToDirection(direction)) &&
                possibleMovesTable.containsKey(position.getPosToDirection(
                        direction))){
            nextPosition = position.getPosToDirection(direction);
        }else if (!visited.contains(position.getPosToNorth()) &&
                possibleMovesTable.containsKey(position.getPosToNorth())) {
            nextPosition = position.getPosToNorth();
            direction = NORTH;
        }else if (!visited.contains(position.getPosToEast()) &&
                possibleMovesTable.containsKey(position.getPosToEast())) {
            nextPosition = position.getPosToEast();
            direction = EAST;
        } else if (!visited.contains(position.getPosToSouth()) &&
                possibleMovesTable.containsKey(position.getPosToSouth())) {
            nextPosition = position.getPosToSouth();
            direction = SOUTH;
        } else if (!visited.contains(position.getPosToWest()) &&
                possibleMovesTable.containsKey(position.getPosToWest())) {
            nextPosition = position.getPosToWest();
            direction = WEST;
        }

        else nextPosition=null;

        return nextPosition;
    }

    /**
     * Method to push positions and directions to the stack for
     * backtrack purposes
     * @param pos
     * @param dir
     */
    public void pushToBackTrack(Position pos, Direction dir) {

        path.push(pos);
        pathDirection.push(dir);
        visited.add(pos);
    }

}
