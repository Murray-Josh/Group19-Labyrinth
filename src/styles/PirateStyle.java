package styles;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the Pirate Styles.Style
 *
 * @author Aaron Davies, Isabelle Ludwig & Joseph Omar
 * @version 3.0
 */
public enum PirateStyle implements Serializable, Style {
    CORNER_FIRE(new Image("resources/styles/pirate/pirate_fire.png")),
    CORNER_ICE(new Image("resources/styles/pirate/pirate_corner_ice.png")),
    CORNER_TILE(new Image("resources/styles/pirate/pirate_corner.png")),
    GAMEBOARD(new Image("resources/styles/pirate/pirate_boarder.png")),
    GOAL_TILE(new Image("resources/styles/pirate/pirate_goal.png")),
    JUNCTION_FIRE(new Image("resources/styles/pirate/pirate_fire.png")),
    JUNCTION_ICE(new Image("resources/styles/pirate/pirate_junction_ice.png")),
    JUNCTION_TILE(new Image("resources/styles/pirate/pirate_junction.png")),
    PLAYER_FOUR(new Image("resources/styles/pirate/pirate_player_four.png")),
    PLAYER_ONE(new Image("resources/styles/pirate/pirate_player_one.png")),
    PLAYER_THREE(new Image("resources/styles/pirate/pirate_player_three.png")),
    PLAYER_TWO(new Image("resources/styles/pirate/pirate_player_two.png")),
    STRAIGHT_FIRE(new Image("resources/styles/pirate/pirate_fire.png")),
    STRAIGHT_ICE(new Image("resources/styles/pirate/pirate_straight_ice.png")),
    STRAIGHT_TILE(new Image("resources/styles/pirate/pirate_straight.png"));

    private final Image IMAGE;
    PirateStyle(Image image) {
        this.IMAGE = image;
    }

    /**
     * Gets Player One's Sprite
     *
     * @return Sprite
     */
    @Override
    public Image getPlayerOne() {
        return null;
    }

    /**
     * Gets Player Two's Sprite
     *
     * @return Sprite
     */
    @Override
    public Image getPlayerTwo() {
        return null;
    }

    /**
     * Gets Player Three's Sprite
     *
     * @return Sprite
     */
    @Override
    public Image getPlayerThree() {
        return null;
    }

    /**
     * Gets Player Four's Sprite
     *
     * @return Sprite
     */
    @Override
    public Image getPlayerFour() {
        return null;
    }

    /**
     * Gets Straight Tile's image when effect is NONE
     *
     * @return Tile when effect is NONE
     */
    @Override
    public Image getStraightTileNone() {
        return null;
    }

    /**
     * Gets Corner Tile's image when effect is NONE
     *
     * @return Tile when effect is NONE
     */
    @Override
    public Image getCornerTileNone() {
        return null;
    }

    /**
     * Gets Junction Tile's image when effect is NONE
     *
     * @return Tile when effect is NONE
     */
    @Override
    public Image getJunctionTileNone() {
        return null;
    }

    /**
     * Gets Straight Tile's image when effect is Fire
     *
     * @return Tile when effect is Fire
     */
    @Override
    public Image getStraightTileFire() {
        return null;
    }

    /**
     * Gets JunctionTile's image when effect is Fire
     *
     * @return Tile when effect is Fire
     */
    @Override
    public Image getJunctionTileFire() {
        return null;
    }

    /**
     * Gets Corner Tile's image when effect is Fire
     *
     * @return Tile when effect is Fire
     */
    @Override
    public Image getCornerTileFire() {
        return null;
    }

    /**
     * Gets Straight Tile's image when effect is ICE
     *
     * @return Tile when effect is ICE
     */
    @Override
    public Image getStraightTileIce() {
        return null;
    }

    /**
     * Gets Junction Tile's image when effect is ICE
     *
     * @return Tile when effect is ICE
     */
    @Override
    public Image getJunctionTileIce() {
        return null;
    }

    /**
     * Gets Corner Tile's image when effect is ICE
     *
     * @return Tile when effect is ICE
     */
    @Override
    public Image getCornerTileIce() {
        return null;
    }

    /**
     * Gets Gameboard border image
     *
     * @return
     */
    @Override
    public Image getGameboard() {
        return null;
    }

    /**
     * Gets Goal Tile's image
     *
     * @return
     */
    @Override
    public Image getGoalTile() {
        return null;
    }
}