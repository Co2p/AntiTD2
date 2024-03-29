package Game;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import helpers.*;
import trooper.*;


/**
 * Created by Simon on 2016-12-01. id13sel@cs.umu.se
 *
 * Class for the shop and it's containing elements
 */
public class Shop {

    public int buttonsize = 50;
    public static int noOfButtons = 6;
    public int noOfElements = 3;
    public int smallSpace = 3;
    public int largeSpace = 60;

    private long noOfCredits = 0;
    private int time = 0;
    private int unitsToWin = 0;
    private Army army;
    private Timer timer;
    private Clock clock;
    private boolean isRunning = true;

    public ShopButton[] buttons;
    public ShopButton[] statsElements;

    /**
     * Constructor of the shop.
     * Sets up an array of buttons and the army
     * @param army the ary of troopers
     */
    public Shop(Army army){
        this.army=army;
        buttons = new ShopButton[noOfButtons];
        statsElements = new ShopButton[noOfElements];
        timer = new Timer();
        clock = new Clock();
        timer.scheduleAtFixedRate(clock,0,1000);
        define();

    }

    /**
     * Method for defining the shop, the credits, units to win,
     * placement of buttons, stats elements and buttonsize.
     * */
    public void define(){

        noOfCredits = Game.level.getCredits();
        unitsToWin = Game.level.getUnitsToWin();

        for (int i = 0; i <buttons.length ; i++) {
            buttons[i] = new ShopButton((Game.width/2) -
                    ((noOfButtons*buttonsize)/2) -((smallSpace*
                    (buttons.length-1)) /2) + ((buttonsize + smallSpace)*i),
                    ((GameContainer.rowCount * GameContainer.squareSize )
                            + largeSpace), buttonsize , buttonsize, i);
        }

        for (int i = 0; i < statsElements.length ; i++) {
            //define where the statselement should be placed.
            statsElements[i] = new ShopButton(((Game.width/2)
                    - (GameContainer.columnCount*
                    GameContainer.squareSize )/2 + (((GameContainer.columnCount
                    *GameContainer.squareSize)/7)*3)*i ),
                    (GameContainer.rowCount * GameContainer.squareSize
                            ), buttonsize , buttonsize, i);
        }
    }

    /**
     * Takes a mousebutton upon method call. Uses mousepoint in Game-class to
     * find out which button index is being clicked
     * @param mouseButton the mousebutton index that was clicked
     * Finalized by Andreas(dv15ahn)
     */
    public void click(int mouseButton){

        if(mouseButton ==1){
            for (int i = 0; i <buttons.length ; i++) {
                //if click was registered on a button
                if(buttons[i].contains(Game.mousePoint)){
                    int j = i +1;

                    if(i == 0 && !(noOfCredits - Translator.pitifullPrice < 0)){
                        noOfCredits -= Translator.pitifullPrice;
                        army.addToArmyQueue(TrooperType.PITIFUL);

                    }
                    if(i == 1 && !(noOfCredits -
                            Translator.armoredTrooperPrice < 0)){
                        noOfCredits -= Translator.armoredTrooperPrice;
                        army.addToArmyQueue(TrooperType.ARMORED);
                    }
                    if(i == 2 && !(noOfCredits -
                            Translator.teleporterPrice < 0)){
                        noOfCredits -= Translator.teleporterPrice;
                        army.addToArmyQueue(TrooperType.TELEPORTER);
                    }
                    if(i==3){
                        for (Trooper t:army.getArmy()) {
                            if(t.getClass().equals(TeleportTrooper.class)){
                                TeleportTrooper tp = (TeleportTrooper)t;
                                if(tp.hasTeleport()) {
                                    Game.air[t.getPosition().getX()]
                                            [t.getPosition().getY()] =
                                            Translator.indexTeleportZone;
                                    tp.placePortal(tp.getDirection());
                                    Game.air[t.getPosition().getX()]
                                            [t.getPosition().getY()] =
                                            Translator.indexTeleporterZoneOut;
                                }
                            }
                        }
                    }
                    if(i==4){
                        if(army != null && army.getPreferred() == null
                                || army.getPreferred() == Direction.RIGHT) {
                            buttons[i].setIsSelected(true);
                            buttons[i+1].setIsSelected(false);
                            army.setPreferred(Direction.LEFT);
                        }
                        else if(army.getPreferred().equals(Direction.LEFT)){
                            buttons[i].setIsSelected(false);
                            army.setPreferred(null);
                        }
                    }
                    if(i==5){
                        if(army != null && army.getPreferred() == null
                                || army.getPreferred() == Direction.LEFT) {
                            army.setPreferred(Direction.RIGHT);
                            buttons[i].setIsSelected(true);
                            buttons[i-1].setIsSelected(false);
                        }
                        else if(army.getPreferred().equals(Direction.RIGHT)){
                            army.setPreferred(null);
                            buttons[i].setIsSelected(false);
                        }
                    }
                }
            }
        }
    }


    /**
     * Method to draw out the graphics of each button.
     * creates a rectangle outside the button and adds to the graphic
     * then calls the shopButtons draw method for each button.
     * @param gr , the graphics element to draw upon
     */
    public void draw(Graphics gr) {

        //Draw the buttons
        for (int i = 0; i < buttons.length; i++) {

            if(buttons[i].getIsSelected()){
                gr.setColor(Color.yellow);
                gr.fillRect((int)buttons[i].getX() , (int)buttons[i].getY()
                        , buttons[i].height, buttons[i].width);
            }
            else if(buttons[i].contains(Game.mousePoint)){
                gr.setColor(Color.red);
                gr.fillRect((int)buttons[i].getX() , (int)buttons[i].getY()
                        , buttons[i].height, buttons[i].width);
            }
                buttons[i].draw(gr, i);
        }

            //Draw the stats
        for (int i = 0; i < statsElements.length ; i++) {
            if(i == 0) {
                statsElements[i].drawStats(gr, i + 6, (int) noOfCredits);
            }else if (i ==1){
                statsElements[i].drawStats(gr, i + 6, time);
            }else if( i == 2){
                statsElements[i].drawStats(gr, i + 6, unitsToWin);
            }
        }
    }


    /**
     * Subtrackts units from unitsToWin
     * @param unitsToWin , number of units
     */
    public void subtractUnitsToWin(int unitsToWin) {
         this.unitsToWin -= unitsToWin;
    }

    /**
     * Refunds money depending on what type of trooper has reached the goal
     * and if he had turned to a zombie or not. If the trooper was a zombie
     * 200% will be refunded, if it was still human 100% will be refunded.
     * @param t the trooper that reached goal.
     */
    public void refund(Trooper t) {
        if(t.hasTurned()) {
            if(ArmoredTrooper.class.isInstance(t)) {
                noOfCredits = (long) (noOfCredits + (1.25 *
                        Translator.armoredTrooperPrice));
            } else if(PitifulTrooper.class.isInstance(t)) {
                noOfCredits = (long) (noOfCredits + (1.25 *
                        Translator.pitifullPrice));
            } else if(TeleportTrooper.class.isInstance(t)) {
                noOfCredits = (long) (noOfCredits + (1.25 *
                        Translator.teleporterPrice));
            }
        } else {
            if(ArmoredTrooper.class.isInstance(t)) {
                noOfCredits = (long) (noOfCredits + (0.75 *
                        Translator.armoredTrooperPrice));
            } else if(PitifulTrooper.class.isInstance(t)) {
                noOfCredits = (long) (noOfCredits + (0.75 *
                        Translator.pitifullPrice));
            } else if(TeleportTrooper.class.isInstance(t)) {
                noOfCredits = (long) (noOfCredits + (0.75 *
                        Translator.teleporterPrice));
            }
        }
    }

    /**
     * Returns the creditscount
     * @return noOfCredits, number of remaining credist
     */
    public long getNoOfCredits() {
        return noOfCredits;
    }

    /**
     * Returns playing time
     * @return time, the time
     *
     * Finalized by Alexander (dv15anm)
     */
    public int getTime() {
        return time;
    }

    /**
     * Used to count the seconds played
     *
     * Finalized by Alexander (dv15anm)
     */
    class Clock extends TimerTask
    {
        int counter;

        public Clock()
        {
            counter = 0;
        }

        public void run()
        {
            counter = time;
            counter++;
            time = counter;
        }
    }

    /**
     * Start the timer
     *
     * Finalized by Alexander (dv15anm)
     */
    public void startTime() {
        if (!isRunning) {
            timer = new Timer();
            clock = new Clock();
            timer.scheduleAtFixedRate(clock,0,1000);
            isRunning = true;
        }
    }

    /**
     * Stop the timer
     *
     * Finalized by Alexander (dv15anm)
     */
    public void stopTime() {
        if (isRunning) {
            timer.cancel();
            timer.purge();
        }
        isRunning = false;
    }
}

