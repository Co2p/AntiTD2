
public class Tower {
    private int damage;
    private int range;

    public Tower(int damage, int range){
        this.damage=damage;
        this.range=range;
    }

    public int fire(int damage){
        return damage;
    }
}
