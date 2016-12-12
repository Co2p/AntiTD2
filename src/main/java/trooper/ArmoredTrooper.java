package trooper;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class ArmoredTrooper extends Trooper {

    private int armor;
    private int zombieArmor;

    /**
     * 
     * @param hp
     * @param speed
     */
    public ArmoredTrooper(int hp, int speed) {
        super(hp, speed);
        armor = 15;
    }

    public ArmoredTrooper(int hp, int speed, int armor) {
        super(hp, speed);
        this.armor = armor;
        zombieArmor = (armor/2);
    }

    public void receiveDamage(int removeHealth){
        if(hasTurned()) {
            armor =  zombieArmor;
        }
        removeHealth = removeHealth - (removeHealth * armor/100);

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
