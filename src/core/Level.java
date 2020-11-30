package core;

import constants.Angle;
import constants.TileType;
import holdables.Holdable;
import holdables.Tile;

import java.io.Serializable;
import java.lang.reflect.Array;
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
    private ArrayList<Tile> movables;

    private int             width;
    private int              height;
    private Coordinate playerOnePosition;
    private Coordinate playerTwoPosition;
    private Coordinate playerThreePosition;
    private Coordinate playerFourPosition;


    /**
     * Constructs an empty {@link Level}
     */
    public Level() {
        this.width=0;
        this.height=0;
        this.movables=new ArrayList<Tile>();
        this.fixed=new ArrayList<Tile>();
        this.playerOnePosition = new Coordinate(0,0);
        this.playerTwoPosition = new Coordinate(0,0);
        this.playerThreePosition = new Coordinate(0,0);
        this.playerFourPosition = new Coordinate(0,0);

    }

    /**
     * Constructs a fully specified level
     * @param fixed Collection of fixed tiles
     * @param width Width of the board
     * @param height Height of the board
     * @param movables Number of movable tiles
     */
    public Level(ArrayList<Tile> fixed, int width, int height, ArrayList<Tile> movables, Coordinate p1Pos, Coordinate p2Pos, Coordinate p3Pos, Coordinate p4Pos) {
        this.width=width;
        this.height=height;
        this.movables =movables;
        this.fixed=fixed;
        this.playerOnePosition = p1Pos;
        this.playerTwoPosition = p2Pos;
        this.playerThreePosition = p3Pos;
        this. playerFourPosition = p4Pos;
    }

    /**
     * Constuts a partially specified level
     * @param width Width of the board
     * @param height Height of the board
     * @param movables Number of movable tiles
     */
    public Level(int width, int height,  ArrayList<Tile> movables) {
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
        this.movables = new ArrayList<Tile>();
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

    /**
     *
     * @return
     */
    public Coordinate getPlayerOnePosition() {
        return playerOnePosition;
    }

    /**
     *
     * @param playerOnePosition
     */
    public void setPlayerOnePosition(Coordinate playerOnePosition) {
        this.playerOnePosition = playerOnePosition;
    }

    /**
     *
     * @return
     */
    public Coordinate getPlayerTwoPosition() {
        return playerTwoPosition;
    }

    /**
     *
     * @param playerTwoPosition
     */
    public void setPlayerTwoPosition(Coordinate playerTwoPosition) {
        this.playerTwoPosition = playerTwoPosition;
    }

    /**
     *
     * @return
     */
    public Coordinate getPlayerThreePosition() {
        return playerThreePosition;
    }

    /**
     *
     * @param playerThreePosition
     */
    public void setPlayerThreePosition(Coordinate playerThreePosition) {
        this.playerThreePosition = playerThreePosition;
    }

    /**
     *
     * @return
     */
    public Coordinate getPlayerFourPosition() {
        return playerFourPosition;
    }

    /**
     *
     * @param playerFourPosition
     */
    public void setPlayerFourPosition(Coordinate playerFourPosition) {
        this.playerFourPosition = playerFourPosition;
    }

    /**
     *
     * @return
     */
    public ArrayList<Tile> getMovables() {
        return movables;
    }

    /**
     * 
     * @param movables
     */
    public void setMovables(ArrayList<Tile> movables) {
        this.movables = movables;
    }

    public void setPlayerPosition(Coordinate coordinate, int playerNumber) {
        switch (playerNumber) {
            case 1 : this.playerOnePosition = coordinate;
            case 2 : this.playerTwoPosition = coordinate;
            case 3 : this.playerThreePosition = coordinate;
            case 4 : this.playerFourPosition = coordinate;
            default : throw new IllegalArgumentException("Player number not in range");
        }
    }
    public Coordinate getPlayerPosition(int playerNumber) {
        switch (playerNumber) {
            case 1 : return this.playerOnePosition;
            case 2 : return this.playerTwoPosition;
            case 3 : return this.playerThreePosition;
            case 4 : return this.playerFourPosition;
            default : throw new IllegalArgumentException("Player number not in range");
        }
    }
}
