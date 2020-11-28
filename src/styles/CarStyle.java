package styles;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Gives the Images for the CarStyles.Style
 *
 * @author Aaron Davies, Isabelle Ludwig & Joseph Omar
 * @version 2.0
 */
public class CarStyle extends Style implements Serializable {
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
        GOAL_TILE = new Image("Cars_Style/cars_goal.png");
        GAMEBOARD = new Image("Cars_Style/cars_boarder.png");
    }

    /**
     * Gets each images and assignes them to the style class
     */
    public CarStyle() {
        super(PLAYER_ONE,PLAYER_TWO,PLAYER_THREE,PLAYER_FOUR,STRAIGHT_TILE,STRAIGHT_FIRE,
                STRAIGHT_ICE,CORNER_TILE,CORNER_FIRE,CORNER_ICE,JUNCTION_TILE,JUNCTION_FIRE,
                JUNCTION_ICE,GOAL_TILE,GAMEBOARD);
    }
}
