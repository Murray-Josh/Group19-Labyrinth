package core;

import holdables.*;
import players.Player;
import styles.Style;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Random;

/*
    Notes: Board dictates EXACT set of tiles, board files need to reflect this
           Board file: size1, size2 \n numFixed \n tile-n(coord x, coord y, type, rotation) (\n each) numUnfixed \n tile-n2 (type) \n player starts
           Need to implement player starting positions
           Need to implement effect tiles into bag

 */

/**
 * A Core.Gameboard  for players to play on
 *
 * @author Joshua Murray, Jordy Martinson
 * @version 1.0
 */
public class Gameboard2 implements Serializable {
    private final static int ROTATION_LOCK = 45;
    private final SilkBag silkBag = new SilkBag();
    private Random rand = new Random();
    private String goal = "goal";
    private Tile goalTile;
    private ArrayList<Player> players;
    private int[] size = new int[2];
    private Tile[][] board;
    private Style style;

    public Gameboard2(String fileName) {
        Scanner in;
        try {
            File file = new File(fileName);
            in = new Scanner(file);
            readGameboard(in);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find " + fileName);
            System.exit(0);
        }
    }

    /**
     * Reads file and calls methods to break it down
     *
     * @param in
     */
    private void readGameboard(Scanner in) {
        readSize(in.nextLine());
        int fixed = Integer.parseInt(in.nextLine());
        readFixed(fixed, in);
        int unfixed = Integer.parseInt(in.nextLine());
        if (unfixed > 0) {
            readUnfixed(unfixed, in);
        }
    }

    /**
     * Reads board size
     *
     * @param sLine
     * @return
     */
    private int[] readSize(String sLine) {
        String[] sizeStrArray = sLine.split(",");
        size[0] = Integer.parseInt(sizeStrArray[0]);
        size[1] = Integer.parseInt(sizeStrArray[1]);

        board = new Tile[size[0]][size[1]];
        return size;
    }

    /**
     * Reads fixed files and places onto board where coordinates dictate
     *
     * @param fixed
     * @param in
     */
    private void readFixed(int fixed, Scanner in) {
        in.useDelimiter(",");

        for (int i = 0; i < fixed; i++) {
            String tLine = in.nextLine();
            String[] fixedArray = tLine.split(",");
            int x = Integer.parseInt(fixedArray[0]);
            int y = Integer.parseInt(fixedArray[1]);
            String fTile = fixedArray[2];
            TileType fTileType = readTileType(fTile);
            int turn = Integer.parseInt(fixedArray[3]);

            Tile newTile = new Tile(new Coordinate(x, y), fTileType, getStyle(), turn, true);
            /* Potentially redundant code
            if (fTile.equals("goal")) {
                newTile.setType(new GoalTile());
                goalTile = newTile;
            }
             */
            board[x][y] = newTile;
            board[x][y].setFixed();
        }
    }

    /**
     * Goes through each unfixed tile and draws from them randomly, ensuring tiles are not drawn twice
     * Then places on empty board or into bag if already a tile there
     *
     * @param unfixed
     * @param in
     */
    private void readUnfixed(int unfixed, Scanner in) {
        ArrayList<TileType> tempList = new ArrayList<>();

        for (int i = 0; i < unfixed; i++) {
            String unfTLine = in.nextLine();
            TileType unfTLineType = readTileType(unfTLine);
            tempList.add(unfTLineType);
        }

        // The spec mentions filling the board then placing the remaining tiles in the bag
        // Could be worth doing it that way specifically
        int[] angleArray = new int[]{0, 90, 180, 270};
        for (int j = 0; j < size[0]; j++) {
            for (int k = 0; k < size[1]; k++) {
                int randTile;
                if (tempList.size() >= 1) {
                    randTile = rand.nextInt(tempList.size());
                } else {
                    randTile = 0;
                }
                int randAngle = rand.nextInt(angleArray.length);
                Tile newTile = new Tile(tempList.get(randTile), style, angleArray[randAngle], false);

                if (!isFixed(j, k)) {
                    newTile.setCoordinate(new Coordinate(j, k));
                    board[j][k] = newTile;
                } else {
                    silkBag.enqueue(newTile);
                }
                tempList.remove(randTile);
            }
        }
    }

    /**
     * Takes in a string value of tiletype and outputs object
     *
     * @param tileTypeString
     * @return
     */
    private TileType readTileType(String tileTypeString) {
        TileType tileTypeObject;

        switch (tileTypeString) {
            case "corner":
                tileTypeObject = new CornerTile();
                break;
            case "junction":
                tileTypeObject = new JunctionTile();
                break;

            case "straight":
                tileTypeObject = new StraightTile();
                break;

            case "goal":
                tileTypeObject = new GoalTile();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tileTypeString);

        }
        return tileTypeObject;
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
        if (!(board[x][y] == null)) {
            return board[x][y].isFixed();
        } else {
            return false;
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
                s.append((board[x][y]).getType());
                s.append(" " + (board[x][y]).getAngle()).append(" | ");
            }
            System.out.println(s);
        }
    }

    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    public static void main(String[] args) {
        Gameboard2 g = new Gameboard2("src/file/GBOne");
        g.boardToString();
    }
}
