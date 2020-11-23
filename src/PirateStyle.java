import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the Pirate Style
 *
 * @author Aaron Davies, Isabelle Ludwig & Joseph Omar
 * @version 1.0
 */
public class PirateStyle extends Style implements Serializable {
    static {
        PLAYER_ONE = new Image("pirate_player_one.png");
        PLAYER_TWO = new Image("pirate_player_two.png");
        PLAYER_THREE = new Image("pirate_player_three.png");
        PLAYER_FOUR = new Image("pirate_player_four.png");
        STRAIGHT_TILE = new Image("pirate_straight.png");
        STRAIGHT_FIRE = new Image("pirate_fire.png");
        STRAIGHT_ICE = new Image("pirate_ice.png");
        CORNER_TILE = new Image("pirate_corner.png");
        CORNER_FIRE = new Image("pirate_fire.png");
        CORNER_ICE = new Image("pirate_ice.png");
        JUNCTION_TILE = new Image("pirate_junction.png");
        JUNCTION_FIRE = new Image("pirate_fire.png");
        JUNCTION_ICE = new Image("pirate_ice.png");
        GAMEBOARD = new Image("patrick.jpg");
    }
}