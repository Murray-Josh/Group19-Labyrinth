import java.io.Serializable;

/**
 * Effect.java
 * @version 1.0.0
 * @author Walid Mohamed
 */

public class Effect {
        protected boolean onFire;
        protected boolean isFrozen;
        protected boolean doubleMovement;
        protected boolean backMovement;

    public Effect(boolean onFire, boolean isFrozen, boolean doubleMovement, boolean backMovement) {
        if (backMovement) this.backMovement = true;
        else {
            this.backMovement = false;
        }
        if (doubleMovement) this.doubleMovement = true;
        else {
            this.doubleMovement = false;
        }
        if (isFrozen) this.isFrozen = true;
        else {
            this.isFrozen = false;
        }
        if (onFire) this.onFire = true;
        else {
            this.onFire = false;
        }
    }

    public Effect(boolean doubleMovement, boolean backMovement) {
    }

    static class PlayerEffect extends Effect{


        public PlayerEffect( boolean doubleMovement, boolean backMovement) {
            super(doubleMovement, backMovement);
        }
    }

    static class TileEffect extends Effect{


        public TileEffect(boolean onFire, boolean isFrozen) {
            super(onFire, isFrozen);
        }
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
        System.out.print("This tile is on fire");
    }

    public boolean isOnFire() {
        return this.onFire;
    }

    public void setIsFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public boolean isFrozen() {
        return this.isFrozen;
    }

    public void setDoubleMovement(boolean doubleMovement) {
        this.doubleMovement = doubleMovement;
    }

    public boolean DoubleMovement() {
        return this.doubleMovement;
    }

    public void setBackMovement(boolean backMovement) {
        this.backMovement = backMovement;
    }

    public boolean isBackMovement() {
        return this.backMovement;
    }
}
// I understand that I'm missing the class hierachies stuff I will get to this immediately
// after this meeting e.g. separating the tile effects from the player effects & implementing how long
// the effects will last for etc ...
}
