import java.awt.*;

/**
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

                gr.drawImage(GamePanel.button_images[id],x, y, null);
        }

}
