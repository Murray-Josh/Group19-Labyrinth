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
 * @version 1.0
 */
public class Player implements Serializable {
    private ArrayList<Effect>   hand;
    private        PlayerProfile         profile;
    private Stack<Coordinate> coordinateHistory;
    private        Style                 style;
    private        int                   playerNum;
    private Angle currentDirection;
    private        PlayerEffect          activeEffect;
    private Image playerImage;

    /**
     * Constructor for a player
     *
     * @param profile   Profile of a player
     * @param coordinate     Coordinates of gameboard of player
     * @param style     Styles.Style of piece for player
     * @param playerNum Players.Player number
     */
    public Player(PlayerProfile profile, Coordinate coordinate, Style style, int playerNum) {
        setProfile(profile);
        setStart(coordinate);
        setStyle(style);
        setPlayerNum(playerNum);
        setCurrentDirection(UP);
        setPlayerImage(playerNum);
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
    private void setProfile(PlayerProfile profile) {
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
    private void setPlayerNum(int playerNum) {
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

    public void setPlayerImage(int playerNumber) {
        this.playerImage = Style.getPlayerImage(playerNumber);
    }

    public Image getPlayerImage() {
        return playerImage;
    }
}
