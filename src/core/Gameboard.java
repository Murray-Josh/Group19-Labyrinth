package core;

import holdables.Holdable;
import holdables.Tile;
import players.Player;
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
    private SilkBag           silkBag;
    private Matrix<Tile> tiles;
    private ArrayList<Player> players;
    private int width;
    private int height;
    private Style style;


    public Gameboard(String levelURL, ArrayList<Player> players, Style style)
            throws IOException, ClassNotFoundException {
        this.players = players;
        this.style=style;
        Level level = deserializeLevel(levelURL);
        this.width = level.getWidth();
        this.height = level.getHeight();
        tiles = new Matrix<Tile>(width, height);
        setTiles(level);
        setStartingPositions(players, level);
    }

    private void setStartingPositions(ArrayList<Player> players, Level level) {
       // players.forEach(player ->
    }

    /**
     * Fill the Gameboard up with tiles
     * @param level Level that the board is based on
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    private void setTiles(Level level) throws IndexOutOfBoundsException, NullPointerException{
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
     * @param levelPath Path to level file
     * @return Deserialised {@link Level}
     * @throws IOException If the Level cannot be found or read
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
     * @return Board width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Height of the board
     * @return Board height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the {@link Matrix} of {@link Tile}
     * @return Matrix of Tiles
     */
    public Matrix<Tile> getTiles() {
        return this.tiles;
    }
}
