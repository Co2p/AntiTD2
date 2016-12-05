
public class TowerTile extends Tile {
    private Tower tower;


    /**
     * Constructor to create a Towertile.
     * @param p The Position that the tile will be placed on.
     */
    public TowerTile(Position p){
        super(p);
    }


    /**
     * Return the tower on the towertile.
     * @return tower
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * Add a tower on the towertile.
     * @param tower the tower to be placed.
     */
    public void setTower(Tower tower) {
        this.tower = tower;
    }
}
