package styles;

import java.io.Serializable;
import javafx.scene.image.Image;

/**
 * Gives the Images for the Pirate Styles.Style
 *
 * @author Aaron Davies
 * @author Isabelle Ludwig
 * @author Joseph Omar
 * @version 3.0
 */
public final class PirateStyle extends Style implements Serializable {

    static {
        PLAYER_ONE = new Image("resources/styles/pirate/one.png");
        PLAYER_TWO = new Image("resources/styles/pirate/two.png");
        PLAYER_THREE = new Image("resources/styles/pirate/three.png");
        PLAYER_FOUR = new Image("resources/styles/pirate/four.png");
        STRAIGHT_TILE = new Image("resources/styles/pirate/str.png");
        STRAIGHT_FIRE = new Image("resources/styles/pirate/str_fire.png");
        STRAIGHT_ICE = new Image("resources/styles/pirate/str_ice.png");
        CORNER_TILE = new Image("resources/styles/pirate/cor.png");
        CORNER_FIRE = new Image("resources/styles/pirate/cor_fire.png");
        CORNER_ICE = new Image("resources/styles/pirate/cor_ice.png");
        JUNCTION_TILE = new Image("resources/styles/pirate/jun.png");
        JUNCTION_FIRE = new Image("resources/styles/pirate/jun_fire.png");
        JUNCTION_ICE = new Image("resources/styles/pirate/jun_ice.png");
        GOAL_TILE = new Image("resources/styles/pirate/goal.png");
        GAMEBOARD = new Image("resources/styles/pirate/bor.png");
    }


    /**
     * Gets each images and assigns them to the style class
     */
    public PirateStyle() {
        super(PLAYER_ONE, PLAYER_TWO, PLAYER_THREE, PLAYER_FOUR, STRAIGHT_TILE, STRAIGHT_FIRE,
                STRAIGHT_ICE,
                CORNER_TILE, CORNER_FIRE, CORNER_ICE, JUNCTION_TILE, JUNCTION_FIRE, JUNCTION_ICE,
                GOAL_TILE, GAMEBOARD);
    }
}