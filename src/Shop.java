import java.awt.*;

/**
 * Created by Simon on 2016-12-01.
 */
public class Shop {

    public int buttonsize = 50;
    public int noOfButtons = 6;
    public int smallSpace = 3;
    public int LargeSpace = 12;

    public Rectangle[] buttons;

    public Shop(){

        buttons = new Rectangle[noOfButtons];
        define();

    }

    public void define(){
        for (int i = 0; i <buttons.length ; i++) {
            buttons[i] = new Rectangle((GamePanel.width/2) - ((noOfButtons*buttonsize)/2) + (buttonsize*i),
            15, buttonsize, 0);

        }
    }

    public void draw(Graphics gr){

        for (int i = 0; i < buttons.length ; i++) {

            gr.fillRect(buttons[i].x, buttons[i].y, buttons[i].width, buttons[i].height);

        }

    }


}
