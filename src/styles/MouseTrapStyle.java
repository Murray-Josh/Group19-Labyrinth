package styles;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the Mouse Trap Styles.Style
 *
 * @author Aaron Davies, Isabelle Ludwig & Joseph Omar
 * @version 1.0
 */

public final class MouseTrapStyle extends Style implements Serializable {
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
        GAMEBOARD = new Image("patrick.jpg");
    }
}
