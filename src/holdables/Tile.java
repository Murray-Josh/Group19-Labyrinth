package holdables;

import constants.Angle;
import constants.TileType;
import core.Coordinate;
import core.Gameboard;
import javafx.scene.image.Image;
import players.Player;
import styles.Style;

import java.io.Serializable;
import java.util.ArrayList;

import static holdables.TileEffect.*;

/** A tile that can be placed on the gameboard or in the silk bag.
 *
 * @author Joseph Omar
 * @version 3.0
 */
public class Tile implements Serializable, Holdable {
    private Coordinate coordinate;
    private TileType   type;
    private  Style      style;
    private Angle     angle;
    private TileEffect effect = NONE;
    private boolean    fixed;


    /**
     * Constructs a new Holdables.Tile.
     *
     * @param coordinate Holdables.Tile's Core.Coordinate
     * @param type       Holdables.Tile's Holdables.TileType
     * @param style      Holdables.Tile's Styles.Style
     * @param angle      Holdables.Tile's Angle of rotation
     * @param fixed      If the is fixed or not
     */
    public Tile(Coordinate coordinate, TileType type, Style style, Angle angle, boolean fixed) {
        setCoordinate(coordinate);
        setType(type);
        setStyle(style);
        setAngle(angle);
        setFixed(fixed);
    }

    /**
     * Constructs a new Holdables.Tile.
     *
     * @param type  Holdables.Tile's Holdables.TileType
     * @param style Holdables.Tile's Styles.Style
     * @param angle Holdables.Tile's Angle of rotation
     * @param fixed If the is fixed or not
     */
    public Tile(TileType type, Style style, Angle angle, boolean fixed) {
        setType(type);
        setStyle(style);
        setAngle(angle);
        setFixed(fixed);
    }

    public Tile(Coordinate coordinate, TileType type, Angle angle, boolean fixed){
        this.coordinate=coordinate;
        this.type=type;
        this.angle = angle;
        this.fixed=fixed;
        this.style=null;
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


    public void makeFireEffect(){
        effect = FIRE;
    }

    public void makeIceEffect(){
        effect = ICE;
    }

    public TileEffect getEffect(){
        return effect;
    }

    public void setEffect(TileEffect effect) {
        this.effect = effect;
    }


    public Image getImage() {
        switch (this.getType().toString()) {

            case "CORNER":

                if (isFixed()) {
                    return Style.getCornerIce();
                } else {
                    return Style.getCornerTile();
                }
            case "JUNCTION":

                if (isFixed()) {
                    return Style.getJunctionIce();
                } else {
                    return Style.getJunctionTile();
                }
            case "STRAIGHT":

                if (isFixed()) {
                    return Style.getStraightIce();
                } else {
                    return Style.getStraightTile();
                }
            case "GOAL":

                return Style.getGoalTile();

            default:
                return null;
        }
    }
}
