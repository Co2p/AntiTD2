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


    public Army (Hashtable<Position, Tile> map, Position startPosition) {
        army = Collections.synchronizedList(new ArrayList<Trooper>());
        armyQueue = new LinkedList<>();
        this.map = map;
        System.out.println("Map in constructor: " + map);
        this.startPosition = startPosition;
    }

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
                t = new ArmoredTrooper(ARMOREDHEALTH, 1);
                t.setArmor(5000000);
                break;
        }
        t.setPosition(startPosition);
        armySize++;
        return t;
    }

    public void addToArmyQueue(TrooperType tt) {
        armyQueue.add(tt);
    }

    public TrooperType getFromQueue() {
        return armyQueue.poll();
    }


    public void updateArmy() {
        if(!armyQueue.isEmpty()) {
            army.add(createTrooper(getFromQueue()));
        }
        if(armySize > 0) {
            Iterator<Trooper> iterator = army.iterator();
            while(iterator.hasNext()){
                Trooper trooper = iterator.next();
                if (!trooper.isDead()) {
                    RoadTile road = trooper.move(map, preferred);
                    road.landOn(trooper);
                    if (trooper.getReachedGoal()) {
                        iterator.remove();
                        reachedGoal++;
                        armySize--;
                    }
                } else {
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
}
