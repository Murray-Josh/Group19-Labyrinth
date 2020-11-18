public class Effect {
  
  /**
 * Effects.java
 * @version 1.0.0
 * @author Walid Mohamed
 */

public class Effects extends Holdable {
        private EffectType type;
        private boolean onFire;
        private boolean isFrozen;
        private boolean doubleMovement;
        private boolean backMovement;

    public Effects(EffectType type, boolean onFire, boolean isFrozen, boolean doubleMovement, boolean backMovement) {
        setEffectType(type);
        setStyle(style);
        setFixed(fixed);
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
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
