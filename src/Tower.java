import java.util.Observable;
import java.util.Observer;

public class Tower implements Observer {
    private int damage;
    private int range;

    public Tower(int damage, int range){
        this.damage=damage;
        this.range=range;
    }

    public int fire(int damage){
        return damage;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
