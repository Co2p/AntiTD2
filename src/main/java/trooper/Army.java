package trooper;

import Game.GameContainer;
import helpers.Direction;
import helpers.Position;
import helpers.Translator;
import tile.RoadTile;
import tile.Tile;
import Game.Square;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class Army {

    private List<Trooper> army;
    private LinkedList<TrooperType> armyQueue;
    private int reachedGoal = 0;
    private Hashtable<Position, Tile> map;
    private Direction preferred;
    private static int TELEPORTERHEALTH = 750;
    private static int ARMOREDHEALTH = 1500;
    private int armySize =0;
    private Position startPosition;
    private ArrayList<Trooper> finished;


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
                t = new ArmoredTrooper(ARMOREDHEALTH);
                t.setArmor(500);
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
        reachedGoal = 0;
        finished = new ArrayList<>();
        if(!armyQueue.isEmpty()) {

            //Set the troopers graphical position to match start in gamecontainer
            int x = GameContainer.airSquares[startPosition.getX()][startPosition.getY()].getSquarePosition().getX();
            int y = GameContainer.airSquares[startPosition.getX()][startPosition.getY()].getSquarePosition().getY();
            Position p = new Position(x,y);
            Trooper t = createTrooper(getFromQueue());
            t.setGraphicPosition(p);
            army.add(t);        //Add trooper to army
        }
        if(armySize > 0) {
            Iterator<Trooper> iterator = army.iterator();
            while(iterator.hasNext()){
                Trooper trooper = iterator.next();
                if (!trooper.isDead()) {
                    RoadTile road = trooper.move(map, preferred);
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

    public void draw(Graphics g, Image[] square_air,  Square[][] sq) {
        for (Trooper t: army) {
            if(!t.isDead()) {

                //Translate trooper position from 1:0 to square positions.
                int value;

                int x = t.getGraphicPosition().getX();
                int y = t.getGraphicPosition().getY();
                System.out.println("X start =" +x);
                System.out.println("Y start =" +y);

                System.out.println("semistep is : " + t.getSemiStep());

                if (t.getSemiStep() == 0) {
                    x = sq[t.getPosition().getX()][t.getPosition().getY()].getSquarePosition().getX();
                    y = sq[t.getPosition().getX()][t.getPosition().getY()].getSquarePosition().getY();

                    System.out.println("X from logic =" + x);
                    System.out.println("Y from logic =" + y);
                    t.setGraphicPosition(new Position(x, y));
                }else {

                    Direction direction = t.getDirection();
                    switch (direction) {
                        case NORTH:
                            value = Math.round(Translator.squareSize / t.getstepDelay());
                            x = x - value;
                            t.setGraphicPosition(new Position(x, y));
                            break;
                        case SOUTH:
                            value = Math.round(Translator.squareSize / t.getstepDelay());
                            x = x + value;

                            System.out.println("X from graphics =" + x);
                            System.out.println("Y from graphics =" + y);
                            t.setGraphicPosition(new Position(x, y));
                            break;
                        case EAST:
                            value = Math.round(Translator.squareSize / t.getstepDelay());
                            y = y + value;
                            t.setGraphicPosition(new Position(x, y));
                            break;
                        case WEST:
                            value = Math.round(Translator.squareSize / t.getstepDelay());
                            y = y - value;
                            System.out.println("X from graphics =" + x);
                            System.out.println("Y from graphics =" + y);
                            t.setGraphicPosition(new Position(x, y));
                            break;
                    }
                }

//
//                int x = t.getGraphicPosition().getX();
//                int y = t.getGraphicPosition().getY();


                if (t.hasTurned()) {
                    g.drawImage(square_air[Translator.indexZombie], x, y,
                            null, null);
                } else {
                    g.drawImage(square_air[Translator.indexTrooper], x, y,
                            null, null);
                }
                System.out.println("Draw");
            }

            else {

            }
        }
    }

    public ArrayList<Trooper> getFinished() {
        return finished;
    }
}
