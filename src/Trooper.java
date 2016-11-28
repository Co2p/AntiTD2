//Göra en move för alla troopers, eller låta varje trooper ovverrida och göra en egen move?


import java.util.Stack;

public class Trooper {
    private int maxhealth;
    private int health;
    private int speed;
    private String direction;
    private Position position = null;
   // private Stack<Position> visited;
    //private Tile previousTile;

    public Trooper(int hp){
        this.maxhealth=hp;
        this.health=hp;
        this.speed=1;
    }

    public Trooper(int hp, int speed){
        this.maxhealth = hp;
        this.health=hp;
        this.speed=speed;
    }

    public void receiveHealth(int addhealth){
        if((this.health + addhealth) >= this.maxhealth){
            this.health=this.maxhealth;
        }
        else {
            this.health = (this.health + addhealth);
        }
    }

    public void receiveDamage(int removehealth){
        this.health = (this.health - removehealth);
    }

    public boolean isDead(){
        if(this.health <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void setDirection(String direction){
        this.direction = direction;
    }

    public String getDirection(){
        return direction;
    }

    public int getHealth(){
        return health;
    }
    
    public void setHealth(int health){
        this.health=health;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed=speed;
    }

    public void moveTo(Position p){
        this.position=p;
    }

    public Position getPosition(){
        return position;
    }


    // Återstår att göra.
    /*public void move(){}

    public void reachedGoal(){
        //Set previousTile
    }

    public void setPreviousTile(Tile previousTile) {
        this.previousTile = previousTile;
    }

    public Tile getPreviousTile() {
        return previousTile;
    }
    */
}
