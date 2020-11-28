package styles;//automatically did this - needs changing

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Create three classes to split up the stuff into to make it easier to access from other classes
 * @author Issi Ludwig & Martin Samm
 * @version 2.0
 */
public abstract class Style implements Serializable {

    protected static Image PLAYER_ONE;
    protected static Image PLAYER_TWO;
    protected static Image PLAYER_THREE;
    protected static Image PLAYER_FOUR;
    protected static Image STRAIGHT_TILE;
    protected static Image STRAIGHT_FIRE;
    protected static Image STRAIGHT_ICE;
    protected static Image CORNER_TILE;
    protected static Image CORNER_FIRE;
    protected static Image CORNER_ICE;
    protected static Image JUNCTION_TILE;
    protected static Image JUNCTION_FIRE;
    protected static Image JUNCTION_ICE;
    protected static Image GOAL_TILE;
    protected static Image GAMEBOARD;

    /**
     * Implements the images onto the gameboard
     * @param PLAYER_ONE Player one
     * @param PLAYER_TWO Player Two
     * @param PLAYER_THREE Player Three
     * @param PLAYER_FOUR Player Four
     * @param STRAIGHT_TILE Straight Tile
     * @param STRAIGHT_FIRE Straight Tile on fire
     * @param STRAIGHT_ICE Straight Tile covered with ice
     * @param CORNER_TILE Corner Tile
     * @param CORNER_FIRE Corner Tile on Fire
     * @param CORNER_ICE Corner Tile covered with ice
     * @param JUNCTION_TILE Junction Tile
     * @param JUNCTION_FIRE Junction tile on fire
     * @param JUNCTION_ICE Junction Tile covered with ice
     * @param GOAL_TILE Goal Tile
     * @param GAMEBOARD Standard Border
     */
    protected Style(Image PLAYER_ONE, Image PLAYER_TWO, Image PLAYER_THREE, Image PLAYER_FOUR, Image STRAIGHT_TILE,
                    Image STRAIGHT_FIRE, Image STRAIGHT_ICE, Image CORNER_TILE, Image CORNER_FIRE, Image CORNER_ICE,
                    Image JUNCTION_TILE, Image JUNCTION_FIRE, Image JUNCTION_ICE, Image GOAL_TILE, Image GAMEBOARD){
        this.PLAYER_ONE = PLAYER_ONE;
        this.PLAYER_TWO = PLAYER_TWO;
        this.PLAYER_THREE = PLAYER_THREE;
        this.PLAYER_FOUR = PLAYER_FOUR;
        this.STRAIGHT_TILE = STRAIGHT_TILE;
        this.STRAIGHT_FIRE = STRAIGHT_FIRE;
        this.STRAIGHT_ICE = STRAIGHT_ICE;
        this.CORNER_TILE = CORNER_TILE;
        this.CORNER_FIRE = CORNER_FIRE;
        this.CORNER_ICE = CORNER_ICE;
        this.JUNCTION_TILE = JUNCTION_TILE;
        this.JUNCTION_FIRE = JUNCTION_FIRE;
        this.JUNCTION_ICE = JUNCTION_ICE;
        this.GOAL_TILE = GOAL_TILE;
        this.GAMEBOARD = GAMEBOARD;
    }
    /**
     * Gets the image corresponding to the player number of a player
     *
     * @param playerNumber The number of the player 1-4
     * @return The players sprite
     */
    public static Image getPlayerImage(int playerNumber) {
        switch (playerNumber) {
            case 1:
                return PLAYER_ONE;
            case 2:
                return PLAYER_TWO;
            case 3:
                return PLAYER_THREE;
            case 4:
                return PLAYER_FOUR;
            default:
                throw new IndexOutOfBoundsException("<" + playerNumber + "> is not a valid player number");
        }
    }

    /**
     * Gets the Corner Holdables.Tile Image
     *
     * @return Corner Holdables.Tile Image
     */
    public static Image getCornerTile() {
        return CORNER_TILE;
    }

    /**
     * Gets the Corner Fire Image
     *
     * @return Corner Fire Image
     */
    public static Image getCornerFire() {
        return CORNER_FIRE;
    }

    /**
     * Gets the Corner Ice Image
     *
     * @return Corner Ice Image
     */
    public static Image getCornerIce() {
        return CORNER_ICE;
    }


    /**
     * Gets the Straight Holdables.Tile Image
     *
     * @return Straight Holdables.Tile Image
     */
    public static Image getStraightTile() {
        return STRAIGHT_TILE;
    }

    /**
     * Gets the Straight Fire Image
     *
     * @return Straight Fire Image
     */
    public static Image getStraightFire() {
        return STRAIGHT_FIRE;
    }

    /**
     * Gets the Straight Ice Image
     *
     * @return Straight Ice Image
     */
    public static Image getStraightIce() {
        return STRAIGHT_ICE;
    }


    /**
     * Gets the Goal Holdables.Tile Image
     *
     * @return Goal Holdables.Tile Image
     */
    public static Image getGoalTile() {
        return GOAL_TILE;
    }

    /**
     * Gets the Junction Holdables.Tile Image
     *
     * @return Junction Holdables.Tile Image
     */
    public static Image getJunctionTile() {
        return JUNCTION_TILE;
    }

    /**
     * Gets the Junction Fire Image
     *
     * @return Junction Fire Image
     */
    public static Image getJunctionFire() {
        return JUNCTION_FIRE;
    }

    /**
     * Gets the Junction Ice Image
     *
     * @return Junction Ice Image
     */
    public static Image getJunctionIce() {
        return JUNCTION_ICE;
    }


    /**
     * Gets the Core.Gameboard Border Image
     *
     * @return Core.Gameboard Border Image
     */
    public static Image getGameboardImage() {
        return GAMEBOARD;
    }


}
