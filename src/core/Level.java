package core;

import constants.Angle;
import constants.TileType;
import holdables.Tile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a Level that can be used in the game
 *
 * @author Joseph Omar
 * @author Martin Samm
 * @author Joshua Murray
 * @version 2.0
 */
public class Level implements Serializable {

    private ArrayList<Tile> fixed;
    private int             movables;
    private int             width;
    private int              height;

    /**
     * Constructs an empty {@link Level}
     */
    public Level() {
        this.width=0;
        this.height=0;
        this.movables =0;
        this.fixed=new ArrayList<Tile>();
    }

    /**
     * Constructs a fully specified level
     * @param fixed Collection of fixed tiles
     * @param width Width of the board
     * @param height Height of the board
     * @param movables Number of movable tiles
     */
    public Level(ArrayList<Tile> fixed, int width, int height, int movables) {
        this.width=width;
        this.height=height;
        this.movables =movables;
        this.fixed=fixed;
    }

    /**
     * Constuts a partially specified level
     * @param width Width of the board
     * @param height Height of the board
     * @param movables Number of movable tiles
     */
    public Level(int width, int height, int movables) {
        this.width=width;
        this.height=height;
        this.movables =movables;
        this.fixed=new ArrayList<Tile>();
    }

    /**
     * Constructs a partially specified level
     * @param width Width of the board
     * @param height Height of the board
     */
    public Level(int width, int height) {
        this.width=width;
        this.height=height;
        this.movables =0;
        this.fixed=new ArrayList<Tile>();
    }

    /**
     * Adds a collection of fixed tiles
     *
     * @param fixed {@link Collection} of fixed tiles
     */
    public void addFixed(ArrayList<Tile> fixed) {
        this.fixed.addAll(fixed);
    }

    /**
     * Adds a fixed {@link Tile}
     *
     * @param fixed Tile to be added
     */
    public void addFixed(Tile fixed) {
        this.fixed.add(fixed);
    }

    /**
     * Adds a fixed Tile
     *
     * @param type       {@link TileType} of the {@link Tile}
     * @param coordinate {@link Coordinate} of the tile
     * @param angle      {@link Angle} of the {@link Tile}
     */
    public void addFixed(TileType type, Coordinate coordinate, Angle angle) {
        this.fixed.add(new Tile(coordinate, type, angle, true));
    }

    /**
     * Gets all of the Fixed Tiles
     *
     * @return Fixed Tiles
     */
    public ArrayList<Tile> getFixed() {
        return this.fixed;
    }

    /**
     * Sets the fixed tiles collection to one specified
     *
     * @param fixed Collection of fixed tiles
     */
    public void setFixed(ArrayList<Tile> fixed) {
        this.fixed = fixed;
    }

    /**
     * Gets the tile an index
     *
     * @param index index of the tile
     *
     * @return The tile at index
     * @throws IndexOutOfBoundsException If the index is too big or too small
     */
    public Tile getFixed(int index) throws IndexOutOfBoundsException {
        return this.fixed.get(index);
    }

    /**
     * If tile exists as a fixed tile
     *
     * @param tile Tile to check
     *
     * @return If the tile is fixed
     */
    public boolean fixedExists(Tile tile) {
        return this.fixed.contains(tile);
    }

    /**
     * Sets the board size
     *
     * @param width  Width of the board
     * @param height Height of the board
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the size of the board
     *
     * @return width, height
     */
    public int[] getSize() {
        return new int[]{this.width, this.height};
    }

    /**
     * Gets the board width
     *
     * @return board width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the board width
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the board height
     *
     * @return board height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the board Height
     *
     * @param height Value for height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the number of fixed tiles
     *
     * @return Number of fixed Tiles
     */

    public int fixedAmount() {
        return this.fixed.size();
    }

}
