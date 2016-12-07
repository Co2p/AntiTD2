import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Simon on 2016-12-01.
 *
 * The mouseAdapter interface lets class use only some of the methods
 * in the mouseclick event handler. Some methods like " Mouse released " might
 * not be used, and is therefor redundant to declare.
 * remove unused methods before export.
 */
public class ClickHandler extends MouseAdapter {


<<<<<<< HEAD
=======

>>>>>>> master
    @Override
    public void mousePressed(MouseEvent e) {

        //call the clickmethod inside the shop
        GamePanel.shop.click(e.getButton());
<<<<<<< HEAD
=======
        
>>>>>>> master

        System.out.println(GamePanel.mousePoint);
    }

    @Override
<<<<<<< HEAD
    public void mouseMoved(MouseEvent e) {
=======
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

>>>>>>> master
        //find out where the mouse is on screen.
        GamePanel.mousePoint = new Point(e.getX(), e.getY() - ShopButton.height/2);
    }
}
