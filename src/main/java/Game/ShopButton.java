package Game;

import java.awt.*;
import helpers.*;

/**
 * Created by Simon on 2016-12-01.
 */
public class ShopButton extends Rectangle{


        public int fontSize = 20;
        private int fontSizeButtons = 12;

        public int id;
        public int x, y;
        public static int height, width;

    /**
     * Constructor for a Game.ShopButton
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

                gr.drawImage(Game.button_images[i],x, y,null);
            gr.setColor(Color.red);
            gr.setFont(new Font("TimesRoman", Font.BOLD, fontSizeButtons));
            if(i==0){
                gr.drawString(Integer.toString(Translator.pitifullPrice), x, y);
            }
            if(i==1){
                gr.drawString(Integer.toString(Translator.armoredTrooperPrice), x, y);
            }
            if(i==2){
                gr.drawString(Integer.toString(Translator.teleporterPrice), x, y);
            }


        }

        public void drawStats(Graphics gr, int i, int value){

            gr.drawImage(Game.button_images[i],x, y, null);

            int buttonXPos = x + width;
            int buttonYPos = y + (height/3)*2;
            gr.setColor(Color.black);
            gr.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
            gr.drawString(Integer.toString(value), buttonXPos, buttonYPos);
    }

}