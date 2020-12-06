package players;

import static constants.Angle.UP;

import constants.Angle;
import core.Coordinate;
import holdables.Effect;
import holdables.PlayerEffect;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;
import styles.Style;

/**
 * Class for players in game
 *
 * @author Joshua Murray
 * @author Issi Ludwig
 * @author Joseph Omar
 * @author Jordy Martinson
 * @author Martin Samm
 * @version 2.0
 */
public class Player implements Serializable {

   private final Stack<Coordinate> coordinateHistory;
   private final int[] moves = new int[]{0, 0};
   private ArrayList<Effect> hand;
   private PlayerProfile profile;
   private Style style;
   private int playerNum;
   private Angle currentDirection;
   private PlayerEffect activeEffect;
   private boolean beenBackTracked;


   /**
    * Constructor for a player
    *
    * @param profile    Profile of a player
    * @param coordinate Coordinates of gameboard of player
    * @param style      Styles.Style of piece for player
    * @param playerNum  Players.Player number
    */
   public Player(PlayerProfile profile, Coordinate coordinate, Style style, int playerNum) {
      coordinateHistory = new Stack<>();
      setProfile(profile);
      setStart(coordinate);
      setStyle(style);
      setPlayerNum(playerNum);
      setCurrentDirection(UP);
      setBeenBackTracked(false);
   }

   /**
    * Constructor for backMovement in PlayerMovement
    */
   public Player() {

      coordinateHistory = new Stack<>();
   }

   /**
    * Sets the player's initial position
    *
    * @param coordinate Coordinate of the players starting position
    */
   public void setStart(Coordinate coordinate) {
      coordinateHistory.push(coordinate);

   }

   /**
    * Adds an effect card to players hand
    *
    * @param effect Holdables.Effect card
    */
   public void addToHand(Effect effect) {
      this.hand.add(effect);
   }

   /**
    * Gets players profile
    *
    * @return Players profile
    */
   public PlayerProfile getProfile() {
      return profile;
   }

   /**
    * Sets the profile
    *
    * @param profile Profile of player
    */
   public void setProfile(PlayerProfile profile) {
      this.profile = profile;
   }

   /**
    * Gets coordinates of player
    *
    * @return Coordinates of player
    */
   public Coordinate getCoordinate() {
      return coordinateHistory.peek();
   }

   /**
    * Sets coordinates of player on gameboard
    *
    * @param coordinate object for player
    */
   public void setCoordinate(Coordinate coordinate) {
      coordinateHistory.push(coordinate);
   }

   /**
    * Gets the coordinates at the start of previous two turns
    *
    * @return Array of coordinates
    */
   @SuppressWarnings("unchecked")
   public Coordinate[] getLastTwoCoordinates() {
      Stack<Coordinate> temp = (Stack<Coordinate>) coordinateHistory.clone();


      for (int i = 0; i < moves[0]; i++) {
         temp.pop();
      }
      Coordinate firstTile = temp.peek();
      for (int j = 0; j < moves[1]; j++) {
         temp.pop();
      }
      Coordinate secondTile = temp.peek();
      return new Coordinate[]{firstTile, secondTile};
   }

   /**
    * Sets the number of moves a player took in the past turns
    *
    * @param i number of moves
    */
   public void setMoves(int i) {
      moves[1] = moves[0];
      moves[0] = i;
   }

   /**
    * Gets the current card hand of player
    *
    * @return Card hand of player
    */
   public ArrayList<Effect> getHand() {
      return hand;
   }

   /**
    * Sets the card hand of a player
    *
    * @param hand Players hand
    */
   public void setHand(ArrayList<Effect> hand) {
      this.hand = hand;
   }

   /**
    * Gets players number
    *
    * @return Players number
    */
   public int getPlayerNum() {
      return playerNum;
   }

   /**
    * Sets players number
    *
    * @param playerNum Players number
    */
   public void setPlayerNum(int playerNum) {
      this.playerNum = playerNum;
   }

   /**
    * Gets direction player is facing
    *
    * @return Direction player is facing
    */
   public Angle getCurrentDirection() {
      return currentDirection;
   }

   /**
    * Sets direction the player is facing
    *
    * @param currentDirection Direction player is facing
    */
   public void setCurrentDirection(Angle currentDirection) {
      this.currentDirection = currentDirection;
   }

   /**
    * Gets active effects of player
    *
    * @return Effects active on player
    */
   public PlayerEffect getActiveEffect() {
      return activeEffect;
   }

   /**
    * Sets effects on player
    *
    * @param activeEffect Effects to be active on player
    */
   public void setActiveEffect(PlayerEffect activeEffect) {
      this.activeEffect = activeEffect;
   }

   /**
    * Set whether player has been back tracked
    *
    * @param beenBackTracked Boolean on if the player has been backed tracked
    */
   public void setBeenBackTracked(boolean beenBackTracked) {
      this.beenBackTracked = beenBackTracked;
   }

   /**
    * Returns if the player has been backtracked
    *
    * @return If the player has been backtracked
    */
   public boolean hasBeenBackTracked() {
      return beenBackTracked;
   }

   /**
    * Gets the assigned style
    *
    * @return Style
    */
   public Style getStyle() {
      return this.style;
   }

   /**
    * Sets the style
    *
    * @param style Styles.Style of player piece
    */
   public void setStyle(Style style) {
      this.style = style;
   }

   /**
    * Convert a player to a String
    * @return Player Name
    */
   @Override
   public String toString() {
      return this.getProfile().getName();
   }

}
