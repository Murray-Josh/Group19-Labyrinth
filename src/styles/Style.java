package styles;

import holdables.TileEffect;
import javafx.scene.image.Image;

public interface Style {
    /**
     * Gets Player One's Sprite
     * @return Sprite
     */
    Image getPlayerOne();
    /**
     * Gets Player Two's Sprite
     * @return Sprite
     */
    Image getPlayerTwo();
    /**
     * Gets Player Three's Sprite
     * @return Sprite
     */
    Image getPlayerThree();
    /**
     * Gets Player Four's Sprite
     * @return Sprite
     */
    Image getPlayerFour();
    /**
     * Gets Straight Tile's image when effect is NONE
     * @return Tile when effect is NONE
     */
    Image getStraightTileNone();
    /**
     * Gets Corner Tile's image when effect is NONE
     * @return Tile when effect is NONE
     */
    Image getCornerTileNone();
    /**
     * Gets Junction Tile's image when effect is NONE
     * @return Tile when effect is NONE
     */
    Image getJunctionTileNone();
    /**
     * Gets Straight Tile's image when effect is Fire
     * @return Tile when effect is Fire
     */
    Image getStraightTileFire();
    /**
     * Gets JunctionTile's image when effect is Fire
     * @return Tile when effect is Fire
     */
    Image getJunctionTileFire();
    /**
     * Gets Corner Tile's image when effect is Fire
     * @return Tile when effect is Fire
     */
    Image getCornerTileFire();
    /**
     * Gets Straight Tile's image when effect is ICE
     * @return Tile when effect is ICE
     */
    Image getStraightTileIce();
    /**
     * Gets Junction Tile's image when effect is ICE
     * @return Tile when effect is ICE
     */
    Image getJunctionTileIce();
    /**
     * Gets Corner Tile's image when effect is ICE
     * @return Tile when effect is ICE
     */
    Image getCornerTileIce();
    /**
     * Gets Gameboard border image
     * @return
     */
    Image getGameboard();
    /**
     * Gets Goal Tile's image
     * @return
     */
    Image getGoalTile();

    /**
     * Converts the Class name to a presentable String
     * @return
     */
    String toString();

    /**
     * Gets a player's sprite based on their Player Number
     * @param playerNumber Player Number 1-4
     * @return Their Sprite
     */
    default Image getPlayer(int playerNumber) {
    switch (playerNumber) {
        case 1 : return getPlayerOne();
        case 2 : return getPlayerTwo();
        case 3 : return getPlayerThree();
        case 4 : return getPlayerFour();
        default: throw new IndexOutOfBoundsException("Player number specified is not supported");
        }
    }

    /**
     * Gets the Straight tile image base on effect
     * @param effect Effect to find the Tile asset for
     * @return Tile asset for the effect
     */
    default Image getStraightTile(TileEffect effect) {
        switch(effect) {
            case ICE:return getStraightTileIce();
            case FIRE:return getStraightTileFire();
            case NONE:return getStraightTileNone();
            default:throw new IllegalArgumentException("Effect does not exist");
        }
    }
    /**
     * Gets the Corner tile image base on effect
     * @param effect Effect to find the Tile asset for
     * @return Tile asset for the effect
     */
    default Image getCornerTile(TileEffect effect) {
        switch(effect) {
            case ICE:return getCornerTileIce();
            case FIRE:return getCornerTileFire();
            case NONE:return getCornerTileNone();
            default:throw new IllegalArgumentException("Effect does not exist");
        }
    }

    /**
     * Gets the Junction tile image base on effect
     * @param effect Effect to find the Tile asset for
     * @return Tile asset for the effect
     */
    default Image getJunctionTile(TileEffect effect) {
        switch(effect) {
            case ICE:return getJunctionTileIce();
            case FIRE:return getJunctionTileFire();
            case NONE:return getJunctionTileNone();
            default:throw new IllegalArgumentException("Effect does not exist");
        }
    }

    
}
