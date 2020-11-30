package core;

import holdables.Holdable;
import holdables.Tile;
import players.Player;
import styles.Style;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
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

    }

    private void setTiles(Level level) throws IndexOutOfBoundsException, NullPointerException{
        ArrayList<Tile> fixed = level.getFixed();
        ArrayList<Tile> movable = (level.getMovables());
        Collections.shuffle(movable);
        fixed.forEach(tile -> {
            tiles.set(tile.getCoordinate(), tile);
        });
        tiles.forEach(tile -> {
            if (tile == null) {

            }
        });
    }

    private Level deserializeLevel(String levelURL)
            throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(levelURL);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Level level = (Level) objectInputStream.readObject();
        fileInputStream.close();
        objectInputStream.close();
        return level;
    }


    private ArrayList<Holdable> combine(ArrayList<? extends Holdable> listA, ArrayList<? extends Holdable> listB) {
        ArrayList<Holdable> toReturn = new ArrayList<Holdable>();
        Stream.of(listA, listB).forEach(toReturn::addAll);
        return toReturn;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
