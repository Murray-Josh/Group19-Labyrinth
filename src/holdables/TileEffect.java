package holdables;

/**
 * Defines the types of {@link Effect} that can be applied to a {@link Tile}. These are also {@link Holdable}, like {@link Tile} and {@link PlayerEffect}
 * @author Walid Mohammed
 * @author Joseph Omar
 * @version 3.0
 */
public enum TileEffect implements Effect, Holdable {
    FIRE(3, 2), ICE(3, 1);
    private final int RANGE;
    private  final int TURNS_ACTIVE;
    TileEffect(int range, int turnsActive) {
        this.RANGE = range;
        this.TURNS_ACTIVE = turnsActive;
    }

    public int getRange() {
        return RANGE;
    }

    public int getTurnsActive() {
        return TURNS_ACTIVE;
    }
}