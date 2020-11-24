package holdables;

public abstract class TileEffect extends Effect {
    protected final static int RANGE = 3;

    public abstract void apply(Tile tile);

        /*
    If there is ANY common code between FireEffect and IceEffect, put the method in THIS CLASS!!
     */
}
