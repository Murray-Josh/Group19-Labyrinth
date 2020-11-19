import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the Car Style
 * @author Aaron Davies, Isabelle Ludwig & Joseph Omar
 * @version 1.0
 */
public class CarStyle extends Style implements Serializable {
    static {
        PLAYER_ONE = new Image("patrick.jpg");
        PLAYER_TWO = new Image("patrick.jpg");
        PLAYER_THREE = new Image("patrick.jpg");
        PLAYER_FOUR = new Image("patrick.jpg");
        STRAIGHT_TILE = new Image("patrick.jpg");
        CORNER_TILE = new Image("patrick.jpg");
        JUNCTION_TILE = new Image("patrick.jpg");
        GAMEBOARD = new Image("patrick.jpg");
    }
}
