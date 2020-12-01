package core;

import holdables.Holdable;
import holdables.Tile;
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

public class Gameboard implements Serializable {


    private SilkBag silkBag;
    private Matrix<Tile> tiles;
    private final ArrayList<Player> players;
    private final int width;
    private final int height;
    private final Style style;
    private Tile goalTile;

    /**
     * Creates a {@link Gameboard} from a Level Path, Style and a {@link java.util.Collection} of {@link PlayerProfile}
     * @param levelPath Path to the level file
     * @param style Style of the board
     * @param profiles {@link java.util.Collection} of {@link PlayerProfile} of the players
     * @throws IOException If the {@link Level} file could not be found.
     * @throws ClassNotFoundException If a class in the {@link Level} file could not be found
     * @throws IndexOutOfBoundsException If a tiles coordinate doesn't exist in the {@link Gameboard} {@link Matrix}
     * @throws NullPointerException If there aren't enough tiles to be placed on the gameboard
     */
    public Gameboard(String levelPath, Style style, ArrayList<PlayerProfile> profiles)
            throws IOException, ClassNotFoundException, IndexOutOfBoundsException, NullPointerException {
        this.style = style;
        Level level = deserializeLevel(levelPath);
        this.players = constructPlayers(profiles, level);
        this.width = level.getWidth();
        this.height = level.getHeight();
        tiles = new Matrix<>(width, height);
        setTiles(level);
    }


    private ArrayList<Player> constructPlayers(ArrayList<PlayerProfile> profiles, Level level) {
        ArrayList<Player> toReturn = new ArrayList<>();
        profiles.forEach(profile -> {
            toReturn.add(new Player(profile, level.getPlayerPosition(profiles.indexOf(profile) + 1), this.style, profiles.indexOf(profile) + 1));
        });
        return toReturn;
    }

    /**
     * Fill the Gameboard up with tiles
     *
     * @param level Level that the board is based on
     * @throws IndexOutOfBoundsException If there are more tiles than the gameboard allows
     * @throws NullPointerException If there aren't enough movable tiles for the gameboard
     */
    public void setTiles(Level level) throws IndexOutOfBoundsException, NullPointerException {
        /* Get Fixed and movable tiles */
        ArrayList<Tile> fixed = level.getFixed();
        ArrayList<Tile> movable = level.getMovables();

        /*Shuffle the movable tiles and add them to a ArrayDeque that queues them up ready to add to the Gameboard */
        Collections.shuffle(movable);
        ArrayDeque<Tile> movableTiles = new ArrayDeque<>(movable);

        /*Add all fixed tiles to the correct position on the board */
        fixed.forEach(tile -> tiles.set(tile.getCoordinate(), tile));

        /* Add a random movable tile to any space not filled in */
        tiles.forEach(tile -> {
            if (tile == null) {
                if (!movableTiles.isEmpty()) {
                    movableTiles.poll();
                } else {
                    throw new NullPointerException("Not enough movable tiles");
                }
            }
        });
    }



        /**
         * Takes the {@link Level} URL and deserializes it into a Level object
         *
         * @param levelPath Path to level file
         * @return Deserialised {@link Level}
         * @throws IOException            If the Level cannot be found or read
         * @throws ClassNotFoundException If a Class in the Serialised Level cannot be found
         */
    private Level deserializeLevel(String levelPath)
            throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(levelPath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Level level = (Level) objectInputStream.readObject();
        fileInputStream.close();
        objectInputStream.close();
        return level;
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
     * Width of the board
     *
     * @return Board width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Height of the board
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

    public void setGameboardTile(Coordinate coord, Tile tile){
        tiles.set(tile.getCoordinate(), tile);

    }


    public ArrayList<Player> makePlayers(Level level){
        PlayerProfile profile1 = null;
        PlayerProfile profile2 = null;
        PlayerProfile profile3 = null;
        PlayerProfile profile4 = null;

        ArrayList<Player> playerList= new ArrayList<Player>();

        Player playerOne = new Player(profile1, level.getPlayerOnePosition(), this.style, 1);
        playerList.add(playerOne);

        Player playerTwo = new Player(profile2, level.getPlayerTwoPosition(), this.style, 2);
        playerList.add(playerTwo);

        Player playerThree = new Player(profile3, level.getPlayerThreePosition(), this.style, 3);
        playerList.add(playerThree);

        Player playerFour = new Player(profile4, level.getPlayerFourPosition(), this.style, 4);
        playerList.add(playerFour);

        return playerList;
    }

    public Player getPlayers(int playerNum) {
        return players.get(playerNum);
    }

    public int getPlayersCount() {
        return players.size();
    }
    public SilkBag getSilkBag() {
        return silkBag;
    }

    public void setSilkBag(SilkBag silkBag) {
        this.silkBag = silkBag;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Style getStyle() {
        return style;
    }

    public Coordinate getGoal() {
        return goalTile.getCoordinate();
    }
}
