package styles;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the Ca Styles.Style
 *
 * @author Aaron Davies, Isabelle Ludwig & Joseph Omar
 * @version 1.0
 */

public class CarStyle extends Style implements Serializable {
    static {
        PLAYER_ONE = new Image("cars_player_one.png");
        PLAYER_TWO = new Image("cars_player_two.png");
        PLAYER_THREE = new Image("cars_player_three.png");
        PLAYER_FOUR = new Image("cars_player_four.png");
        STRAIGHT_TILE = new Image("cars_straight.png");
        STRAIGHT_FIRE = new Image("cars_straight_fire.png");
        STRAIGHT_ICE = new Image("cars_straight_ice.png");
        CORNER_TILE = new Image("cars_corner.png");
        CORNER_FIRE = new Image("cars_corner_fire.png");
        CORNER_ICE = new Image("cars_corner_ice.png");
        JUNCTION_TILE = new Image("cars_junction.png");
        JUNCTION_FIRE = new Image("cars_junction_fire.png");
        JUNCTION_ICE = new Image("cars_junction_ice.png");
        GAMEBOARD = new Image("cars_boarder.png");

    }
}
