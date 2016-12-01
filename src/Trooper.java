

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




    //TODO
    public void move(Hashtable<Position, Tile> hashtable, Direction prefered){

        switch (direction){
            case NORTH:
                if(prefered==Direction.RIGHT){
                    Tile t = hashtable.get(position.getPosToEast());
                }
                else if (prefered==Direction.LEFT){
                    Tile t = hashtable.get(position.getPosToWest());
                }
                else {
                    Tile t = hashtable.get(position.getPosToNorth());

                    if ((t != null) && (RoadTile.class.isInstance(t)) && (visited.contains(position.getPosToNorth()))) {
                        visited.add(position);
                        setPosition(position.getPosToNorth());
                        (RoadTile) t.landOn(this);

                    }
                }

            case SOUTH:

            case EAST:

            case WEST:

        }

    }

}
