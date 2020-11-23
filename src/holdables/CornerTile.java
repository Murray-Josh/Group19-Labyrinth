package holdables;

/**
 * A Corner tile, where a player can go North or east.
 *
 * @author FungWah Westley & Joseph Omar
 * @version 2.0
 */
public class CornerTile extends TileType {
    /*
     * Specify the directions where the player can go
     */
    static {
        NORTH = true;
        EAST = true;
        SOUTH = false;
        WEST = false;
    }
}
