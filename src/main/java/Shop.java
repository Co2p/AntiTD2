

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import helpers.*;
import trooper.*;


/**
 * Created by Simon on 2016-12-01.
 */
public class Shop {

    public int buttonsize = 50;
    public static int noOfButtons = 6;
    public int noOfElements = 3;
    public int smallSpace = 3;
    public int largeSpace = 60;


    //Maybe this stats should not be declaired inside of the shop.
    private long noOfCredits = 0;
    private int time = 0;
    private int unitsToWin = 0;
    private Army army;
    private Timer timer;

    public ShopButton[] buttons;
    public ShopButton[] statsElements;

    /**
     * Constructor of the shop.
     *
     * Sets up an array of buttons
     *
     * */
    public Shop(Army army){
        this.army=army;
        buttons = new ShopButton[noOfButtons];
        statsElements = new ShopButton[noOfElements];
        timer = new Timer();
        timer.scheduleAtFixedRate(new MyCounterTask(),0,1000);
        define();

    }

    /**
     * Method for defining the shop, placement of buttons and buttonsize.
     * */
    public void define(){

        noOfCredits = Game.level.getCredits();
        unitsToWin = Game.level.getUnitsToWin();

        for (int i = 0; i <buttons.length ; i++) {
            buttons[i] = new ShopButton((Game.width/2) -
                    ((noOfButtons*buttonsize)/2) -((smallSpace*
                    (buttons.length-1)) /2) + ((buttonsize + smallSpace)*i),
                    (GameContainer.rowCount * GameContainer.squareSize )
                            + largeSpace, buttonsize , buttonsize, i);
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
     * Method called when a click is registered
     *
     * mousebutton 1 is left click on Mac.
     *
     * */
    
    //Do something better than just mouse button (left click)
    public void click(int mouseButton){

        if(mouseButton ==1){
            for (int i = 0; i <buttons.length ; i++) {

                //if click was registered on a button
                if(buttons[i].contains(Game.mousePoint)){
                    int j = i +1;
                    System.out.println("Button: " + j + " was clicked");

                    if(i == 0 && !(noOfCredits - Translator.pitifullPrice < 0)){
                        noOfCredits -= Translator.pitifullPrice;
                        army.addToArmyQueue(TrooperType.PITIFUL);

                    }
                    if(i == 1 && !(noOfCredits - Translator.armoredTrooperPrice < 0)){
                        noOfCredits -= Translator.armoredTrooperPrice;
                        army.addToArmyQueue(TrooperType.ARMORED);
                    }
                    if(i == 2 && !(noOfCredits - Translator.teleporterPrice < 0)){
                        noOfCredits -= Translator.teleporterPrice;
                        army.addToArmyQueue(TrooperType.TELEPORTER);
                    }
                    if(i==3){
                        //trooper move.
                    }
                    if(i==4){
                        //setprefered left
                    }
                    if(i==5){
                        //set prefered right.
                    }
                    else{
                        //SLUT PÅ CASH!
                    }
                }
            }
        }
    }


    /**
     * Method to draw out the graphics of each button.
     *
     * creates a rectangle outside the button and adds to the graphic
     * then calls the shopButtons draw method for each button.
     *
     * */
    public void draw(Graphics gr) {


        //Draw the buttons
        for (int i = 0; i < buttons.length; i++) {

            if(buttons[i].contains(Game.mousePoint)){
                gr.setColor(Color.red);
                gr.fillRect(buttons[i].x, buttons[i].y, buttons[i].height,
                        buttons[i].width);
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
                noOfCredits = noOfCredits + (2 * Translator.armoredTrooperPrice);
            } else if(PitifulTrooper.class.isInstance(t)) {
                noOfCredits = noOfCredits + (2 * Translator.pitifullPrice);
            } else if(TeleportTrooper.class.isInstance(t)) {
                noOfCredits = noOfCredits + (2 * Translator.teleporterPrice);
            }
        } else {
            if(ArmoredTrooper.class.isInstance(t)) {
                noOfCredits = noOfCredits + Translator.armoredTrooperPrice;
            } else if(PitifulTrooper.class.isInstance(t)) {
                noOfCredits = noOfCredits + Translator.pitifullPrice;
            } else if(TeleportTrooper.class.isInstance(t)) {
                noOfCredits = noOfCredits + Translator.teleporterPrice;
            }
        }
    }

    class MyCounterTask extends TimerTask
    {
        int counter;

        public MyCounterTask()
        {
            counter = 0;
        }

        public void run()
        {
            time = counter;
            counter++;
        }
    }

    public void stopTime() {
        timer.cancel();
        timer.purge();
    }
}

