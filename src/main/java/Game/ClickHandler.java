package Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Simon on 2016-12-01. id13sel@cs.umu.se
 *
 * The mouseAdapter interface lets class use only some of the methods
 * in the mouseclick event handler. Some methods like " Mouse released " might
 * not be used, and is therefor redundant to declare.
 * remove unused methods before export.
 */
public class ClickHandler extends MouseAdapter {


    /**
     * Method to call clickmethod in shop when a mous event is registered.
     *
     * @param e mousevent
     */
    @Override
    public void mousePressed(MouseEvent e) {

        //call the clickmethod inside the shop
        Game.shop.click(e.getButton());
    }

    /**
     * Method to update Game.mousepoint when the cursor is moved by the user
     *
     * @param e Mouse event
     */
    @Override
    public void mouseMoved(MouseEvent e) {

        //find out where the mouse is on screen.
        Game.mousePoint = new Point(e.getX(), e.getY() - ShopButton.height);
    }
}
