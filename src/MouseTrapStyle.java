import javafx.scene.image.Image;

import java.io.Serializable;
public class MouseTrapStyle extends Style{
    public static void MouseTrapImages() {
        private static final Image MouseTrap_PLAYER_ONE = new Image("patrick.jpg");
        private static final Image MouseTrap_PLAYER_TWO = new Image("patrick.jpg");
        private static final Image MouseTrap_PLAYER_THREE = new Image("patrick.jpg");
        private static final Image MouseTrap_PLAYER_FOUR = new Image("patrick.jpg");
        private static final Image MouseTrap_STRAIGHT_TILE = new Image("patrick.jpg");
        private static final Image MouseTrap_CORNER_TILE = new Image("patrick.jpg");
        private static final Image MouseTrap_JUNCTION_TILE = new Image("patrick.jpg");
        private static final Image MouseTrap_GAMEBOARD_TILE = new Image("patrick.jpg");
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
