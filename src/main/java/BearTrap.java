import tile.Zone;
import trooper.Trooper;

import java.util.Random;

/**
 * Created by Alexander Nyström(dv15anm) on 12/12/2016.
 */
public class BearTrap implements Zone {

    public BearTrap(){

    }

    @Override
    public void landOn(Trooper t) {
        Random rand = new Random();

        if(rand.nextInt(100+1) < 5) {
            int damage = rand.nextInt(230)+20;
            t.receiveDamage(damage);
            //TODO remove Print
            System.out.println("Trooper was horibly mutilated by a bear trap, recieved " + damage + " damage");
        }
    }
}
