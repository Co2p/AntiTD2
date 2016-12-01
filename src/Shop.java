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
            buttons[i] = new Rectangle();

        }
    }

    public void draw(Graphics gr){

    }


}
