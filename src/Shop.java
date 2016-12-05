import java.awt.*;

/**
 * Created by Simon on 2016-12-01.
 */
public class Shop {

    public int buttonsize = 50;
    public static int noOfButtons = 6;
    public int smallSpace = 3;
    public int largeSpace = 50;

    public ShopButton[] buttons;

    /**
     * Constructor of the shop.
     *
     * Sets up an array of buttons
     *
     * */
    public Shop(){
        buttons = new ShopButton[noOfButtons];
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

                //if the buttons in the shop are located where click was registered
                if(buttons[i].contains(GamePanel.mousePoint)){
                    int j = i +1;
                    System.out.println("Button: " + j + " was clicked");
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


        for (int i = 0; i < buttons.length; i++) {

            if(buttons[i].contains(GamePanel.mousePoint)){
                gr.setColor(Color.red);
                gr.fillRect(buttons[i].x, buttons[i].y, buttons[i].height, buttons[i].width);
            }
                buttons[i].draw(gr, i);
            }
    }

}
