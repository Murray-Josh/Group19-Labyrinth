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
      PLAYER_ONE = new Image("resources/styles/mouse/mouse_one.png");
      PLAYER_TWO = new Image("resources/styles/mouse/mouse_two.png");
      PLAYER_THREE = new Image("resources/styles/mouse/mouse_three.png");
      PLAYER_FOUR = new Image("resources/styles/mouse/mouse_four.png");
      STRAIGHT_TILE = new Image("resources/styles/mouse/mouse_str.png");
      STRAIGHT_FIRE = new Image("resources/styles/mouse/mouse_str_fire.png");
      STRAIGHT_ICE = new Image("resources/styles/mouse/mouse_str_ice.png");
      CORNER_TILE = new Image("resources/styles/mouse/mouse_corner.png");
      CORNER_FIRE = new Image("resources/styles/mouse/mouse_corner_fire.png");
      CORNER_ICE = new Image("resources/styles/mouse/mouse_corner_ice.png");
      JUNCTION_TILE = new Image("resources/styles/mouse/mouse_junction.png");
      JUNCTION_FIRE = new Image("resources/styles/mouse/mouse_junction_fire.png");
      JUNCTION_ICE = new Image("resources/styles/mouse/mouse_junction_ice.png");
      GOAL_TILE = new Image("resources/styles/mouse/mouse_goal.png");
      GAMEBOARD = new Image("resources/styles/mouse/mouse_background.png");
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
