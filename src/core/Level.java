package core;

import constants.Angle;
import constants.TileType;
import holdables.Tile;
import styles.CarStyle;
import styles.PirateStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.Random;


/**
 * Represents a Level that can be used in the game
 *
 * @author Joseph Omar
 * @author Martin Samm
 * @author Joshua Murray
 * @version 2.0
 */
public class Level implements Serializable {

    private ArrayList<Tile> fixed;
    private ArrayList<Tile> movables;

    private int             width;
    private int              height;
    private Coordinate playerOnePosition;
    private Coordinate playerTwoPosition;
    private Coordinate playerThreePosition;
    private Coordinate playerFourPosition;

    private static final int MAX_PLAYERS = 4;

    /**
     * Constructs an empty {@link Level}
     */
    public Level() {
        this.width=0;
        this.height=0;
        this.movables=new ArrayList<Tile>();
        this.fixed=new ArrayList<Tile>();
        this.playerOnePosition = new Coordinate(0,0);
        this.playerTwoPosition = new Coordinate(0,0);
        this.playerThreePosition = new Coordinate(0,0);
        this.playerFourPosition = new Coordinate(0,0);

    }


    /**
     * Constructs a fully specified level
     * @param fixed Collection of fixed tiles
     * @param width Width of the board
     * @param height Height of the board
     * @param movables Number of movable tiles
     */
    public Level(ArrayList<Tile> fixed, int width, int height, ArrayList<Tile> movables, Coordinate p1Pos, Coordinate p2Pos, Coordinate p3Pos, Coordinate p4Pos) {
        this.width=width;
        this.height=height;
        this.movables=new ArrayList<Tile>();
        this.fixed=fixed;
        this.playerOnePosition = p1Pos;
        this.playerTwoPosition = p2Pos;
        this.playerThreePosition = p3Pos;
        this. playerFourPosition = p4Pos;
    }

    /**
     * Constuts a partially specified level
     * @param width Width of the board
     * @param height Height of the board
     * @param movables Number of movable tiles
     */
    public Level(int width, int height,  ArrayList<Tile> movables) {
        this.width=width;
        this.height=height;
        this.movables=new ArrayList<Tile>();
        this.fixed=new ArrayList<Tile>();
    }

    /**
     * Constructs a partially specified level
     * @param width Width of the board
     * @param height Height of the board
     */
    public Level(int width, int height) {

        this.movables = new ArrayList<Tile>();
        this.fixed=new ArrayList<Tile>();
    }
    public Level(String path) {
        this.width=width;
        this.height=height;
        this.movables=new ArrayList<Tile>();
        this.fixed=new ArrayList<Tile>();

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
     * Adds a fixed {@link Tile}
     *
     * @param fixed Tile to be added
     */
    //public void addFixed(Tile fixed) {
    //    this.fixed.add(fixed);
    //}

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
     *
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
     *
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
     * @return board width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the board width
     *
     * @param width
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
     *
     * @return
     */
    public Coordinate getPlayerOnePosition() {
        return playerOnePosition;
    }

    /**
     *
     * @param playerOnePosition
     */
    public void setPlayerOnePosition(Coordinate playerOnePosition) {
        this.playerOnePosition = playerOnePosition;
    }

    /**
     *
     * @return
     */
    public Coordinate getPlayerTwoPosition() {
        return playerTwoPosition;
    }

    /**
     *
     * @param playerTwoPosition
     */
    public void setPlayerTwoPosition(Coordinate playerTwoPosition) {
        this.playerTwoPosition = playerTwoPosition;
    }

    /**
     *
     * @return
     */
    public Coordinate getPlayerThreePosition() {
        return playerThreePosition;
    }

    /**
     *
     * @param playerThreePosition
     */
    public void setPlayerThreePosition(Coordinate playerThreePosition) {
        this.playerThreePosition = playerThreePosition;
    }

    /**
     *
     * @return
     */
    public Coordinate getPlayerFourPosition() {
        return playerFourPosition;
    }

    /**
     *
     * @param playerFourPosition
     */
    public void setPlayerFourPosition(Coordinate playerFourPosition) {
        this.playerFourPosition = playerFourPosition;
    }

    /**
     *
     * @return
     */
    public ArrayList<Tile> getMovables() {
        return movables;
    }

    /**
     * 
     * @param movables
     */
    public void setMovables(ArrayList<Tile> movables) {
        this.movables = movables;
    }

    public void setPlayerPosition(Coordinate coordinate, int playerNumber) {
        switch (playerNumber) {
            case 1 : this.playerOnePosition = coordinate;
            break;
            case 2 : this.playerTwoPosition = coordinate;
                break;
            case 3 : this.playerThreePosition = coordinate;
                break;
            case 4 : this.playerFourPosition = coordinate;
                break;
            default : throw new IllegalArgumentException("Player number not in range");
        }
    }

    public Coordinate getPlayerPosition(int playerNumber) {
        switch (playerNumber) {
            case 1 : return this.playerOnePosition;
            case 2 : return this.playerTwoPosition;
            case 3 : return this.playerThreePosition;
            case 4 : return this.playerFourPosition;
            default : throw new IllegalArgumentException("Player number not in range");
        }
    }

    /**
     *
     * @param fileName
     */
    public void readGameboardFile(String fileName) {
        Scanner in;
        PirateStyle style = new PirateStyle();
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
        int fixedTile = Integer.parseInt(in.nextLine());
        readFixed(fixedTile, in);
        System.out.println(fixed);
        int unfixed = Integer.parseInt(in.nextLine());
        if (unfixed > 0) {
            readUnfixed(unfixed, in);
        }
        System.out.println(unfixed);

        setStartCoords(in);
    }

    /**
     * Reads board size
     *
     * @param sLine
     * @return
     */
    private void readSize(String sLine) {
        String[] sizeStrArray = sLine.split(",");
        this.setHeight(Integer.parseInt(sizeStrArray[0]));
        this.setWidth(Integer.parseInt(sizeStrArray[1]));

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


            System.out.println(fTileType);
            System.out.println(Angle.toAngle(turn));
            PirateStyle style = new PirateStyle();


            addToFixed(new Tile(new Coordinate(x,y), fTileType,style ,Angle.UP, true));

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
        int[] angleArray = new int[]{0, 90, 180, 270};

        Angle newAngle;
        for (int i = 0; i < unfixed; i++) {
            String unfTLine = in.nextLine();
            TileType unfTLineType = readTileType(unfTLine);
            tempList.add(unfTLineType);
            Random rand = new Random();
            int randAngle = rand.nextInt(angleArray.length);
            PirateStyle style = new PirateStyle();

            movables.add(new Tile(unfTLineType,style, Angle.UP, false));
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
        for(int i = 1; i <= MAX_PLAYERS; i++) {
            String cLine = in.nextLine();
            String[] coordArray = cLine.split(",");
            Coordinate playCoord = new Coordinate(Integer.parseInt(coordArray[1]), Integer.parseInt(coordArray[2]));
            System.out.println(i);
            setPlayerPosition(playCoord,i);
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
}
