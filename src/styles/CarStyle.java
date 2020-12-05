package styles;

import java.io.Serializable;
import javafx.scene.image.Image;

/**
 * Gives the Images for the CarStyles.Style
 *
 * @author Aaron Davies
 * @author Isabelle Ludwig
 * @author Joseph Omar
 * @version 3.5
 */
public class CarStyle extends Style implements Serializable {

   static {
      PLAYER_ONE = new Image("resources/styles/car/one.png");
      PLAYER_TWO = new Image("resources/styles/car/two.png");
      PLAYER_THREE = new Image("resources/styles/car/three.png");
      PLAYER_FOUR = new Image("resources/styles/car/four.png");
      STRAIGHT_TILE = new Image("resources/styles/car/str.png");
      STRAIGHT_FIRE = new Image("resources/styles/car/str_fire.png");
      STRAIGHT_ICE = new Image("resources/styles/car/str_ice.png");
      CORNER_TILE = new Image("resources/styles/car/cor.png");
      CORNER_FIRE = new Image("resources/styles/car/cor_fire.png");
      CORNER_ICE = new Image("resources/styles/car/cor_ice.png");
      JUNCTION_TILE = new Image("resources/styles/car/jun.png");
      JUNCTION_FIRE = new Image("resources/styles/car/jun_fire.png");
      JUNCTION_ICE = new Image("resources/styles/car/jun_ice.png");
      GOAL_TILE = new Image("resources/styles/car/goal.png");
      GAMEBOARD = new Image("resources/styles/car/bor.png");
   }

   /**
    * Gets each images and assigns them to the style class
    */
   public CarStyle() {
      super(PLAYER_ONE, PLAYER_TWO, PLAYER_THREE, PLAYER_FOUR, STRAIGHT_TILE, STRAIGHT_FIRE,
           STRAIGHT_ICE,
           CORNER_TILE, CORNER_FIRE, CORNER_ICE, JUNCTION_TILE, JUNCTION_FIRE, JUNCTION_ICE,
           GOAL_TILE, GAMEBOARD);
   }
}