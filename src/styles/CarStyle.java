package styles;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the CarStyles.Style
 *
 * @author Aaron Davies, Isabelle Ludwig & Joseph Omar
 * @version 3.5
 */
public enum  CarStyle implements Serializable, Style {
        PLAYER_ONE(new Image("resources/styles/car/one.png")),
        PLAYER_TWO(new Image("resources/styles/car/two.png")),
        PLAYER_THREE(new Image("resources/styles/car/three.png")),
        PLAYER_FOUR(new Image("resources/styles/car/four.png")),
        STRAIGHT_TILE(new Image("resources/styles/car/str.png")),
        STRAIGHT_FIRE(new Image("resources/styles/car/str_fire.png")),
        STRAIGHT_ICE(new Image("resources/styles/car/str_ice.png")),
        CORNER_TILE(new Image("resources/styles/car/cor.png")),
        CORNER_FIRE(new Image("resources/styles/car/cor_fire.png")),
        CORNER_ICE(new Image("resources/styles/car/cor_ice.png")),
        JUNCTION_TILE(new Image("resources/styles/car/jun.png")),
        JUNCTION_FIRE(new Image("resources/styles/car/jun_fire.png")),
        JUNCTION_ICE(new Image("resources/styles/car/jun_ice.png")),
        GOAL_TILE(new Image("resources/styles/car/goal.png")),
        GAMEBOARD(new Image("resources/styles/car/bor.png"));

    private final Image IMAGE;
    CarStyle(Image image) {
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