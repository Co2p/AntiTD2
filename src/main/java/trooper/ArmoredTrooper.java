package trooper;

/**
 * A Trooper that has a amazing armor that deflects any attack,
 * a little bit anyway
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 * id13gcr Gordon Cooper
 */
public class ArmoredTrooper extends Trooper {

    private int armor;
    private int zombieArmor;

    /**
     * Constructor for a ArmoredTrooper
     * @param hp amount of health the trooper has
     */
    public ArmoredTrooper(int hp) {
        super(hp);
        armor = 15;
    }

    /**
     * Constructor for a ArmoredTrooper
     * @param hp amount of health the trooper has
     * @param armor amount of armor the trooper has
     *              (calculated in %) ie 12 = 12% less damage
     */
    public ArmoredTrooper(int hp, int armor) {
        super(hp);
        this.armor = armor;
        zombieArmor = (armor/2);
    }

    /**
     * Removes damage from the damage that has been received
     * @param removeHealth amount of damage to the trooper
     *
     * id13gcr Gordon Cooper
     */
    @Override
    public void receiveDamage(int removeHealth){
        if(hasTurned()) {
            armor =  zombieArmor;
        }
        removeHealth = removeHealth - (removeHealth * armor/100);

        super.receiveDamage(removeHealth);
    }


    /**
     * Sets the armour strength
     * @param armor armour strength
     */
    public void setArmor(int armor) {
        this.armor = armor;
    }
}
