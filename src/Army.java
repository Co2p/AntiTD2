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

    public Army (Hashtable<Position, Tile> map) {
        army = new ArrayList<>();
        armyQueue = new LinkedList<>();
        this.map = map;
    }

    public void createTrooper(TrooperType type) {
        switch (type) {
            case PITIFUL:
                armyQueue.add(new PitifulTrooper());
                break;
            case TELEPORTER:
                armyQueue.add(new TeleportTrooper(75));
                break;
            case ARMORED:
                ArmoredTrooper trooper = new ArmoredTrooper(150,1);
                trooper.setArmor(5);
                armyQueue.add(trooper);
                break;
        }
    }

    public Trooper getFromQueue() {
        return armyQueue.poll();
    }

    public void updateArmy() {
        army.add(armyQueue.poll());
        for (Trooper trooper: army) {
            if (!trooper.isDead()) {
                trooper.move(map, preferred);
                if (trooper.reachedGoal()) {
                    army.remove(trooper);
                    reachedGoal++;
                }
            } else {
                army.remove(trooper);
            }
        }
    }

    public void setPreferred(Direction preferred) {
        this.preferred = preferred;
    }
}
