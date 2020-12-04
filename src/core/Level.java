package core;

import constants.Angle;
import constants.TileType;
import holdables.Tile;
import holdables.TileEffect;
import styles.PirateStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;


/**
 * Represents a Level that can be used in the game
 *
 * @author Joseph Omar
 * @author Martin Samm
 * @author Joshua Murray
 * @author Jordy Martinson
 * @version 2.0
 */
public class Level {

    private static final int MAX_PLAYERS = 4;
    private ArrayList<Tile> fixed = new ArrayList<>();
    private ArrayList<Tile> movables = new ArrayList<>();
    private ArrayList<Tile> actions = new ArrayList<>();
    private int width;
    private int height;
    private Coordinate playerOnePosition;
    private Coordinate playerTwoPosition;
    private Coordinate playerThreePosition;
    private Coordinate playerFourPosition;

    /**
     * Constructs an empty {@link Level}
     */
    public Level() {
        this.width = 0;
        this.height = 0;
        this.movables = new ArrayList<Tile>();
        this.fixed = new ArrayList<Tile>();
        this.playerOnePosition = new Coordinate(0, 0);
        this.playerTwoPosition = new Coordinate(0, 0);
        this.playerThreePosition = new Coordinate(0, 0);
        this.playerFourPosition = new Coordinate(0, 0);
        this.action = new ArrayList<Tile>();

    }


    /**
     * Constructs a fully specified level
     *
     * @param fixed    Collection of fixed tiles
     * @param width    Width of the board
     * @param height   Height of the board
     * @param movables Number of movable tiles
     */
    public Level(ArrayList<Tile> fixed, int width, int height, ArrayList<Tile> movables, Coordinate p1Pos, Coordinate p2Pos, Coordinate p3Pos, Coordinate p4Pos) {
        this.width = width;
        this.height = height;
        this.movables = new ArrayList<Tile>();
        this.fixed = fixed;
        this.playerOnePosition = p1Pos;
        this.playerTwoPosition = p2Pos;
        this.playerThreePosition = p3Pos;
        this.playerFourPosition = p4Pos;
    }

    /**
     * Constructs a partially specified level
     *
     * @param width    Width of the board
     * @param height   Height of the board
     * @param movables Number of movable tiles
     */
    public Level(int width, int height, ArrayList<Tile> movables) {
        this.width = width;
        this.height = height;
        this.movables = new ArrayList<Tile>();
        this.fixed = new ArrayList<Tile>();
        this.action = new ArrayList<Tile>();
    }

    /**
     * Constructs a partially specified level
     *
     * @param width  Width of the board
     * @param height Height of the board
     */
    public Level(int width, int height) {

        this.movables = new ArrayList<Tile>();
        this.fixed = new ArrayList<Tile>();
    }

    /**
     * Constructs a level with file path
     *
     * @param path Path of level object
     */
    public Level(String path) throws FileNotFoundException {
        readGameboardFile(path);
    }

    /**
     * Adds a collection of fixed tiles
     *
     * @param fixed {@link Collection} of fixed tiles
     */
    public void addFixed(ArrayList<Tile> fixed) {
        this.fixed.addAll(fixed);
    }

    /**
     * Adds a fixed Tile
     *
     * @param type       {@link TileType} of the {@link Tile}
     * @param coordinate {@link Coordinate} of the tile
     * @param angle      {@link Angle} of the {@link Tile}
     */
    public void addFixed(TileType type, Coordinate coordinate, Angle angle) {
        this.fixed.add(new Tile(coordinate, type, angle, true));
    }

    /**
     * Gets all of the Fixed Tiles
     *
     * @return Fixed Tiles
     */
    public ArrayList<Tile> getFixed() {
        return this.fixed;
    }

    /**
     * Sets the fixed tiles collection to one specified
     *
     * @param fixed Collection of fixed tiles
     */
    public void setFixed(ArrayList<Tile> fixed) {
        this.fixed = fixed;
    }

    public void addToFixed(Tile fixedTile) {
        this.fixed.add(fixedTile);
    }

    /**
     * Gets the tile an index
     *
     * @param index index of the tile
     * @return The tile at index
     * @throws IndexOutOfBoundsException If the index is too big or too small
     */
    public Tile getFixed(int index) throws IndexOutOfBoundsException {
        return this.fixed.get(index);
    }

    /**
     * If tile exists as a fixed tile
     *
     * @param tile Tile to check
     * @return If the tile is fixed
     */
    public boolean fixedExists(Tile tile) {
        return this.fixed.contains(tile);
    }

    /**
     * Sets the board size
     *
     * @param width  Width of the board
     * @param height Height of the board
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the size of the board
     *
     * @return width, height
     */
    public int[] getSize() {
        return new int[]{this.width, this.height};
    }

    /**
     * Gets the board width
     *
     * @return Board width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the board width
     *
     * @param width Board Width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the board height
     *
     * @return board height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the board Height
     *
     * @param height Value for height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the number of fixed tiles
     *
     * @return Number of fixed Tiles
     */
    public int fixedAmount() {
        return this.fixed.size();
    }

    /**
     * Gets coordinate of first player
     *
     * @return Coordinate of first player
     */
    public Coordinate getPlayerOnePosition() {
        return playerOnePosition;
    }

    /**
     * Sets coordinate of first player
     *
     * @param playerOnePosition Coordinate of first player
     */
    public void setPlayerOnePosition(Coordinate playerOnePosition) {
        this.playerOnePosition = playerOnePosition;
    }

    /**
     * Gets coordinate of second player
     *
     * @return Coordinate of second player
     */
    public Coordinate getPlayerTwoPosition() {
        return playerTwoPosition;
    }

    /**
     * Sets coordinate of second player
     *
     * @param playerTwoPosition Coordinate of second player
     */
    public void setPlayerTwoPosition(Coordinate playerTwoPosition) {
        this.playerTwoPosition = playerTwoPosition;
    }

    /**
     * Gets coordinate of third player
     *
     * @return Coordinate of third player
     */
    public Coordinate getPlayerThreePosition() {
        return playerThreePosition;
    }

    /**
     * Sets coordinate of third player
     *
     * @param playerThreePosition Coordinate of third player
     */
    public void setPlayerThreePosition(Coordinate playerThreePosition) {
        this.playerThreePosition = playerThreePosition;
    }

    /**
     * Gets coordinate of fourth player
     *
     * @return Coordinate of fourth player
     */
    public Coordinate getPlayerFourPosition() {
        return playerFourPosition;
    }

    /**
     * Sets coordinate of fourth player
     *
     * @param playerFourPosition Coordinate of fourth player
     */
    public void setPlayerFourPosition(Coordinate playerFourPosition) {
        this.playerFourPosition = playerFourPosition;
    }

    /**
     * Gets list of movable tiles
     *
     * @return ArrayList of movable tiles
     */
    public ArrayList<Tile> getMovables() {
        return movables;
    }

    /**
     * Sets list of movable tiles
     *
     * @param movables ArrayList of movable tiles
     */
    public void setMovables(ArrayList<Tile> movables) {
        this.movables = movables;
    }

    /**
     * Set coordinate of a player with num
     *
     * @param coordinate   Coordinate of player
     * @param playerNumber Player number
     */
    public void setPlayerPosition(Coordinate coordinate, int playerNumber) {
        switch (playerNumber) {
            case 1:
                this.playerOnePosition = coordinate;
                break;
            case 2:
                this.playerTwoPosition = coordinate;
                break;
            case 3:
                this.playerThreePosition = coordinate;
                break;
            case 4:
                this.playerFourPosition = coordinate;
                break;
            default:
                throw new IllegalArgumentException("Player number not in range");
        }
    }

    /**
     * Get coordinate of specified player
     *
     * @param playerNumber Players number
     * @return Coordinate of player
     */
    public Coordinate getPlayerPosition(int playerNumber) {
        switch (playerNumber) {
            case 1:
                return this.playerOnePosition;
            case 2:
                return this.playerTwoPosition;
            case 3:
                return this.playerThreePosition;
            case 4:
                return this.playerFourPosition;
            default:
                throw new IllegalArgumentException("Player number not in range");
        }
    }

    /**
     * Reads a File containing the level
     *
     * @param fileName Path to the file to load
     */
    public void readGameboardFile(String fileName) throws FileNotFoundException {
        Scanner in;
        File file = new File(fileName);
        in = new Scanner(file);
        readGameboard(in);
    }

    /**
     * Reads file and calls methods to break it down
     *
     * @param in Scanner of whole game file
     */
    private void readGameboard(Scanner in) {
        readSize(in.nextLine());
        int fixedTile = Integer.parseInt(in.nextLine());
        readFixed(fixedTile, in);
        System.out.println(fixed);
        int unfixed = Integer.parseInt(in.nextLine());
        if (unfixed > 0) {
            readUnfixed(unfixed, in);
        }
        System.out.println(unfixed);
        setStartCoordinates(in);
        int action = Integer.parseInt(in.nextLine());
        if (action > 0) {
            readAction(action, in);
        }
    }

    /**
     * Gets the Size of the board from the level file
     *
     * @param sLine Line containing gameboard size
     */
    private void readSize(String sLine) {
        String[] sizeStrArray = sLine.split(",");
        this.setHeight(Integer.parseInt(sizeStrArray[0]));
        this.setWidth(Integer.parseInt(sizeStrArray[1]));
    }

    /**
     * Reads and adds fixed tiles to fixed array list
     *
     * @param fixed The fixed tile to read
     * @param in    Scanner to get the data from
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


            System.out.println(fTileType);
            System.out.println(Angle.toAngle(turn));

            addToFixed(new Tile(new Coordinate(x, y), fTileType, Angle.toAngle(turn), true));

        }
    }

    /**
     * Reads and adds all unfixed files to movables list
     *
     * @param unfixed Number of unfixed tiles
     * @param in      Scanner of all unfixed tiles
     */
    private void readUnfixed(int unfixed, Scanner in) {
        Angle[] angleArray = new Angle[]{Angle.UP, Angle.DOWN, Angle.LEFT, Angle.RIGHT};

        for (int i = 0; i < unfixed; i++) {
            String unfTLine = in.nextLine();
            TileType unfTLineType = readTileType(unfTLine);
            Random rand = new Random();
            Angle randAngle = angleArray[rand.nextInt(angleArray.length)];

            movables.add(new Tile(unfTLineType, randAngle, false));
        }
    }

    /**
     * Reads file and determines start location for all players
     * Then sets each player's start
     *
     * @param in Scanner to read from
     */
    private void setStartCoordinates(Scanner in) {

        in.useDelimiter(",");
        for (int i = 1; i <= MAX_PLAYERS; i++) {
            String cLine = in.nextLine();
            String[] coordArray = cLine.split(",");
            Coordinate playCoord = new Coordinate(Integer.parseInt(coordArray[1]), Integer.parseInt(coordArray[2]));
            System.out.println(i);
            setPlayerPosition(playCoord, i);
        }
    }
    
    /**
     * Reads action tiles
     * @param action Number of action tiles
     * @param in Scanner input
     */
    private void readAction(int action, Scanner in) {
        for (int i = 0; i < action; i++) {
            String actTLine = in.nextLine();
            TileType actTLineType = readTileType(actTLine);

            actions.add(new Tile(actTLineType, Angle.DOWN, false));
        }
    }


    /**
     * Takes in a string value of tiletype and outputs object
     *
     * @param tileTypeString String of tile type
     * @return TileType object
     */
    private TileType readTileType(String tileTypeString) {
        TileType tileTypeObject;

        switch (tileTypeString) {
            case "corner":
                return TileType.CORNER;
            case "junction":
                return TileType.JUNCTION;
            case "straight":
                return TileType.STRAIGHT;
            case "goal":
                return TileType.GOAL;
            case "double":
                return TileType.DOUBLE_MOVE;
            case "back":
                return TileType.BACKTRACK;
            case "fire":
                return TileType.FIRE;
            case "ice":
                return TileType.ICE;
            default:
                throw new IllegalStateException("Unexpected value: " + tileTypeString);
        }
    }
}
