package players;

import constants.Angle;
import core.Coordinate;
import holdables.Effect;
import holdables.Holdable;
import holdables.PlayerEffect;
import javafx.scene.image.Image;
import styles.Style;

import static constants.Angle.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

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
    private ArrayList<Effect> hand;
    private PlayerProfile profile;
    private Stack<Coordinate> coordinateHistory;
    private Style style;
    private int playerNum;
    private Angle currentDirection;
    private PlayerEffect activeEffect;
    private Image playerImage;
    private boolean beenBackTracked;
    private int[] moves = new int[]{0, 0};



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
        setPlayerImage(playerNum);
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
     * @param coordinate
     */
    public void setStart(Coordinate coordinate) {
        coordinateHistory.push(coordinate);

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
     * Gets the coordinates at the start of previous two turns
     * @return Array of coordinates
     */
    public Coordinate[] getLastTwoCoordinates() {
        Stack<Coordinate> temp = (Stack<Coordinate>) coordinateHistory.clone();
        Coordinate firstTile = null;
        Coordinate secondTile = null;
        for (int i = 0; i < moves[0]; i++) {
            firstTile = temp.pop();
        }
        firstTile = temp.peek();
        for (int j = 0; j < moves[1]; j++) {
            secondTile = temp.pop();
        }
        secondTile = temp.peek();
        return new Coordinate[]{firstTile, secondTile};
    }

    /**
     * Sets the number of moves a player took in the past turns
     * @param i number of moves
     */
    public void setMoves(int i) {
        moves[1] = moves[0];
        moves[0] = i;
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
     * Set image of player
     * @param playerNumber Player number to set image of
     */
    public void setPlayerImage(int playerNumber) {
        this.playerImage = Style.getPlayerImage(playerNumber);
    }

    /**
     * Set whether player has been back tracked
     * @param beenBackTracked Boolean on if the player has been backed tracked
     */
    public void setBeenBackTracked(boolean beenBackTracked){
        this.beenBackTracked = beenBackTracked;
    }

    public boolean hasBeenBackTracked(){
        return beenBackTracked;
    }

    /**
     * Get image of a player
     * @return Image of player
     */
    public Image getPlayerImage() {
        return playerImage;
    }
}
