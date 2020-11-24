package holdables;

import players.Player;

public abstract class PlayerEffect extends Effect {
    public abstract void apply(Player player);
    /*
    If there is ANY common code between backmovement and doublemovement, put the method in THIS CLASS!!
     */
}
