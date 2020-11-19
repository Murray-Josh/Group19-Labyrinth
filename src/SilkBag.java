import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class represents the Silk Bag: an array list and its methods.
 *
 * @author Jordy Martinson
 * @version 0.7
 */
public class SilkBag implements Serializable {
    private static final double EFFECTS = 10; // Additional effects tiles on top of placeable ones
    private ArrayList<Object> bagList = new ArrayList<Object>();
    private Random random = new Random();

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
        // add to player hand here?
        bagList.remove(0);
    }

    /**
     * Randomly generates a certain number of tiles and effects, then shuffles
     */
    public void generate() {
        double boardSize = 25; //Gameboard size

        //placeholders
        Coordinate coordinate = new Coordinate(0, 0);
        Style style = null;

        int[] angleArray = new int[]{0, 90, 180, 270};
        TileType[] typeArray = new TileType[]{new CornerTile(), new StraightTile(), new JunctionTile()}; // need TileType class
        Effect[] effectArray = new Effect[]{Fire(), Ice(), DoubleMove(), Backtrack()}; // need effect class

        for (int i = 0; i < boardSize; i++) {
            int randAngle = random.nextInt(angleArray.length);
            int randType = random.nextInt(typeArray.length);
            Tile newTile = new Tile(coordinate, typeArray[randType], style, angleArray[randAngle], false);
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
