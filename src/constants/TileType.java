package constants;

import holdables.Tile;

/**
 * Defines the types of {@link Tile} that can be placed on the board. Also
 * defines the directions a player can go on a tile when the tile's angle is 0
 *
 * @author Fungwah Westley
 * @author Joseph Omar
 * @version 3.0
 */
public enum TileType {
    STRAIGHT(false, true, false, true),
    CORNER(false, true, true, false),
    JUNCTION(false, true, true, true),
    GOAL(true, true, true, true);

    private final boolean NORTH;
    private final boolean EAST;
    private final boolean SOUTH;
    private final boolean WEST;

    /**
     * Defines where a player can go on a particular tile when angle = 0
     *
     * @param north Can the player go north
     * @param east  Can the player go east
     * @param south Can the player go south
     * @param west  Can the player go west
     */
    TileType(boolean north, boolean east, boolean south, boolean west) {
        this.NORTH = north;
        this.EAST = east;
        this.SOUTH = south;
        this.WEST = west;
    }
}
