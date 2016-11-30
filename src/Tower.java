import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Tower implements Observer {
    private int damage;
    private int range;
    private Position pos;
    private ArrayList<Position> neighbours = new ArrayList<>();

    public Tower(int damage, int range){
        this.damage=damage;
        this.range=range;    //Ska vi ens ha range?
    }

    public int fire(){
        return damage;
    }

    public int getRange() {
        return range;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

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
