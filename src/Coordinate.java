/**
 * A 2 dimension grid coordinate consisting of an X and Y value
 *
 * @author Joseph Omar
 * @version 1.0
 */
public class Coordinate {
    private int x;
    private int y;

    /**
     * Constructs a new Coordinate object
     *
     * @param x x Coordinate
     * @param y y Coordinate
     */
    public Coordinate(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Gets the x coordinate
     *
     * @return x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Set the x coordinate
     *
     * @param x x Coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * gets the y coordinate
     *
     * @return y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Set the y coordinate
     *
     * @param y y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets both the x and y coordinates to new values
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
