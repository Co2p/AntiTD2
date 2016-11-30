import java.util.Map;

public class Position {
    private int y;
    private int x;

    /**
     * Constructor for making a new position with specific x and y.
     * @param x the integer you want as X coordinate
     * @param y the integer you want as Y coordinate
     */
    public Position(int x, int y){
        this.y = y;
        this.x = x;
    }

    /**
     * Constructor for making a new position thats gets x=0 and y=0.
     */
    public Position(){
        this.y = 0;
        this.x = 0;
    }

    /**
     * Get the x from a Position.
     * @return x
     */
    public int getX(){
        return x;
    }

    /**
     * Get the y from a Position.
     * @return y
     */
    public int getY(){
        return y;
    }

    /**
     * Set/change x of a Position.
     * @param i the integer you want to set as x coordinate.
     */
    public void setX(int i){
        this.x=i;
    }

    /**
     * Set/change y of a Position.
     * @param j the integer you want to set as y coordinate.
     */
    public void setY(int j){
        this.y=j;
    }

    /**
     * Get the Position to south of the current Position.
     * @return p
     */
    public Position getPosToSouth(){
        Position p = new Position(this.x,this.y);
        p.setX(this.x+1);
        return p;
    }

    public double pythagoras(Position p) {
        int xVert = Math.abs(p.getX() - x);
        int yVert = Math.abs(p.getY() - y);
        double dist = Math.sqrt(Math.pow(xVert, 2) + Math.pow(yVert, 2));
        return dist;
    }

    /**
     * Get the Position to north of the current Position
     * @return p
     */
    public Position getPosToNorth(){
        Position p = new Position(this.x,this.y);
        p.setX(this.x-1);
        return p;
    }

    /**
     * Get the Position to west of the current Position.
     * @return p
     */
    public Position getPosToWest(){
        Position p = new Position(this.x,this.y);
        p.setY(this.y-1);
        return p;
    }

    /**
     * Get the Position to east of the current Position.
     * @return p
     */
    public Position getPosToEast(){
        Position p = new Position(this.x,this.y);
        p.setY(this.y+1);
        return p;
    }

    /**
     * Compares two Positions
     * @param o Objects to compare
     * @return True if equal, false if different
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return y == position.y && x == position.x;
    }

    /**
     * Creates hashcode for a Position.
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = y;
        result = 31 * result + x;
        return result;
    }


}
