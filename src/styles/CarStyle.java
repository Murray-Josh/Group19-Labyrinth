package styles;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the Ca Styles.Style
 *
 * @author Aaron Davies, Isabelle Ludwig & Joseph Omar
 * @version 1.0
 */

public final class CarStyle extends Style implements Serializable {
    static {
        PLAYER_ONE = new Image("Cars_Style/cars_player_one.png");
        PLAYER_TWO = new Image("Cars_Style/cars_player_two.png");
        PLAYER_THREE = new Image("Cars_Style/cars_player_three.png");
        PLAYER_FOUR = new Image("Cars_Style/cars_player_four.png");
        STRAIGHT_TILE = new Image("Cars_Style/cars_straight.png");
        STRAIGHT_FIRE = new Image("Cars_Style/cars_straight_fire.png");
        STRAIGHT_ICE = new Image("Cars_Style/cars_straight_ice.png");
        CORNER_TILE = new Image("Cars_Style/cars_junction.png");
        CORNER_FIRE = new Image("Cars_Style/cars_corner_fire.png");
        CORNER_ICE = new Image("Cars_Style/cars_corner_ice.png");
        JUNCTION_TILE = new Image("Cars_Style/cars_junction.png");
        JUNCTION_FIRE = new Image("Cars_Style/cars_junction_fire.png");
        JUNCTION_ICE = new Image("Cars_Style/cars_junction_ice.png");
        GAMEBOARD = new Image("Cars_Style/cars_boarder.png");

    }
}
