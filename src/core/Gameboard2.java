package core;

import holdables.*;
import players.Player;
import styles.CarStyle;
import styles.PirateStyle;
import styles.Style;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
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
    private ArrayList<Player> players = new ArrayList<>();
    private int[] size = new int[2];
    private Tile[][] board;
    private Style style;
    private final int MAX_PLAYERS = 4;
    private Coordinate[] startCoords = new Coordinate[4];

    public Gameboard2(String fileName) {
        Scanner in;
        style = new PirateStyle() ;
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
        setStartCoords(in);
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
     * Reads file and determines start location for all players
     * Then sets each player's start
     * @param in Scanner to read from
     */
    private void setStartCoords(Scanner in) {

//        players.add(new Player(new PlayerProfile("a"), new Coordinate(0,0), getStyle(), 1));
//        players.add(new Player(new PlayerProfile("b"), new Coordinate(0,0), getStyle(), 2));
//        players.add(new Player(new PlayerProfile("c"), new Coordinate(0,0), getStyle(), 3));
//        players.add(new Player(new PlayerProfile("d"), new Coordinate(0,0), getStyle(), 4));

        in.useDelimiter(",");
        for(int i = 0; i < MAX_PLAYERS; i++) {
            String cLine = in.nextLine();
            String[] coordArray = cLine.split(",");
            Coordinate playCoord = new Coordinate(Integer.parseInt(coordArray[1]), Integer.parseInt(coordArray[2]));
            startCoords[i] = playCoord;
        }

        for(int j = 0; j < players.size(); j++) {
            players.get(j).setStart(startCoords[j]);
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
                tileTypeObject = TileType.CORNER;
                break;
            case "junction":
                tileTypeObject = TileType.JUNCTION;
                break;

            case "straight":
                tileTypeObject = TileType.STRAIGHT;
                break;

            case "goal":
                tileTypeObject = TileType.GOAL;
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

    public void setTile(Tile tile, int x, int y) {
        board[x][y] = tile;
    }
    
    /**
     *  Returns true if tile is a start tile
     * @param x X coordinate
     * @param y Y coordinate
     * @return if tile is a start tile
     */
    public boolean isStart(int x, int y) {
        boolean result = false;
        for(int i = 0; i < startCoords.length; i++) {
            if (getTile(x, y).getCoordinate().getX() == startCoords[i].getX()) {
                if (getTile(x, y).getCoordinate().getY() == startCoords[i].getY()) {
                    result = true;
                }
            }
        }
        return result;
    }

    //just for testing
    public static void main(String[] args) {
        Gameboard2 g = new Gameboard2("src/file/GBOne");
        g.boardToString();
//        System.out.println(g.isStart(0,0));
//        System.out.println(g.isStart(4,8));
//        System.out.println(g.isStart(2,5));
//        System.out.println(g.isStart(4,5));
//        System.out.println(g.isStart(8,8));
//        System.out.println(g.startCoords[0].getX() + ", " + g.startCoords[0].getY());
//        System.out.println(g.startCoords[1].getX() + ", " + g.startCoords[0].getY());
    }
}
