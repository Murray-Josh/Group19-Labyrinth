package core;

import holdables.*;
import players.Player;
import styles.Style;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Core.Gameboard  for players to play on
 *
 * @author Joshua Murray
 * @version 1.0
 */
public class Gameboard implements Serializable {
    private final static int ROTATION_LOCK = 45;
    Random rand = new Random();
    GoalTile goal = new GoalTile();
    Tile goalTile;
    private ArrayList<Player> players;
    private int[] size;
    private Tile[][] board;
    private Style style;
    private SilkBag silkBag;


    /**
     * Constructs a new Core.Gameboard
     *
     * @param size    Game board size
     * @param style   Styles.Style of the game
     * @param players List of players in game
     */
    public Gameboard(int[] size, Style style, ArrayList<Player> players) {
        setPlayers(players);
        setSize(size);
        generateBoard(size);
        setStyle(style);
        silkBag = new SilkBag();
        //board = new Holdables.Tile[size[0]][size[1]];
    }

    /**
     * Gets players in game
     *
     * @return List of players in game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Set players in game
     *
     * @param players List of players in game
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Get size of Core.Gameboard
     *
     * @return size of Core.Gameboard
     */
    public int[] getSize() {
        return size;
    }

    /**
     * Set size of Core.Gameboard
     *
     * @param size Size of Core.Gameboard
     */
    public void setSize(int[] size) {
        this.size = size;
    }

    /**
     * Get style of game
     *
     * @return style of game
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Set style of game
     *
     * @param style style of game
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Checks whether tile should be fixed
     *
     * @return Boolean of fixed or not
     */
    private boolean isFixed(int x, int y) {
        return (x == size[0]) && (y == 0) || (x == size[0]) && (y == size[1]) || (x == 0) && (y == 0) || (x == 0) && (y == size[1]);
    }

    /**
     * Generates Core.Gameboard from size
     *
     * @param size Size of Core.Gameboard
     */
    private void generateBoard(int[] size) {
        board = new Tile[size[0]][size[1]];
        int middleY = size[1] / 2;
        int middleX = size[0] / 2;
        Tile goalTile = new Tile(new Coordinate(middleX, middleY), goal, style, 0, true);
        board[middleY][middleX] = goalTile;
        for (int y = 0; y < size[1]; y++) {
            for (int x = 0; x < size[0]; x++) {
                if (board[y][x] == null) {
                    int randAngle = (rand.nextInt(4) * ROTATION_LOCK);
                    TileType[] typeArray = new TileType[]{new CornerTile(), new StraightTile(), new JunctionTile()};
                    int randType = rand.nextInt(typeArray.length);
                    board[y][x] = new Tile(new Coordinate(x, y), typeArray[randType], style, randAngle, isFixed(x, y));
                }
            }
        }
    }

    /**
     * Outputs board as a string in a grid format
     */
    public void boardToString() {
        for (int y = 0; y < size[1]; y++) {
            System.out.println("\n");
            StringBuilder s = new StringBuilder();
            for (int x = 0; x < size[0]; x++) {
                s.append((board[x][y]).getType().toString()).append(" | ");
            }
            System.out.println(s);
        }
    }
}
