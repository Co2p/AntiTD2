package tile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Timer;

import helpers.Direction;
import trooper.Trooper;
import helpers.Position;

/**
 * A main that Troopers walk on
 * Created by gordon on 2016-11-28.
 */
public class RoadTile extends Tile implements Zone {
    private boolean isGoal = false;
    private boolean isStart = false;
    private RoadTile portalExit = null;
    private Direction exitDirection;
    private Object landOnModifier = null;
    private Method landOnMethod = null;
    private ArrayList<Trooper> troopers;
    private boolean gotLandOn = false;
    private Hashtable<Position, Tile> map;

    /**
     * Constructor for main.RoadTile
     * @param p, the position where the main will be placed.
     * Constructor for RoadTile
     * @param p, the position where the tile will be placed.
     */
    public RoadTile(Position p, String isGoalOrStart) {
        super(p);

        if(isGoalOrStart.equals("start")){
            this.isStart = true;
        }else if (isGoalOrStart.equals("goal")){
            this.isGoal = true;
        }
        initTroopersArray();
    }

    /**
     * Constructor for main.RoadTile, isGoal is set to false by default.
     * @param p, the position where the main will be placed.
     */
    public RoadTile(Position p) {
        super(p);
        isGoal = false;
        initTroopersArray();
    }

    /**
     * creates a new array of troopers standing on the tile
     */
    private void initTroopersArray() {
        troopers = new ArrayList<>();
    }

    /**
     * Called when a Trooper lands on the main.RoadTile
     * @param t the Trooper
     *
     * Finalized by Gordon (id13gcr) && Alexander (dv15anm) && Andreas (dv15ahn)
     */
    @Override
    public void landOn(Trooper t) {
        if (isGoal) {
            t.setReachedGoal();
        } else if(portalExit != null) {
            if (t.getDirection() != exitDirection) {
                t.setDirection(exitDirection);
            }
            t.setPosition(portalExit.getPosition());
            portalExit.landOn(t);
        } else {
            setChanged();
            if (t != null && countObservers() > 0) {
                troopers.add(t);
                notifyObservers(troopers);
                troopers.clear();
            }
            try {
                if(gotLandOn) {
                    landOnMethod.invoke(landOnModifier,t);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns true if the main has a portal on it.
     * @return true if the main has a portal on it.
     */
    public boolean hasPortal() {
        return (portalExit != null);
    }

    /**
     * Sets a portal on the main
     * @param t the main where the portal exits
     */
    public void setPortal(RoadTile t, Hashtable<Position,Tile> map) {
        portalExit = t;
        this.map = map;
    }

    /**
     * Returns the exit of the portal on the main else null.
     * @return the exit of the portal on the main else null.
     */
    public Tile getPortalExit() {
        return portalExit;
    }

    /**
     * Returns true if the main is a goal.
     * @return true if the main is a goal.
     */
    public boolean isGoal() {
        return isGoal;
    }

    /**
     * Returns true if the main is a start.
     * @return true if the main is a start.
     */
    public boolean isStart(){ return isStart;}

    /**
     *
     * @param exitDirection The direction to move after a trooper have stepped
     *                      through a portal
     *
     * Finalized by Alexander (dv15anm)
     */
    public void setExitDirection(Direction exitDirection) {
        this.exitDirection = exitDirection;
    }

    /**
     * Set landOn modifier that has been loaded through reflection
     * @param zone The instantiated class containing the landOn method
     * @param landOn The method in the zone object to be called on landOn
     *
     * @see helpers.ZoneLoader
     *
     * Finalized by Alexander (dv15anm)
     */
    public void setLandOnModifier(Object zone, Method landOn) {
        landOnModifier = zone;
        landOnMethod = landOn;
        gotLandOn = true;
    }
}
