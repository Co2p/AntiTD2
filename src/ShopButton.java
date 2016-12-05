import java.awt.*;

/**
 * Created by Simon on 2016-12-01.
 */
public class ShopButton extends Rectangle{

        public int id;
        public int x, y;
        public static int height, width;


    /**
     * Constructor for a ShopButton
     *
     * takes the buttons position : X, Y
     * the width of a button, and an id.
     *
     * */
        public ShopButton(int x, int y, int width, int height, int id){
            setBounds(x,y,width,height);
            this.id = id;
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
        }

        /**
         * Method to draw the button with image.
         *
         * draw rect draws a rectangle around the button image.
         *
         * */
        public void draw(Graphics gr, int i){

                //draw the bound of the button rectangle
                gr.setColor(Color.black);
                gr.drawRect(x,y,width,height);

                //Draw the image on the "button"
                gr.drawImage(GamePanel.button_images[id],x, y, null);
        }
}
