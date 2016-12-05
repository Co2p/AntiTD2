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

    public void drawBackground(Graphics gr, int indexX, int indexY){
        gr.drawImage(GamePanel.square_material[
                Character.getNumericValue(GamePanel.background[indexX][indexY])]
                , x,y, width, height, null);
        //gr.drawRect(x,y,width,height);
    }

    public void drawGraphics(Graphics gr, int indexX, int indexY){
        //gr.drawImage(GamePanel.square_air[1], x,y, width, height, null);

        gr.drawImage(GamePanel.square_air[
                        Character.getNumericValue(GamePanel.air[indexX][indexY])]
                , x,y, width, height, null);
        //gr.drawRect(x,y,width,height);
    }

}