package tile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
    private Object landOnModifier = null;
    private Method landOnMethod = null;
    private ArrayList<Trooper> troopers;
    private boolean gotLandOn = false;

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

    private void initTroopersArray() {
        troopers = new ArrayList<>();
    }

    /**
     * Called when a mainper.Trooper lands on the main.RoadTile
     * @param t the mainper.Trooper
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
            if (t != null && countObservers() > 0) {
                troopers.add(t);
                notifyObservers(troopers);
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
    public void setPortal(RoadTile t) {
        portalExit = t;
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

    public boolean isStart(){ return isStart;}

    public void setLandOnModifier(Object zone, Method landOn) {
        landOnModifier = zone;
        landOnMethod = landOn;
        gotLandOn = true;
    }
}
