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

    public Shop(){

        buttons = new ShopButton[noOfButtons];
        define();

    }

    public void define(){

        for (int i = 0; i <buttons.length ; i++) {
            buttons[i] = new ShopButton((GamePanel.width/2) -
                    ((noOfButtons*buttonsize)/2) -((smallSpace*(buttons.length-1)) /2) + ((buttonsize + smallSpace)*i),
                    (GameContainer.rowCount * GameContainer.squareSize )
                            + largeSpace, buttonsize , buttonsize, i);
        }

    }


    //Do something better than just mouse button (left click)
    public void click(int mouseButton){

        if(mouseButton ==1){
            for (int i = 0; i <buttons.length ; i++) {

                if(buttons[i].contains(GamePanel.mousePoint)){

                    int j = i +1;
                    System.out.println("this is Button: " + j);
                }

            }

        }

    }

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
