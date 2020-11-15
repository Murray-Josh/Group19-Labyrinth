import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

/**
 * A Gameboard  for players to play on
 *
 * @author Joshua Murray
 * @version 1.0
 */
public class Gameboard {
    private ArrayList<Player> players;
    private int[] size;
    private Tile[][] board;
    private Style style;
    Random rand = new Random();
    private final static int ROTATION_LOCK = 45;

    /**
     * Constructs a new Gameboard
     *
     * @param size    Game board size
     * @param style   Style of the game
     * @param players List of players in game
     */
    public Gameboard(int[] size, Style style, ArrayList<Player> players) {
        setPlayers(players);
        setSize(size);
        generateBoard(size);
        setStyle(style);
        //board = new Tile[size[0]][size[1]];
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
     * Gets players in game
     *
     * @return List of players in game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Set size of Gameboard
     *
     * @param size Size of Gameboard
     */
    public void setSize(int[] size) {
        this.size = size;
    }

    /**
     * Get size of Gameboard
     *
     * @return size of Gameboard
     */
    public int[] getSize() {
        return size;
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
     * Get style of game
     *
     * @return style of game
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Checks whether tile should be fixed
     * Currently only returns false
     *
     * @return Boolean of fixed or not
     */
    private boolean isFixed() {
        return false;
    }

    /**
     * Generates Gameboard from size
     *
     * @param size Size of Gameboard
     */
    private void generateBoard(int[] size) {
        board = new Tile[size[0]][size[1]];
        for (int y = 0; y < size[1]; y++) {
            for (int x = 0; x < size[0]; x++) {
                TileType tileType = new TileType();
                int randAngle = (rand.nextInt(4) * ROTATION_LOCK);
                board[y][x] = new Tile(new Coordinate(x, y), tileType, style, randAngle, isFixed());
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
                s.append(board[x][y]).append(" | ");
            }
            System.out.println(s);
        }
    }
}
