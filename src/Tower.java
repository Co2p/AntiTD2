import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Superclass for any tower class
 */
public class Tower implements Observer {
    protected int damage;
    protected int range;
    protected Position pos;
    protected ArrayList<Position> neighbours = new ArrayList<>();

    /**
     * Super tower constructor, called by the sub-tower classes
     * @param damage tower damage
     * @param range tower range
     */
    public Tower(int damage, int range){
        this.damage=damage;
        this.range=range;    //Ska vi ens ha range?
    }

    /**
     * fires the tower and returns the tower damage, overridden by subclasses
     * @return tower damage
     */
    public int fire(){
        return damage;
    }

    /**
     * Get tower range
     * @return tower range
     */
    public int getRange() {
        return range;
    }

    /**
     * Position of the tower
     * @return tower position
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Set the tower position
     * @param pos a new tower position
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     *
     */
    public void addNeighbours(){
        int x= pos.getX();
        int y = pos.getY();
        for(int i=1; i<=range; i++){
            neighbours.add(new Position(x+i,y+i));
            neighbours.add(new Position(x,y+i));
            neighbours.add(new Position(x+i,y));
            neighbours.add(new Position(x-i,y-i));
            neighbours.add(new Position(x,y-i));
            neighbours.add(new Position(x-i,y));
            neighbours.add(new Position(x+i,y-i));
            neighbours.add(new Position(x-i,y+i));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Trooper t = (Trooper)arg;
        if(inRange(t.getPosition())){
            t.receiveDamage(fire());
        }
    }

    public boolean inRange(Position position) {
        for(int i =0; i<neighbours.size(); i++){
            if(position.equals(neighbours.get(i))){
                return true;
            }
        }
        return false;
    }
}
