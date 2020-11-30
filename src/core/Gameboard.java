package core;

import constants.ErrorMsg;
import constants.Title;
import constants.Window;
import controllers.StageController;
import holdables.Holdable;
import holdables.Tile;
import players.Player;
import styles.Style;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.addAll;

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
        setTiles(level);


    }

    private void setTiles(Level level) throws IndexOutOfBoundsException, NullPointerException{
        ArrayList<Tile> fixed = level.getFixed();
        fixed.forEach(tile -> {
            tiles.set(tile.getCoordinate(), tile);
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

    private ArrayList<Holdable> shuffle(ArrayList<Holdable> list) {
        Collections.shuffle(list);
        return list;
    }

    private ArrayList<Holdable> combine(ArrayList<Holdable> listA, ArrayList<Holdable> listB) {
        ArrayList<Holdable> toReturn = new ArrayList<Holdable>();
        Stream.of(listA, listB).forEach(toReturn::addAll);
        return toReturn;
    }



}
