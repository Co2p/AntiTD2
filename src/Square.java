import java.awt.*;

/**
 * Created by Simon on 2016-11-30.
 *
 * this class defines how a square on the gameboard looks for the player.
 */


public class Square extends Rectangle {

    public int id;

    public Square(int x, int y, int width, int height, int id){
        setBounds(x,y,width,height);
        this.id = id;
    }

    public void draw(Graphics gr){
        gr.drawRect(x,y,width,height);
    }

}
