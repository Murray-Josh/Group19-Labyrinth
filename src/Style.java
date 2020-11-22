//automatically did this - needs changing

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * icons
 * create three classes to split up the stuff into to make it easier to access from other classes ie make it more customisable later on...
 */

public abstract class Style implements Serializable {

    protected static Image PLAYER_ONE = null;
    protected static Image PLAYER_TWO = null;
    protected static Image PLAYER_THREE = null;
    protected static Image PLAYER_FOUR = null;
    protected static Image STRAIGHT_TILE = null;
    protected static Image STRAIGHT_FIRE = null;
    protected static Image STRAIGHT_ICE = null;
    protected static Image CORNER_TILE = null;
    protected static Image CORNER_FIRE = null;
    protected static Image CORNER_ICE = null;
    protected static Image JUNCTION_TILE = null;
    protected static Image JUNCTION_FIRE = null;
    protected static Image JUNCTION_ICE = null;
    protected static Image GOAL_TILE = null;
    protected static Image GAMEBOARD = null;


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
     * Gets the Corner Tile Image
     *
     * @return Corner Tile Image
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
     * Gets the Straight Tile Image
     *
     * @return Straight Tile Image
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
     * Gets the Goal Tile Image
     *
     * @return Goal Tile Image
     */
    public static Image getGoalTile() {
        return GOAL_TILE;
    }

    /**
     * Gets the Junction Tile Image
     *
     * @return Junction Tile Image
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
     * Gets the Gameboard Border Image
     *
     * @return Gameboard Border Image
     */
    public static Image getGameboardImage() {
        return GAMEBOARD;
    }


}
