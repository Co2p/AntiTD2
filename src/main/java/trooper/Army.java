package trooper;

import helpers.Direction;
import helpers.Position;
import tile.RoadTile;
import tile.Tile;

import java.util.*;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class Army {

    private List<Trooper> army;
    private LinkedList<TrooperType> armyQueue;
    private int reachedGoal = 0;
    private Hashtable<Position, Tile> map;
    private Direction preferred;
    private static int TELEPORTERHEALTH = 75;
    private static int ARMOREDHEALTH = 150;
    private int armySize =0;
    private Position startPosition;
    private ArrayList<Trooper> finished;


    /**
     * Constructor for the army object
     * @param map the active map
     * @param startPosition the position of the start tile
     */
    public Army (Hashtable<Position, Tile> map, Position startPosition) {
        army = Collections.synchronizedList(new ArrayList<Trooper>());
        armyQueue = new LinkedList<>();
        this.map = map;
        //TODO remove Print
        System.out.println("Map in constructor: " + map);
        this.startPosition = startPosition;
    }

    /**
     * Create a new trooper
     * @param type the type of trooper that will be created
     * @return A new trooper object
     */
    public Trooper createTrooper(TrooperType type) {

        Trooper t = new Trooper();

        switch (type) {
            case PITIFUL:
                t = new PitifulTrooper();
                break;
            case TELEPORTER:
                t = new TeleportTrooper(TELEPORTERHEALTH, map);
                break;
            case ARMORED:
                ArmoredTrooper tp = new ArmoredTrooper(ARMOREDHEALTH, 1);
                tp.setArmor(50);
                t = tp;
                break;
        }
        t.setPosition(startPosition);
        armySize++;
        return t;
    }

    /**
     * Adds a TrooperType Enum to the queue of troopers that will spawn
     * @param tt a TrooperType that will be spawned
     */
    public void addToArmyQueue(TrooperType tt) {
        armyQueue.add(tt);
    }

    public TrooperType getFromQueue() {
        return armyQueue.poll();
    }


    public void updateArmy() {
        reachedGoal = 0;
        finished = new ArrayList<>();
        if(!armyQueue.isEmpty()) {
            army.add(createTrooper(getFromQueue()));
        }
        if(armySize > 0) {
            Iterator<Trooper> iterator = army.iterator();
            while(iterator.hasNext()){
                Trooper trooper = iterator.next();
                if (!trooper.isDead()) {
                    RoadTile road = trooper.move(map, preferred);
                    System.out.println("Trooper "+trooper.getPosition());
                    road.landOn(trooper);
                    if (trooper.getReachedGoal()) {
                        if(trooper.hasTurned()) {
                            reachedGoal++;
                        }
                        finished.add(trooper);
                        iterator.remove();
                        armySize--;
                    }
                } else {
                    System.out.println("Im dead!");
                    iterator.remove();
                    armySize--;
                }
            }
        }
    }

    private void getArmyStartPos() {
        for (int i = 0; i < map.size(); i++) {
            Tile t = map.get(i);
            System.out.println("Map: " + map.get(i));
            if (t != null && t.getClass() == RoadTile.class) {
                RoadTile rT = (RoadTile) t;
                if (rT.isStart()) {
                    startPosition =  rT.getPosition();
                }
            }
        }
    }

    public int getArmySize() {
        return armySize;
    }

    public List<Trooper> getArmy() {
        return army;
    }

    /**
     * Sets the preferred direction for the army to move,
     * @param preferred enum telling the army if they should try to move left
     *                  or right before anything else.
     */
    public void setPreferred(Direction preferred) {
        this.preferred = preferred;
    }

    public int getReachedGoal() {
        return reachedGoal;
    }

    public ArrayList<Trooper> getFinished() {
        return finished;
    }
}
