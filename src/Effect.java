/**
 * Effect.java
 *
 * @author Walid Mohamed
 * @version 1.0.0
 */

public class Effect {
    protected boolean onFire;
    protected boolean isFrozen;
    protected boolean doubleMovement;
    protected boolean backMovement;

    public Effect(boolean onFire, boolean isFrozen, boolean doubleMovement, boolean backMovement) {
        this.backMovement = backMovement;
        this.doubleMovement = doubleMovement;
        this.isFrozen = isFrozen;
        this.onFire = onFire;
    }

    public Effect(boolean doubleMovement, boolean backMovement) {
    }

    public Effect(boolean isFrozen) {
    }
    
    public Effect() {
        
    }

    static class PlayerEffect extends Effect {

        public PlayerEffect(boolean doubleMovement, boolean backMovement) {
            super(doubleMovement, backMovement);
        }

        public PlayerEffect(boolean doubleMovement) {
        }
    }

    static class TileEffect extends Effect {

        public TileEffect(boolean onFire, boolean isFrozen) {
            super(onFire, isFrozen);
        }

        public TileEffect(boolean onFire) {
        }
    }
}
