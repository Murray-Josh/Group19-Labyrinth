import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the Car Style
 *
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
public class CarStyle {
    public static void CarsImages() {
        private static final Image Cars_PLAYER_ONE = new Image("patrick.jpg");
        private static final Image Cars_PLAYER_TWO = new Image("patrick.jpg");
        private static final Image Cars_PLAYER_THREE = new Image("patrick.jpg");
        private static final Image Cars_PLAYER_FOUR = new Image("patrick.jpg");
        private static final Image Cars_STRAIGHT_TILE = new Image("patrick.jpg");
        private static final Image Cars_CORNER_TILE = new Image("patrick.jpg");
        private static final Image Cars_JUNCTION_TILE = new Image("patrick.jpg");
        private static final Image Cars_GAMEBOARD_TILE = new Image("patrick.jpg");
    }
    public Image getCORNER_TILE(){
        return CORNER_TILE;
    }
    public Image getJUNCTION_TILE(){
        return JUNCTION_TILE;
    }
    public Image getGOAL_TILE(){
        return GOAL_TILE;
    }
    public Image getGAMEBOARD_BORDER(){
        return GAMEBOARD_BORDER;
    }

    public Image getPLAYER_ONE(){
        return PLAYER_ONE;
    }

    public Image getPLAYER_TWO(){
        return PLAYER_TWO;
    }

    public Image getPLAYER_THREE(){
        return PLAYER_THREE;
    }

    public Image getPLAYER_FOUR(){
        return PLAYER_FOUR;
    }
}
