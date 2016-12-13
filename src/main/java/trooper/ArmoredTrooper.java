package trooper;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class ArmoredTrooper extends Trooper {

    public ArmoredTrooper(int hp) {
        super(hp);
        setArmor(hp/2);
    }
}
