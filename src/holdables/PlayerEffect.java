package holdables;

/**
 * Defines a type for the two specified player effects, that can be used to identify what effect to apply.
 *
 * @author Joseph Omar
 * @version 2.0
 */
public enum PlayerEffect implements Effect, Holdable {
    DOUBLE_MOVE,
    BACKTRACK,
    NONE
}
