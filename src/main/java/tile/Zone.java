package tile;

import trooper.Trooper;

public interface Zone {

    /**
     * A method that is called when a trooper lands on a tile
     * @param t the trooper that landed
     */
    public void landOn(Trooper t);
}
