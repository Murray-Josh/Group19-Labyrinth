package core;

import holdables.*;
import players.Player;
import styles.Style;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A Core.Gameboard  for players to play on
 *
 * @author Joshua Murray
 * @version 1.0
 */
public class Gameboard implements Serializable {
    private final static int ROTATION_LOCK = 45;
    private final SilkBag silkBag;
    Random rand = new Random();
    GoalTile goal = new GoalTile();
    Tile goalTile;
    private ArrayList<Player> players;
    private int[] size;
    private Tile[][] board;
    private Style style;


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
        //board = new Holdables.Tile[size[0]][size[1}
    }

    /*	private static Queue<ClosedShape> readDataFile(Scanner file) {
		Queue<ClosedShape> shapeQueue = new Queue<ClosedShape>();
		while (file.hasNextLine()) {
			String data = file.nextLine();
			//reads in the individual bits of information from the lines
			Scanner scanner = new Scanner(data);
			String shape = scanner.next();*/

    public Gameboard(String fileName) {
        File file = new File(fileName);
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find " + fileName);
            System.exit(0);
        }
        readGameboardSize(in);
        in.close();
    }


    private void readGameboardSize(Scanner line) {
        line.useDelimiter(",");
        size[0] = line.nextInt();
        size[1] = line.nextInt();


        String nextLine = line.nextLine();
        Scanner Fixed = new Scanner(nextLine);
        readGameboardFixed(Fixed);
    }

    private void readGameboardFixed(Scanner line){
        int fixed = line.nextInt();
        for(int i = 0; i <= fixed; i++){
            String nextLine = new Scanner(newLine);
            fixed = new Scanner(nextLine)
            fixed.de
        }
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
    public void boardToString(){}
}
