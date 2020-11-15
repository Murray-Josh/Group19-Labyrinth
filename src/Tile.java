/*** A tile that can be placed on the gameboard or in the silk bag.
 *
 * @author Joseph Omar
 * @version 1.0
 */
public class Tile {
    private Coordinate coordinate;
    private TileType type;
    private Style style;
    private double angle;
    private boolean fixed;
    private boolean onFire;

    /**
     * Constructs a new Tile.
     *
     * @param coordinate Tile's Coordinate
     * @param type       Tile's TileType
     * @param style      Tile's Style
     * @param angle      Tile's Angle of rotation
     * @param fixed      If the is fixed or not
     */
    public Tile(Coordinate coordinate, TileType type, Style style, double angle, boolean fixed) {
        setCoordinate(coordinate);
        setType(type);
        setStyle(style);
        setAngle(angle);
        setFixed(fixed);
    }

    /**
     * Sets the Coordinate
     *
     * @param coordinate New Coordinate
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Sets the TileType
     *
     * @param type New TileType
     */
    public void setType(TileType type) {
        this.type = type;
    }

    /**
     * Set's the Tile's new Style
     *
     * @param style New Style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Sets the Angle of rotation
     *
     * @param angle New angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Makes the tile fixed
     */
    public void setFixed() {
        this.fixed = true;
    }

    /**
     * Sets the tile fixed value
     *
     * @param fixed Whether the tile is fixed or not
     */
    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    /**
     * makes the tile movable
     */
    public void setMovable() {
        this.fixed = false;
    }

    /**
     * Makes the tile be on fire
     */
    public void setOnFire() {
        this.onFire = true;
    }

    /**
     * Sets the tiles onFire value
     *
     * @param onFire Whether or not the tile is on fire
     */
    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }

    /**
     * Removes the Fire effect from the tile
     */
    public void removeFire() {
        this.onFire = false;
    }

    /**
     * Get's the tile's Coordinate
     *
     * @return Coordinate of tile
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    /**
     * Gets the tile's TileType
     *
     * @return TileType of tile
     */
    public TileType getType() {
        return this.type;
    }

    /**
     * Gets the tile's Style
     *
     * @return Style of tile
     */
    public Style getStyle() {
        return this.style;
    }

    /**
     * Gets the tile's angle of rotation
     *
     * @return Rotation angle of tile
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * Gets if the tile is fixed
     *
     * @return Fixed value of tile
     */
    public boolean isFixed() {
        return this.fixed;
    }

    /**
     * Gets if the tile is on fire
     *
     * @return Fixed value of tile
     */
    public boolean isOnFire() {
        return this.onFire;
    }


}
