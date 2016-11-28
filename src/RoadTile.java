import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 *
 * Created by gordon on 2016-11-28.
 */
public class RoadTile extends Tile implements Zone, Observable {
    private Trooper trooper;
    private boolean isGoal = false;

    public RoadTile(Position p) {
        super(p);
    }

    @Override
    public void landOn(Trooper t) {

    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    public void moveTo() {

    }

    public void moveFrom() {

    }

    public boolean hasPortal() {
        return false;
    }

    public void setPortal() {

    }

    public Tile getPortalExit() {
        return this; //TODO eeh
    }

    public void setGoal() {
        isGoal = true;
    }

    public boolean isGoal() {
        return isGoal;
    }
}
