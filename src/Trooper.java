

import javafx.geometry.Pos;

import java.util.Hashtable;
import java.util.Stack;

public class Trooper {
    private int maxhealth;
    private int zombiehealth;
    private int health;
    private int speed;
    private int zombiespeed;
    private int armor;
    private boolean hasTurned = false;
    private boolean isDead = false;
    private Direction direction;
    private Position position = null;
    private Stack<Position> visited;
    private boolean moveEast;
    private boolean moveNorth;
    private boolean moveSouth;
    private boolean moveWest;

    public Trooper(){
        this.maxhealth=100;
        this.zombiehealth=100;
        this.health=100;
        this.speed=2;
        this.zombiespeed=(this.speed/2);
        this.armor=0;
    }

    public Trooper(int hp){
        this.maxhealth=hp;
        this.zombiehealth=hp;
        this.health=hp;
        this.speed=1;
        this.zombiespeed=(this.speed/2);
        this.armor=0;
    }

    public Trooper(int hp, int speed){
        this.maxhealth = hp;
        this.health=hp;
        this.speed=speed;
        this.zombiehealth=hp;
        this.zombiespeed=(this.speed/2);
        this.armor=0;

    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void receiveHealth(int addhealth){
        if(!hasTurned) {
            if ((this.health + addhealth) >= this.maxhealth) {
                this.health = this.maxhealth;
            } else {
                this.health = (this.health + addhealth);
            }
        }
        else{
            if ((this.zombiehealth + addhealth) >= this.maxhealth) {
                this.zombiehealth = this.maxhealth;
            } else {
                this.zombiehealth = (this.health + addhealth);
            }
        }
    }

    public void receiveDamage(int removehealth){
        if(!hasTurned) {
            this.health = (this.health - (removehealth-armor));
            if(this.health < 1 ){
                hasTurned = true;
            }
        }
        else{
            this.zombiehealth = (this.zombiehealth - (removehealth-(armor/2)));
            if(this.zombiehealth < 1){
                isDead = true;
            }
        }
    }

    public boolean isDead(){
        return isDead;
    }

    public void setDirection(Direction d){
        this.direction = d;
    }

    public Direction getDirection(){
        return direction;
    }

    public int getHealth(){
        if(!hasTurned) {
            return health;
        }
        else{
            return zombiehealth;
        }
    }

    public void setHealth(int health){
        this.health=health;
    }

    public int getSpeed(){
        if(!hasTurned) {
            return speed;
        }
        else{
            return zombiespeed;
        }
    }

    public void setSpeed(int speed){
        this.speed=speed;
        this.zombiespeed=(speed/2);
    }

    public void moveTo(Position p){
        this.position=p;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean reachedGoal(Boolean goal){
        if(goal){
            return true;
        }
        return false;
    }


    private void setPossibleMoves(Hashtable<Position,Tile> hashtable){
        Tile t = hashtable.get(position);
        if ((t != null) && (RoadTile.class.isInstance(t)) &&
                (visited.contains(position.getPosToEast()))) {
                moveEast=true;
        }
        else{
            moveEast=false;
        }
        if ((t != null) && (RoadTile.class.isInstance(t)) &&
                (visited.contains(position.getPosToNorth()))) {
            moveNorth=true;
        }
        else{
            moveNorth=false;
        }
        if ((t != null) && (RoadTile.class.isInstance(t)) &&
                (visited.contains(position.getPosToWest()))) {
            moveWest=true;
        }
        else{
            moveWest=false;
        }
        if ((t != null) && (RoadTile.class.isInstance(t)) &&
                (visited.contains(position.getPosToSouth()))) {
            moveSouth=true;
        }
        else {
            moveSouth = false;
        }
    }


    private void backTrace(){

    }

    private RoadTile moveToEast(Hashtable<Position,Tile> hashtable){
        RoadTile t;
        t= (RoadTile)hashtable.get(position.getPosToEast());
        visited.add(position);
        setPosition(position.getPosToEast());
        t.landOn(this);
        return t;
    }

    private RoadTile moveToWest(Hashtable<Position,Tile> hashtable){
        RoadTile t;
        t= (RoadTile)hashtable.get(position.getPosToWest());
        visited.add(position);
        setPosition(position.getPosToWest());
        t.landOn(this);
        return t;
    }

    private RoadTile moveToNorth(Hashtable<Position,Tile> hashtable){
        RoadTile t;
        t= (RoadTile)hashtable.get(position.getPosToNorth());
        visited.add(position);
        setPosition(position.getPosToNorth());
        t.landOn(this);
        return t;
    }
    private RoadTile moveToSouth(Hashtable<Position,Tile> hashtable){
        RoadTile t;
        t= (RoadTile)hashtable.get(position.getPosToSouth());
        visited.add(position);
        setPosition(position.getPosToSouth());
        t.landOn(this);
        return t;
    }



    //TODO
    public void move(Hashtable<Position, Tile> hashtable, Direction prefered){
        RoadTile t;
        setPossibleMoves(hashtable);

        switch (direction){
            case NORTH:
                if(prefered==Direction.RIGHT && moveEast ){
                    t = moveToEast(hashtable);
                }
                else if (prefered==Direction.LEFT && moveWest){
                    t = moveToWest(hashtable);
                }
                else {
                    if(moveNorth){
                        t = moveToNorth(hashtable);
                    }
                    else if(moveWest){
                        t = moveToWest(hashtable);
                    }else if(moveEast){
                        t = moveToEast(hashtable);
                    }
                    else backTrace();
                }
            case SOUTH:
                if(prefered==Direction.RIGHT && moveWest ){
                    t = moveToWest(hashtable);
                }
                else if (prefered==Direction.LEFT && moveEast){
                    t = moveToEast(hashtable);
                }
                else {
                    if(moveSouth){
                        t = moveToSouth(hashtable);
                    }
                    else if(moveWest){
                        t = moveToWest(hashtable);
                    }else if(moveEast){
                        t = moveToEast(hashtable);
                    }
                    else backTrace();
                }
            case EAST:
                if(prefered==Direction.RIGHT && moveSouth ){
                    t = moveToSouth(hashtable);
                }
                else if (prefered==Direction.LEFT && moveNorth){
                    t = moveToNorth(hashtable);
                }
                else {
                    if(moveEast){
                        t = moveToEast(hashtable);
                    }
                    else if(moveNorth){
                        t = moveToNorth(hashtable);
                    }else if(moveSouth){
                        t = moveToSouth(hashtable);
                    }
                    else backTrace();
                }
            case WEST:
                if(prefered==Direction.RIGHT && moveNorth ){
                    t = moveToNorth(hashtable);
                }
                else if (prefered==Direction.LEFT && moveSouth){
                    t = moveToSouth(hashtable);
                }
                else {
                    if(moveWest){
                        t = moveToWest(hashtable);
                    }
                    else if(moveNorth){
                        t = moveToNorth(hashtable);
                    }else if(moveSouth){
                        t = moveToSouth(hashtable);
                    }
                    else backTrace();
                }

        }

    }

}
