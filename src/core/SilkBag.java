package core;

import holdables.Holdable;
import players.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the Silk Bag: an array list and its methods.
 *
 * @author Jordy Martinson
 * @version 1.0
 */
public class SilkBag implements Serializable {
    private final ArrayList<Holdable> bagList = new ArrayList<Holdable>();

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
    public void dequeue(Player p) {
        Player.addToHand(bagList.get(0));
        bagList.remove(0);
    }
}
