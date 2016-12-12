package tower;

import helpers.Position;
import sun.misc.PostVMInitHook;
import tile.RoadTile;
import tile.Tile;
import tile.TowerTile;

import java.util.*;

/**
 * Created by Alexander Nyström(dv15anm) on 05/12/2016.
 */
public class Defense {
    private ArrayList<Tower> towers = new ArrayList<>();
    private int spawnRate;

    public ArrayList<TowerTile> getTowerMap() {
        return towerMap;
    }

    private ArrayList<TowerTile> towerMap = new ArrayList<>();
    private Hashtable<Position, Tile> roadMap = new Hashtable<>();


    public Defense(Hashtable<Position, Tile> map, int spawnRate) {
        for(Tile tile: map.values()) {
            if (TowerTile.class.isInstance(tile)) {
                towerMap.add((TowerTile) tile);
            }
            if (RoadTile.class.isInstance(tile)) {
                roadMap.put(tile.getPosition(), tile);
            }
        }
        this.spawnRate = spawnRate;
    }

    public Position createTower() {
        Random rand = new Random();
        Position pos = null;
        if(rand.nextInt(100+1) <= spawnRate) {
            int towerPlacement;
            if (towerMap.size() > 1) {
                towerPlacement = rand.nextInt(towerMap.size());
            } else {
                towerPlacement = 0;
            }
            if (!towerMap.isEmpty()) {
                pos = towerMap.get(towerPlacement).getPosition();
                Tower tower = new LaserTower(roadMap, pos);
                towers.add(tower);
                towerMap.get(towerPlacement).setTower(tower);
                towerMap.remove(towerPlacement);
            }
        }
        return pos;
    }

    public void update() {
        if(towers.size() > 0 ) {
            for (Tower tower : towers) {
                tower.fire();
            }
        }
    }

    public int getTowerCount() {
        return towers.size();
    }

}
