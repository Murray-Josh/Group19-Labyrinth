import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Gameboard  for players to play on
 *
 * @author Joshua Murray
 * @version 1.0
 */
public class Gameboard implements Serializable {
    private final static int ROTATION_LOCK = 45;
    Random rand = new Random();
    private ArrayList<Player> players;
    private int[] size;
    private Tile[][] board;
    private Style style;

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
     * Get size of Gameboard
     *
     * @return size of Gameboard
     */
    public int[] getSize() {
        return size;
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
                int randAngle = (rand.nextInt(4) * ROTATION_LOCK);
                TileType[] typeArray = new TileType[]{new CornerTile(), new StraightTile(), new JunctionTile()};
                int randType = rand.nextInt(typeArray.length);
                board[y][x] = new Tile(new Coordinate(x, y), typeArray[randType], style, randAngle, isFixed());
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
