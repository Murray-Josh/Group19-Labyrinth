package core;

import holdables.Holdable;
import holdables.Tile;
import players.Player;
import players.PlayerProfile;
import players.Profiles;
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
    private Level level;
    private SilkBag silkBag;
    private final Matrix<Tile> tiles;
    private final ArrayList<Player> players;
    private final int width;
    private final int height;
    private final Style style;


    public Gameboard(String levelURL, Style style)
            throws IOException, ClassNotFoundException {
        this.style = style;
        Level level = deserializeLevel(levelURL);
        this.players = makePlayers(level);

        this.width = level.getWidth();
        this.height = level.getHeight();
        tiles = new Matrix<Tile>(width, height);
        setTiles(level);
        setStartingPositions(players, level);
    }

    private void setStartingPositions(ArrayList<Player> players, Level level) {
        players.forEach(player ->
                player.setCoordinate(level.getPlayerPosition(player.getPlayerNum()), player.getPlayerNum()));
    }

    /**
     * Fill the Gameboard up with tiles
     *
     * @param level Level that the board is based on
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    private void setTiles(Level level) throws IndexOutOfBoundsException, NullPointerException {
        /* Get Fixed and movable tiles */
        ArrayList<Tile> fixed = level.getFixed();
        ArrayList<Tile> movable = level.getMovables();

        /*Shuffle the movable tiles and add them to a ArrayDeque that queues them up ready to add to the Gameboard */
        Collections.shuffle(movable);
        ArrayDeque<Tile> movableTiles = new ArrayDeque<>(movable);

        /*Add all fixed tiles to the correct position on the board */
        fixed.forEach(tile -> {
            tiles.set(tile.getCoordinate(), tile);
        });

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
        ArrayList<Holdable> toReturn = new ArrayList<Holdable>();
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

    public int getNumOfPLayers() {
        return players.size();
    }
}
