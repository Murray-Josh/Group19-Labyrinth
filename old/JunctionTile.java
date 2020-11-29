package holdables;

/**
 * A Junction tile, where a player can go North, east or west.
 *
 * @author FungWah Westley & Joseph Omar
 * @version 2.0
 */
public class JunctionTile extends TileTypes {
    /*
     * Specify the directions where the player can go
     */
    static {
        NORTH = true;
        EAST = true;
        SOUTH = false;
        WEST = true;
    }
    public JunctionTile(){
        super(NORTH,EAST,SOUTH,WEST);
    }

}
