package styles;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the Mouse Trap Styles.Style
 *
 * @author Aaron Davies, Isabelle Ludwig & Joseph Omar
 * @version 3.5
 */

public class MouseTrapStyle extends Style implements Serializable {
    static {
        PLAYER_ONE = new Image("patrick.jpg");
        PLAYER_TWO = new Image("patrick.jpg");
        PLAYER_THREE = new Image("patrick.jpg");
        PLAYER_FOUR = new Image("patrick.jpg");
        STRAIGHT_TILE = new Image("cars_straight.png");
        STRAIGHT_FIRE = new Image("cars_straight_fire.png");
        STRAIGHT_ICE = new Image("cars_straight_ice.png");
        CORNER_TILE = new Image("cars_corner.png");
        CORNER_FIRE = new Image("cars_corner_fire.png");
        CORNER_ICE = new Image("cars_corner_ice.png");
        JUNCTION_TILE = new Image("cars_junction.png");
        JUNCTION_FIRE = new Image("cars_junction_fire.png");
        JUNCTION_ICE = new Image("cars_junction_ice.png");
        GOAL_TILE = new Image("Pirate_Style/pirate_goal.png");
        GAMEBOARD = new Image("patrick.jpg");
    }

    /**
     * Gets each images and assigns them to the style class
     */
    public MouseTrapStyle() {
        super(PLAYER_ONE, PLAYER_TWO, PLAYER_THREE, PLAYER_FOUR, STRAIGHT_TILE, STRAIGHT_FIRE, STRAIGHT_ICE,
                CORNER_TILE, CORNER_FIRE, CORNER_ICE, JUNCTION_TILE, JUNCTION_FIRE, JUNCTION_ICE, GOAL_TILE, GAMEBOARD);
    }
}
