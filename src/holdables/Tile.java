package holdables;

import constants.Angle;
import constants.TileType;
import core.Coordinate;
import core.Gameboard;
import javafx.scene.image.Image;
import styles.Style;

import java.io.Serializable;

import static holdables.TileEffect.*;

/**
 * A tile that can be placed on the gameboard or in the silk bag.
 *
 * @author Joseph Omar
 * @author Martin Samm
 * @author Joshua Murray
 * @version 3.0
 */
public class Tile implements Serializable, Holdable {
    private Coordinate coordinate;
    private TileType type;
    private Style style;
    private Angle angle;
    private TileEffect effect = NONE;
    private boolean fixed;


    /**
     * Constructs a new Tile.
     *
     * @param coordinate Tile's Coordinate
     * @param type       Tile's TileType
     * @param style      Tile's Styles.Style
     * @param angle      Tile's Angle of rotation
     * @param fixed      If the is fixed or not
     */
    public Tile(Coordinate coordinate, TileType type, Style style, Angle angle,
                boolean fixed) {
        setCoordinate(coordinate);
        setType(type);
        setStyle(style);
        setAngle(angle);
        setFixed(fixed);
    }

    /**
     * Constructs a new Tile without coordinate
     *
     * @param type  Tile's TileType
     * @param style Tile's Style
     * @param angle Tile's Angle of rotation
     * @param fixed If the is fixed or not
     */
    public Tile(TileType type, Style style, Angle angle, boolean fixed) {
        setType(type);
        setStyle(style);
        setAngle(angle);
        setFixed(fixed);
    }

    /**
     * Constructs a new Tile without style
     *
     * @param coordinate Tile's Coordinate
     * @param type       Tile's TileType
     * @param angle      Tile's Angle of rotation
     * @param fixed      If the is fixed or not
     */
    public Tile(Coordinate coordinate, TileType type, Angle angle,
                boolean fixed) {
        setCoordinate(coordinate);
        setType(type);
        setAngle(angle);
        setFixed(fixed);
    }

    /**
     * Constructs a new Tile without style or coordinate
     *
     * @param type  Tile's TileType
     * @param angle Tile's Angle of rotation
     * @param fixed If the is fixed or not
     */
    public Tile(TileType type, Angle angle, boolean fixed) {
        setType(type);
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
    public Angle getAngle() {
        return this.angle;
    }

    /**
     * Sets the Angle of rotation
     *
     * @param angle New angle
     */
    public void setAngle(Angle angle) {
        this.angle = angle;
    }

    public void makeFireEffect() {
        effect = FIRE;
    }

    public void makeIceEffect() {
        effect = ICE;
    }

    public TileEffect getEffect() {
        return effect;
    }

    public void setEffect(TileEffect effect) {
        this.effect = effect;
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
     * Gets tile north of this tile
     *
     * @param g Gameboard
     * @return Tile north of this tile
     * @throws ArrayIndexOutOfBoundsException Exception if out of array bound
     */
    public Tile getNorthTile(Gameboard g) throws ArrayIndexOutOfBoundsException {
        try {
            return g.getTiles()
                    .get(getCoordinate().getX(), getCoordinate().getY() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Gets tile south of this tile
     *
     * @param g Gameboard
     * @return Tile south of this tile
     * @throws ArrayIndexOutOfBoundsException Exception if out of array bound
     */
    public Tile getSouthTile(Gameboard g) throws ArrayIndexOutOfBoundsException {
        try {
            return g.getTiles()
                    .get(getCoordinate().getX(), getCoordinate().getY() + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Gets tile east of this tile
     *
     * @param g Gameboard
     * @return Tile east of this tile
     * @throws ArrayIndexOutOfBoundsException Exception if out of array bound
     */
    public Tile getEastTile(Gameboard g) throws ArrayIndexOutOfBoundsException {
        try {
            return g.getTiles()
                    .get(getCoordinate().getX() + 1, getCoordinate().getY());
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Gets tile west of this tile
     *
     * @param g Gameboard
     * @return Tile west of this tile
     * @throws ArrayIndexOutOfBoundsException Exception if out of array bound
     */
    public Tile getWestTile(Gameboard g) throws ArrayIndexOutOfBoundsException {
        try {
            return g.getTiles()
                    .get(getCoordinate().getX() - 1, getCoordinate().getY());
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Get image of tile from type
     *
     * @return Image of tile
     */
    public Image getImage() {
        switch (this.getType().toString()) {

            case "CORNER":
                return Style.getCornerTile();
            case "JUNCTION":
                return Style.getJunctionTile();
            case "STRAIGHT":
                return Style.getStraightTile();
            case "GOAL":
                return Style.getGoalTile();
            default:
                return null;
        }
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
}
