package helpers;

import helpers.Direction;

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
     * Get the x from a helpers.Position.
     * @return x
     */
    public int getX(){
        return x;
    }

    /**
     * Get the y from a helpers.Position.
     * @return y
     */
    public int getY(){
        return y;
    }

    /**
     * Set/change x of a helpers.Position.
     * @param i the integer you want to set as x coordinate.
     */
    public void setX(int i){
        this.x=i;
    }

    /**
     * Set/change y of a helpers.Position.
     * @param j the integer you want to set as y coordinate.
     */
    public void setY(int j){
        this.y=j;
    }

    /**
     * Converts helpers.Direction into helpers.Position
     * @param direction the direction of travel
     * @return the position
     */
    public Position getPosToDirection(Direction direction) {
        switch (direction) {
            case NORTH:
                return getPosToNorth();
            case SOUTH:
                return getPosToSouth();
            case WEST:
                return getPosToWest();
            case EAST:
                return getPosToEast();
        }
        return this;
    }

    /**
     * Method to get the position to left according to
     * the direction youre facing.
     * @param direction
     * @return helpers.Position to the left.
     */
    public Position getPosToLeft(Direction direction) {
        switch (direction) {
            case NORTH:
                return getPosToWest();
            case SOUTH:
                return getPosToEast();
            case WEST:
                return getPosToSouth();
            case EAST:
                return getPosToNorth();
        }
        return this;
    }

    /**
     * Method to get the position to right according to
     * the direction youre facing.
     * @param direction
     * @return helpers.Position to the right.
     */
    public Position getPosToRight(Direction direction) {
        switch (direction) {
            case NORTH:
                return getPosToEast();
            case SOUTH:
                return getPosToWest();
            case WEST:
                return getPosToNorth();
            case EAST:
                return getPosToSouth();
        }
        return this;
    }

    /**
     * Get the helpers.Position to south of the current helpers.Position.
     * @return p
     */
    public Position getPosToSouth(){
        Position p = new Position(this.x,this.y);
        p.setX(this.x+1);
        return p;
    }

    /**
     * Get the helpers.Position to north of the current helpers.Position
     * @return p
     */
    public Position getPosToNorth(){
        Position p = new Position(this.x,this.y);
        p.setX(this.x-1);
        return p;
    }

    /**
     * Get the helpers.Position to west of the current helpers.Position.
     * @return p
     */
    public Position getPosToWest(){
        Position p = new Position(this.x,this.y);
        p.setY(this.y-1);
        return p;
    }

    /**
     * Get the helpers.Position to east of the current helpers.Position.
     * @return p
     */
    public Position getPosToEast(){
        Position p = new Position(this.x,this.y);
        p.setY(this.y+1);
        return p;
    }

    /**
     * Get the helpers.Position to north east of the current helpers.Position.
     * @return p
     */
    public Position getPosToNorthEast() {
        return getPosToNorth().getPosToEast();
    }

    /**
     * Get the helpers.Position to north west of the current helpers.Position.
     * @return p
     */
    public Position getPosToNorthWest() {
        return getPosToNorth().getPosToWest();
    }

    /**
     * Get the helpers.Position to south east of the current helpers.Position.
     * @return p
     */
    public Position getPosToSouthEast() {
        return getPosToSouth().getPosToEast();
    }

    /**
     * Get the helpers.Position to south west of the current helpers.Position.
     * @return p
     */
    public Position getPosToSouthWest() {
        return getPosToSouth().getPosToWest();
    }

    /**
     * Method to se if an position is out of range.
     * @return true if the position is out of rage, false if not.
     */
    public boolean outOfRange() {
        return x < 0 || y < 0;
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
     * Method to generate a distance between two positions.
     * @param p the position to searh from
     * @return the dinstance between two posiitons.
     */
    public double distance(Position p) {
        int xVert = Math.abs(p.getX() - x);
        int yVert = Math.abs(p.getY() - y);
        double dist = Math.sqrt(Math.pow(xVert, 2) + Math.pow(yVert, 2));
        return dist;
    }

    /**
     * Creates hashcode for a helpers.Position.
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = y;
        result = 31 * result + x;
        return result;
    }


}
