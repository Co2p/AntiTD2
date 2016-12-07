import java.awt.*;
import java.sql.Time;


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

    private int noOfCredits = 1000;
    private int noOfRed = 500;
    private int noOfGreen = 20;

    public ShopButton[] buttons;
    public ShopButton[] statsElementses;


    /**
     * Constructor of the shop.
     *
     * Sets up an array of buttons
     *
     * */
    public Shop(){
        buttons = new ShopButton[noOfButtons];
        statsElementses = new ShopButton[noOfElements];
        define();

    }

    /**
     * Method for defining the shop, placement of buttons and buttonsize.
     * */
    public void define(){



        for (int i = 0; i <buttons.length ; i++) {
            buttons[i] = new ShopButton((GamePanel.width/2) -
                    ((noOfButtons*buttonsize)/2) -((smallSpace*(buttons.length-1)) /2) + ((buttonsize + smallSpace)*i),
                    (GameContainer.rowCount * GameContainer.squareSize )
                            + largeSpace, buttonsize , buttonsize, i);
        }



        for (int i = 0; i <statsElementses.length ; i++) {

            //define where the statselement should be placed.
            statsElementses[i] = new ShopButton(((GamePanel.width/2) - (GameContainer.columnCount*
                    GameContainer.squareSize )/2 + (((GameContainer.columnCount*GameContainer.squareSize)/7)*3)*i ),
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
                if(buttons[i].contains(GamePanel.mousePoint)){
                    int j = i +1;

                    System.out.println("Button: " + j + " was clicked");

                    if(i == 0 && noOfCredits!= 0){
                        noOfCredits -= 20;
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

            if(buttons[i].contains(GamePanel.mousePoint)){
                gr.setColor(Color.red);
                gr.fillRect(buttons[i].x, buttons[i].y, buttons[i].height, buttons[i].width);
            }


                buttons[i].draw(gr, i);
            }

            //Draw the stats
        for (int i = 0; i <statsElementses.length ; i++) {
            if(i == 0) {
                statsElementses[i].drawStats(gr, i + 6, noOfCredits);
            }else if (i ==1){
                statsElementses[i].drawStats(gr, i + 6, (int) System.nanoTime());
            }else if( i == 2){
                statsElementses[i].drawStats(gr, i + 6, noOfGreen);
            }


        }
    }
}
