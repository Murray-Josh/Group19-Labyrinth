package core;

import holdables.*;
import players.Player;
import styles.Style;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class represents the Silk Bag: an array list and its methods.
 *
 * @author Jordy Martinson
 * @version 0.9
 */
public class SilkBag implements Serializable {
    private static final double EFFECTS = 0.2; // Additional effects tiles on top of placeable ones
    //    private final double FLOOR_TILES = floorSize(Core.Gameboard.getSize()); // Currently cannot get floor tiles, static issue
    //placeholder below
    private static final int WIDTH = 5;
    private static final int HEIGHT = 6;
    private final double FLOOR_TILES = floorSize(new int[]{WIDTH, HEIGHT});
    
    private ArrayList<Object> bagList = new ArrayList<Object>();
    private final Random random = new Random();

    /**
     * Adds an object to the bag
     *
     * @param obj the object to be added
     */
    private void enqueue(Holdable obj) {
        bagList.add(obj);
    }

    /**
     * Removes the first object from the bag
     */
    public void dequeue() {
        Player.addToHand(bagList.get(0)); // again issues with static
        bagList.remove(0);
    }
    
    /**
     * Determines how big the gameboard is
     * @param size Size of the board
     * @return Area of board
     */
    public double floorSize(int[] size) {
            return (size[0] * size[1]); // will fail unless specifically two ints
    }

    /**
     * Randomly generates a certain number of tiles and effects, then shuffles
     */
    public void generate() {
        Style style = null; // still need to retrieve style somehow

        int[] angleArray = new int[]{0, 90, 180, 270};
        TileType[] typeArray = new TileType[]{new CornerTile(), new StraightTile(), new JunctionTile()};
        Effect[] effectArray = new Effect[]{new FireEffect(), new IceEffect(), new DoubleMovement(), new BackMovement()};

        for (int i = 0; i < boardSize; i++) {
            int randAngle = random.nextInt(angleArray.length);
            int randType = random.nextInt(typeArray.length);
            Tile newTile = new Tile(new Coordinate(0, 0), typeArray[randType], style, angleArray[randAngle], false);
            enqueue(newTile);
        }

        for (int j = 0; j < EFFECTS; j++) {
            int randEffect = random.nextInt(effectArray.length);
            Effect newEffect = effectArray[randEffect];
            enqueue(newEffect);
        }
        Collections.shuffle(bagList);
    }
}
