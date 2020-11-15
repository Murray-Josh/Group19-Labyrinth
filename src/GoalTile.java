/**
 * A Goal tile, where a player can go in all directions.
 *
 * @author FungWah Westley & Joseph Omar
 * @version 2.0
 */
public class GoalTile extends TileType {
    /**
     * Specify the directions where the player can go
     */
    static {
        NORTH = true;
        EAST = true;
        SOUTH = true;
        WEST = true;
    }
}
