package core;

import constants.Angle;
import holdables.Effect;
import holdables.Holdable;
import holdables.PlayerEffect;
import holdables.Tile;
import java.util.Arrays;
import players.Player;
import players.PlayerProfile;
import styles.Style;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * Gameboard class for players to play on
 *
 * @author Joshua Murray
 * @author Joseph Omar
 * @author Jordy Martinson
 * @version 2.2
 */
public class Gameboard implements Serializable {


    private final ArrayList<Player> players;
    private final int width;
    private final int height;
    private Style style;
    private SilkBag silkBag;
    private final Matrix<Tile> tiles;


    /**
     * Creates a {@link Gameboard} from a Level Path, Style and a {@link java.util.Collection} of {@link PlayerProfile}
     *
     * @param level Level to create a board from
     * @param style     Style of the board
     * @param profiles  {@link java.util.Collection} of {@link PlayerProfile} of the players
     * @throws IOException               If the {@link Level} file could not be found.
     * @throws ClassNotFoundException    If a class in the {@link Level} file could not be found
     * @throws IndexOutOfBoundsException If a tiles coordinate doesn't exist in the {@link Gameboard} {@link Matrix}
     * @throws NullPointerException      If there aren't enough tiles to be placed on the gameboard
     */
    public Gameboard(Level level, Style style, ArrayList<PlayerProfile> profiles)
            throws IOException, ClassNotFoundException, IndexOutOfBoundsException, NullPointerException {
        this.style = style;
        this.players = constructPlayers(profiles, level);
        this.width = level.getWidth();
        this.height = level.getHeight();
        tiles = new Matrix<>(width, height);
        setTiles(level);
    }

    /**
     * Constructs players to game from given info
     *
     * @param profiles List of player profiles
     * @param level    Level file
     * @return Array list of players
     */
    private ArrayList<Player> constructPlayers(ArrayList<PlayerProfile> profiles, Level level) {
        ArrayList<Player> toReturn = new ArrayList<>();
        for (PlayerProfile profile : profiles) {
            int index = profiles.indexOf(profile) + 1;
            Player p = new Player();
            p.setProfile(profile);
            p.setStart(level.getPlayerPosition(index));
            p.setStyle(this.style);
            p.setPlayerImage(index);
            p.setActiveEffect(PlayerEffect.NONE);
            p.setCurrentDirection(Angle.UP);
            p.setPlayerNum(index);
            toReturn.add(p);
        }
        return toReturn;
    }


    /**
     * Combines two Arraylists of holdable implementing objects together
     *
     * @param listA List to combine
     * @param listB Other list to combine
     * @return Combined List
     */
    private ArrayList<Holdable> combine(ArrayList<? extends Holdable> listA, ArrayList<? extends Holdable> listB) {
        ArrayList<Holdable> toReturn = new ArrayList<>();
        Stream.of(listA, listB).forEach(toReturn::addAll);
        return toReturn;
    }

    /**
     * Gets width of the board
     *
     * @return Board width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height of the board
     *
     * @return Board height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the {@link Matrix} of {@link Tile}
     *
     * @return Matrix of Tiles
     */
    public Matrix<Tile> getTiles() {
        return this.tiles;
    }

    /**
     * Fills the Gameboard up with tiles
     *
     * @param level Level that the board is based on
     * @throws IndexOutOfBoundsException If there are more tiles than the gameboard allows
     * @throws NullPointerException      If there aren't enough movable tiles for the gameboard
     */
    public void setTiles(Level level) throws IndexOutOfBoundsException, NullPointerException {
        /* Get Fixed and movable tiles */
        ArrayList<Tile> fixed = level.getFixed();
        ArrayList<Tile> movable = level.getMovables();
        fixed.forEach(tile -> tile.setStyle(this.style));
        movable.forEach(tile -> tile.setStyle(this.style));
        /*Shuffle the movable tiles and add them to a ArrayDeque that queues them up ready to add to the Gameboard */
        Collections.shuffle(movable);
        ArrayDeque<Tile> movableTiles = new ArrayDeque<>(movable);

        /*Add all fixed tiles to the correct position on the board */
        fixed.forEach(tile -> tiles.set(tile.getCoordinate(), tile));

        /* Add a random movable tile to any space not filled in */
        for(int i = 0; i < tiles.getWidth(); i++) {
            for(int j = 0; j < tiles.getHeight(); j++) {
                if(tiles.get(i,j) == null) {
                    Tile temp = movableTiles.poll();
                    if (temp != null) {
                        temp.setCoordinate(new Coordinate(i, j));
                        tiles.set(i, j, temp);
                    } else {
                        throw new NullPointerException("Not enough movable tiles to create a gameboard");
                    }
                }
            }
        }
        movable.retainAll(movableTiles);
        createSilkBag(level, movable);
    }

    /**
     * Creates a silkbag object from the leftover tiles and the action tiles from the level
     * @param level Level file to get the action tiles from
     * @param movable The leftover movable tiles
     */
    private void createSilkBag(Level level, ArrayList<Tile> movable) {

        ArrayList<Holdable> combined = combine(movable, level.getActions());
        this.silkBag=new SilkBag(combined);
    }

    /**
     * Set a gameboard tile
     *
     * @param coord Coordinates for tile
     * @param tile  Tile object to be set
     */
    public void setGameboardTile(Coordinate coord, Tile tile) {
        tiles.set(tile.getCoordinate(), tile);

    }


    /**
     * Gets a player
     *
     * @param playerNum Num of player to get
     * @return Player object of requested player
     */
    public Player getPlayers(int playerNum) {
        return players.get(playerNum);
    }

    /**
     * Gets number of players in game
     *
     * @return Number of players in game
     */
    public int getPlayersCount() {
        return players.size();
    }

    /**
     * Gets silk bag for game
     *
     * @return Silk bag from game
     */
    public SilkBag getSilkBag() {
        return silkBag;
    }

    /**
     * Sets silk bag for game
     *
     * @param silkBag Silk bag for game
     */
    public void setSilkBag(SilkBag silkBag) {
        this.silkBag = silkBag;
    }

    /**
     * Get list of players in game
     *
     * @return Arraylist of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets style of game
     *
     * @return Style of game
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Sets the gamebaord style
     * @param style New Style
     */
    public void setStyle(Style style){this.style=style;}
}
