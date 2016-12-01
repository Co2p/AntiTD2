import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Simon on 2016-12-01.
 */
public class ClickHandler implements MouseListener , MouseMotionListener{


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    GamePanel.shop.click(e.getButton());

        System.out.println("Button clicked");

        System.out.println(GamePanel.mousePoint);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        //find out where the mouse is on screen.
        
        GamePanel.mousePoint = new Point(e.getX(), e.getY());

    }
}
