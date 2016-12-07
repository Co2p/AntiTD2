import java.awt.*;

/**
<<<<<<< HEAD
<<<<<<<<< Temporary merge branch 1
=======
<<<<<<< HEAD
=======
<<<<<<<<< Temporary merge branch 1
>>>>>>> master
>>>>>>> clickHandler
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

        if((GamePanel.background[indexX][indexY])
                == Translator.squareGrass){
            gr.drawImage(GamePanel.square_material[Translator.squareGrass]
                    , x,y, width, height, null);
        }else if((GamePanel.background[indexX][indexY])
                == Translator.squareRoad){
            gr.drawImage(GamePanel.square_material[Translator.squareRoad]
                    , x,y, width, height, null);
        }

    }

    public void drawGraphics(Graphics gr, int indexX, int indexY){

        gr.drawImage(GamePanel.square_air[(GamePanel.air[indexX][indexY])]
                , x,y, width, height, null);
        //gr.drawRect(x,y,width,height);
    }

}
