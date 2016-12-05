import java.awt.*;

/**
<<<<<<<<< Temporary merge branch 1
 * Created by Simon on 2016-12-01.
 */
public class ShopButton extends Rectangle{

        public int id;
        public int x, y;


        public ShopButton(int x, int y, int width, int height, int id){
            setBounds(x,y,width,height);
            this.id = id;
            this.x = x;
            this.y = y;
        }


        public void draw(Graphics gr, int i){

                //draw the bound of the button rectangle
                gr.setColor(Color.black);
                gr.drawRect(x,y,width,height);

                //Draw the image on the "button"
                gr.drawImage(GamePanel.button_images[id],x, y, null);
        }

=========
 * Created by Daniel on 2016-12-05.
 */
public class ShopButton extends Rectangle {
    public int id;
    public int x, y;


    public ShopButton(int x, int y, int width, int height, int id){
        setBounds(x,y,width,height);
        this.id = id;
        this.x = x;
        this.y = y;
    }


    public void draw(Graphics gr, int i){

        gr.drawImage(GamePanel.button_images[id],x, y, null);
    }
>>>>>>>>> Temporary merge branch 2
}
