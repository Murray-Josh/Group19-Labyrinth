package styles;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the Pirate Styles.Style
 *
 * @author Aaron Davies, Isabelle Ludwig & Joseph Omar
 * @version 3.0
 */
public final class PirateStyle extends Style implements Serializable {
    static {
        PLAYER_ONE = new Image("resources/styles/pirate/pirate_player_one.png");
        PLAYER_TWO = new Image("resources/styles/pirate/pirate_player_two.png");
        PLAYER_THREE = new Image("resources/styles/pirate/pirate_player_three.png");
        PLAYER_FOUR = new Image("resources/styles/pirate/pirate_player_four.png");
        STRAIGHT_TILE = new Image("resources/styles/pirate/pirate_straight.png");
        STRAIGHT_FIRE = new Image("resources/styles/pirate/pirate_fire.png");
        STRAIGHT_ICE = new Image("resources/styles/pirate/pirate_straight_ice.png");
        CORNER_TILE = new Image("resources/styles/pirate/pirate_corner.png");
        CORNER_FIRE = new Image("resources/styles/pirate/pirate_fire.png");
        CORNER_ICE = new Image("resources/styles/pirate/pirate_corner_ice.png");
        JUNCTION_TILE = new Image("resources/styles/pirate/pirate_junction.png");
        JUNCTION_FIRE = new Image("resources/styles/pirate/pirate_fire.png");
        JUNCTION_ICE = new Image("resources/styles/pirate/pirate_junction_ice.png");
        GOAL_TILE = new Image("resources/styles/pirate/pirate_goal.png");
        GAMEBOARD = new Image("resources/styles/pirate/pirate_boarder.png");
    }

    /**
     * Gets each images and assigns them to the style class
     */
    public PirateStyle() {
        super(PLAYER_ONE, PLAYER_TWO, PLAYER_THREE, PLAYER_FOUR, STRAIGHT_TILE, STRAIGHT_FIRE, STRAIGHT_ICE,
                CORNER_TILE, CORNER_FIRE, CORNER_ICE, JUNCTION_TILE, JUNCTION_FIRE, JUNCTION_ICE, GOAL_TILE, GAMEBOARD);
    }
}