package trooper;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class ArmoredTrooper extends Trooper {

    private int armor;

    public ArmoredTrooper(int hp, int speed) {
        super(hp, speed);
        setArmor(15);
    }

    public void receiveDamage(int removeHealth){
        if(hasTurned())
        {
            removeHealth = Math.abs(removeHealth - (armor/2));
        } else {
            removeHealth = Math.abs(removeHealth - armor);
        }

        super.receiveDamage(removeHealth);
        //TODO REMOVE SOUT
        System.out.println("Target hp: " + getHealth());
    }


    /**
     * Sets the armour strength
     * @param armor armour strength
     */
    public void setArmor(int armor) {
        this.armor = armor;
    }
}
