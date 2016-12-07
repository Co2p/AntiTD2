package main.java.trooper;

import main.java.helpers.Direction;
import main.java.helpers.Position;
import main.java.tile.RoadTile;
import main.java.tile.Tile;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class Army {

    private ArrayList<Trooper> army;
    private LinkedList<Trooper> armyQueue;
    private int reachedGoal = 0;
    private Hashtable<Position, Tile> map;
    private Direction preferred;
    private static int TELEPORTERHEALTH = 75;
    private static int ARMOREDHEALTH = 150;


    public Army (Hashtable<Position, Tile> map) {
        army = new ArrayList<>();
        armyQueue = new LinkedList<>();
        this.map = map;
    }

    /**
     * Creates a main.java.trooper of given type.
     * @param type enum telling which main.java.trooper to create, pitiful, teleporter
     *             or armored main.java.trooper.
     */
    public void createTrooper(TrooperType type) {
        switch (type) {
            case PITIFUL:
                armyQueue.add(new PitifulTrooper());
                break;
            case TELEPORTER:
                armyQueue.add(new TeleportTrooper(TELEPORTERHEALTH,map));
                break;
            case ARMORED:
                ArmoredTrooper trooper = new ArmoredTrooper(ARMOREDHEALTH,1);
                trooper.setArmor(5);
                armyQueue.add(trooper);
                break;
        }
    }

    /**
     * Retrieves the main.java.trooper in front of the queue.
     * @return returns a main.java.trooper from the queue
     */
    public Trooper getFromQueue() {
        return armyQueue.poll();
    }

    /**
<<<<<<< HEAD:src/main/java/trooper/Army.java
     * Will take a newly created main.java.trooper from the queue and add it to the list
     * of active troopers, then it will move each main.java.trooper if they are alive and
     * check if they reach goal.
=======
     * Will take a newly created trooper from the queue and add it to the list
     * of active troopers, then it will move each trooper if they are alive and
     * check if they reach squareGoal.
>>>>>>> master:src/Army.java
     */
    public void updateArmy() {
        army.add(getFromQueue());
        for (Trooper trooper: army) {
            if (!trooper.isDead()) {
                RoadTile road = trooper.move(map, preferred);
                road.landOn(trooper);
                if (trooper.getReachedGoal()) {
                    army.remove(trooper);
                    reachedGoal++;
                }
            } else {
                army.remove(trooper);
            }
        }
    }

    /**
     * Sets the preferred direction for the army to move,
     * @param preferred enum telling the army if they should try to move left
     *                  or right before anything else.
     */
    public void setPreferred(Direction preferred) {
        this.preferred = preferred;
    }
}
