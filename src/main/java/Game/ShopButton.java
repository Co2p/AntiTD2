package Game;

import java.awt.*;
import helpers.*;

/**
 * Created by Simon on 2016-12-01. id13sel@cs.umu.se
 *
 * Defines a shopButton
 */
public class ShopButton extends Rectangle{


        public int fontSize = 20;
        private int fontSizeButtons = 12;

        private boolean isSelected = false;
        public int id;
        public int x, y;
        public static int height, width;


    /**
     * Constructor of a shopButton
     * @param x Position x
     * @param y Position y
     * @param width width of the button
     * @param height height of the button
     * @param id    button id
     */
        public ShopButton(int x, int y, int width, int height, int id){
            setBounds(x,y,width,height);
            this.id = id;
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;

        }

    /**
     * Method to draw a button image, also draws a rectangle around the image
     *
     * @param gr The Graphics element to draw upon
     * @param i index to get the right image from the button array:
     *          button_images[]
     */
    public void draw(Graphics gr, int i){

                //draw the bound of the button rectangle
                gr.setColor(Color.black);
                gr.drawRect(x,y,width,height);
                //Draw the image on the "button"

                gr.drawImage(Game.button_images[i],x, y,width,height,null);
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

    /**
     * Method to draw the stats elements in the shop.
     * Stats elements use similar graphic layout as a bytton, but are not
     * clickable
     *
     * @param gr Gragpics element
     * @param i index to get the right image from the button array:
     * @param value a value to be presented together with a statselement
     */
        public void drawStats(Graphics gr, int i, int value){

            gr.drawImage(Game.button_images[i],x, y, null);

            int buttonXPos = x + width;
            int buttonYPos = y + (height/3)*2;
            gr.setColor(Color.black);
            gr.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
            gr.drawString(Integer.toString(value), buttonXPos, buttonYPos);
    }

    /**
     * Method to get if a button is selected
     *
     * @return isSelected boolean
     */
    public boolean getIsSelected(){
        return  isSelected;
    }

    /**
     * Method to set if a button is selected
     *
     * @param b boolean
     */
    public void setIsSelected(boolean b){
        isSelected = b;
    }

}
