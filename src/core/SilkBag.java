package core;

import holdables.*;
import players.Player;

import java.io.Serializable;
import java.util.*;

/**
 * This class represents the Silk Bag: an array list and its methods.
 *
 * @author Jordy Martinson
 * @version 1.0
 */
public class SilkBag extends ArrayDeque<Holdable> implements Serializable {
    public SilkBag(List<Holdable> items) {
        super();
        Collections.shuffle(items);
        super.addAll(items);
    }

    /**
     * Shuffles the {@link SilkBag}
     */
    public void shuffle() {
        ArrayList<Holdable> temp= new ArrayList<Holdable>();
        while (!isEmpty()) {
            temp.add(poll());
        }
        Collections.shuffle(temp);
        temp.addAll(temp);
    }
}
