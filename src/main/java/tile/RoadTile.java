package main.java.tile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import main.java.trooper.Trooper;
import main.java.helpers.Position;

/**
 * A main.java.tile that Troopers walk on
 * Created by gordon on 2016-11-28.
 */
public class RoadTile extends Tile implements Zone {
    private boolean isGoal;
    private RoadTile portalExit = null;
    private Object landOnModifier = null;
    private Method landOnMethod = null;
    private ArrayList<Trooper> troopers;

    /**
     * Constructor for main.java.tile.RoadTile
     * @param p, the position where the main.java.tile will be placed.
     * @param isGoal, if the main.java.tile is a goal (end main.java.tile)
     */
    public RoadTile(Position p, boolean isGoal) {
        super(p);
        this.isGoal = isGoal;
        troopers = new ArrayList<>();
    }

    /**
     * Constructor for main.java.tile.RoadTile, isGoal is set to false by default.
     * @param p, the position where the main.java.tile will be placed.
     */
    public RoadTile(Position p) {
        super(p);
        isGoal = false;
    }

    /**
     * Called when a main.java.trooper.Trooper lands on the main.java.tile.RoadTile
     * @param t the main.java.trooper.Trooper
     */
    @Override
    public void landOn(Trooper t) {
        if (isGoal) {
            t.setReachedGoal();
        } else if(portalExit != null) {
            t.setPosition(portalExit.getPosition());
            portalExit.landOn(t);
        } else {
            setChanged();
            if (t != null) {
                troopers.add(t);
                notifyObservers(troopers);
            }
            try {
                landOnMethod.invoke(landOnModifier,t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns true if the main.java.tile has a portal on it.
     * @return true if the main.java.tile has a portal on it.
     */
    public boolean hasPortal() {
        return (portalExit != null);
    }

    /**
     * Sets a portal on the main.java.tile
     * @param t the main.java.tile where the portal exits
     */
    public void setPortal(RoadTile t) {
        portalExit = t;
    }

    /**
     * Returns the exit of the portal on the main.java.tile else null.
     * @return the exit of the portal on the main.java.tile else null.
     */
    public Tile getPortalExit() {
        return portalExit;
    }

    /**
     * Returns true if the main.java.tile is a goal.
     * @return true if the main.java.tile is a goal.
     */
    public boolean isGoal() {
        return isGoal;
    }
}
