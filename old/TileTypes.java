package holdables;

import javafx.scene.image.Image;

/**
 * Outline for a Holdables.TileTypes, which specifies the directions the player can go.
 *
 * @author FungWah Westley & Joseph Omar
 * @version 2.0
 */
public abstract class TileTypes {
    protected static boolean NORTH;
    protected static boolean SOUTH;
    protected static boolean EAST;
    protected static boolean WEST;

    protected TileTypes(Boolean NORTH, Boolean SOUTH, Boolean EAST, Boolean WEST){
        this.NORTH = NORTH;
        this.SOUTH = SOUTH;
        this.EAST = EAST;
        this.WEST = WEST;
    }

    /**
     * Gets an array of boolean which indicates the directions a player can go.
     *
     * @return Available directions <NORTH, EAST, SOUTH, WEST>
     */
    public static boolean[] getAvailableDirections() {
        return new boolean[]{NORTH, EAST, SOUTH, WEST};
    }

    /**
     * Gets the Holdables.TileTypes name.
     *
     * @return Holdables.Tile type name
     */
    public String toString() {
        return this.getClass().getName();
    }

    public static boolean canMoveNorth(){return NORTH;}
    public static boolean canMoveEast(){return EAST;}
    public static boolean canMoveSouth(){return SOUTH;}
    public static boolean canMoveWest(){return WEST;}
}