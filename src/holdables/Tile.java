package holdables;

import constants.TileType;
import core.Coordinate;
import javafx.scene.image.Image;
import styles.Style;

import java.io.Serializable;

/*** A tile that can be placed on the gameboard or in the silk bag.
 *
 * @author Joseph Omar
 * @version 1.0
 */
public class Tile implements Serializable, Holdable {
    private Coordinate coordinate;
    private TileType type;
    private Style style;
    private double angle;
    private boolean fixed;
    private boolean onFire;

    /**
     * Constructs a new Holdables.Tile.
     *
     * @param coordinate Holdables.Tile's Core.Coordinate
     * @param type       Holdables.Tile's Holdables.TileType
     * @param style      Holdables.Tile's Styles.Style
     * @param angle      Holdables.Tile's Angle of rotation
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
     * Constructs a new Holdables.Tile.
     *
     * @param type       Holdables.Tile's Holdables.TileType
     * @param style      Holdables.Tile's Styles.Style
     * @param angle      Holdables.Tile's Angle of rotation
     * @param fixed      If the is fixed or not
     */
    public Tile(TileType type, Style style, double angle, boolean fixed) {
        setType(type);
        setStyle(style);
        setAngle(angle);
        setFixed(fixed);
    }

    /**
     * Makes the tile fixed
     */
    public void setFixed() {
        this.fixed = true;
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
     * Removes the Fire effect from the tile
     */
    public void removeFire() {
        this.onFire = false;
    }

    /**
     * Get's the tile's Core.Coordinate
     *
     * @return Core.Coordinate of tile
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    /**
     * Sets the Core.Coordinate
     *
     * @param coordinate New Core.Coordinate
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Gets the tile's Holdables.TileType
     *
     * @return Holdables.TileType of tile
     */
    public TileType getType() {
        return this.type;
    }

    /**
     * Sets the Holdables.TileType
     *
     * @param type New Holdables.TileType
     */
    public void setType(TileType type) {
        this.type = type;
    }

    /**
     * Gets the tile's Styles.Style
     *
     * @return Styles.Style of tile
     */
    public Style getStyle() {
        return this.style;
    }

    /**
     * Set's the Holdables.Tile's new Styles.Style
     *
     * @param style New Styles.Style
     */
    public void setStyle(Style style) {
        this.style = style;
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
     * Sets the Angle of rotation
     *
     * @param angle New angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
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
     * Sets the tile fixed value
     *
     * @param fixed Whether the tile is fixed or not
     */
    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    /**
     * Gets if the tile is on fire
     *
     * @return Fixed value of tile
     */
    public boolean isOnFire() {
        return this.onFire;
    }

    /**
     * Sets the tiles onFire value
     *
     * @param onFire Whether or not the tile is on fire
     */
    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }

    public Image getImage(){
        //String thisStyle = getStyle().toString();

        switch (this.getType().toString()) {
            case "holdables.CornerTile":
                return Style.getCornerTile();
            case "holdables.JunctionTile":
                return Style.getJunctionTile();
            case "holdables.StraightTile":
                return Style.getStraightTile();
            default:
                return null;
        }
    }


}
