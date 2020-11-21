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
        PLAYER_ONE = new Image("patrick.jpg");
        PLAYER_TWO = new Image("patrick.jpg");
        PLAYER_THREE = new Image("patrick.jpg");
        PLAYER_FOUR = new Image("patrick.jpg");
        STRAIGHT_TILE = new Image("patrick.jpg");
        CORNER_TILE = new Image("patrick.jpg");
        JUNCTION_TILE = new Image("patrick.jpg");
        GAMEBOARD = new Image("patrick.jpg");
public class PirateStyle {
    public static void PirateImages() {
        private static final Image Pirate_PLAYER_ONE = new Image("patrick.jpg");
        private static final Image Pirate_PLAYER_TWO = new Image("patrick.jpg");
        private static final Image Pirate_PLAYER_THREE = new Image("patrick.jpg");
        private static final Image Pirate_PLAYER_FOUR = new Image("patrick.jpg");
        private static final Image Pirate_STRAIGHT_TILE = new Image("patrick.jpg");
        private static final Image Pirate_CORNER_TILE = new Image("patrick.jpg");
        private static final Image Pirate_JUNCTION_TILE = new Image("patrick.jpg");
        private static final Image Pirate_GAMEBOARD_TILE = new Image("patrick.jpg");

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
