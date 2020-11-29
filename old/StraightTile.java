package holdables;

/**
 * A Straight Holdables.Tile, where a player can go east or west.
 *
 * @author FungWah Westley & Joseph Omar
 * @version 2.0
 */
public class StraightTile extends TileType {
    /*
     * Specify the directions where the player can go
     */
    static {
        NORTH = false;
        EAST = true;
        SOUTH = false;
        WEST = true;
    }
    public StraightTile(){
        super(NORTH,EAST,SOUTH,WEST);
    }
}
