package styles;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the Mouse Trap Styles.Style
 *
 * @author Aaron Davies, Isabelle Ludwig & Joseph Omar
 * @version 3.5
 */

public enum MouseStyle implements Serializable, Style {
    
        PLAYER_ONE(new Image("patrick.jpg")),
        PLAYER_TWO(new Image("patrick.jpg")),
        PLAYER_THREE(new Image("patrick.jpg")),
        PLAYER_FOUR(new Image("patrick.jpg")),
        STRAIGHT_TILE(new Image("cars_straight.png")),
        STRAIGHT_FIRE(new Image("cars_straight_fire.png")),
        STRAIGHT_ICE(new Image("cars_straight_ice.png")),
        CORNER_TILE(new Image("cars_corner.png")),
        CORNER_FIRE(new Image("cars_corner_fire.png")),
        CORNER_ICE(new Image("cars_corner_ice.png")),
        JUNCTION_TILE(new Image("cars_junction.png")),
        JUNCTION_FIRE(new Image("cars_junction_fire.png")),
        JUNCTION_ICE(new Image("cars_junction_ice.png")),
        GOAL_TILE(new Image("Pirate_Style/pirate_goal.png")),
        GAMEBOARD(new Image("patrick.jpg"));

    private final Image IMAGE;
    MouseStyle(Image image) {
        this.IMAGE = image;
    }

    @Override
    public Image getPlayerOne() {
        return null;
    }

    @Override
    public Image getPlayerTwo() {
        return null;
    }

    @Override
    public Image getPlayerThree() {
        return null;
    }

    @Override
    public Image getPlayerFour() {
        return null;
    }

    @Override
    public Image getStraightTileNone() {
        return null;
    }

    @Override
    public Image getCornerTileNone() {
        return null;
    }

    @Override
    public Image getJunctionTileNone() {
        return null;
    }

    @Override
    public Image getStraightTileFire() {
        return null;
    }

    @Override
    public Image getJunctionTileFire() {
        return null;
    }

    @Override
    public Image getCornerTileFire() {
        return null;
    }

    @Override
    public Image getStraightTileIce() {
        return null;
    }

    @Override
    public Image getJunctionTileIce() {
        return null;
    }

    @Override
    public Image getCornerTileIce() {
        return null;
    }

    @Override
    public Image getGameboard() {
        return null;
    }

    @Override
    public Image getGoalTile() {
        return null;
    }}