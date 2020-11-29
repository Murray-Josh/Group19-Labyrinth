package holdables;

/**
 * Holdables.Effect.java
 *
 * @author Walid Mohamed
 * @version 1.0.0
 */

public class Effect1 {
    protected boolean onFire;
    protected boolean isFrozen;
    protected boolean doubleMovement;
    protected boolean backMovement;

    public Effect1(boolean onFire, boolean isFrozen, boolean doubleMovement, boolean backMovement) {
        this.backMovement = backMovement;
        this.doubleMovement = doubleMovement;
        this.isFrozen = isFrozen;
        this.onFire = onFire;
    }

    public Effect1(boolean doubleMovement, boolean backMovement) {
    }

    public Effect1(boolean isFrozen) {
    }

    public Effect1() {

    }
}
