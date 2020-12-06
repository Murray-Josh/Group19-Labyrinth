package styles;

import java.io.Serializable;
import javafx.scene.image.Image;

/**
 * Gives the Images for the Mouse Trap Styles.Style
 *
 * @author Aaron Davies
 * @author Isabelle Ludwig
 * @author Joseph Omar
 * @version 3.5
 */

public class MouseStyle extends Style implements Serializable {

    static {
        PLAYER_ONE = new Image("resources/styles/mouse/one.png");
        PLAYER_TWO = new Image("resources/styles/mouse/two.png");
        PLAYER_THREE = new Image("resources/styles/mouse/three.png");
        PLAYER_FOUR = new Image("resources/styles/mouse/four.png");
        STRAIGHT_TILE = new Image("resources/styles/mouse/str.png");
        STRAIGHT_FIRE = new Image("resources/styles/mouse/str_fire.png");
        STRAIGHT_ICE = new Image("resources/styles/mouse/str_ice.png");
        CORNER_TILE = new Image("resources/styles/mouse/cor.png");
        CORNER_FIRE = new Image("resources/styles/mouse/cor_fire.png");
        CORNER_ICE = new Image("resources/styles/mouse/cor_ice.png");
        JUNCTION_TILE = new Image("resources/styles/mouse/jun.png");
        JUNCTION_FIRE = new Image("resources/styles/mouse/jun_fire.png");
        JUNCTION_ICE = new Image("resources/styles/mouse/jun_ice.png");
        GOAL_TILE = new Image("resources/styles/mouse/goal.png");
        GAMEBOARD = new Image("resources/styles/mouse/bor.png");
    }

    /**
     * Gets each images and assigns them to the style class
     */
    public MouseStyle() {
        super(PLAYER_ONE, PLAYER_TWO, PLAYER_THREE, PLAYER_FOUR, STRAIGHT_TILE, STRAIGHT_FIRE,
                STRAIGHT_ICE,
                CORNER_TILE, CORNER_FIRE, CORNER_ICE, JUNCTION_TILE, JUNCTION_FIRE, JUNCTION_ICE,
                GOAL_TILE, GAMEBOARD);
    }
}
