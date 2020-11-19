import java.io.Serializable;

/**
 * Effects.java
 *
 * @author Walid Mohamed
 * @version 1.0.0
 */

public class Effect extends Holdable implements Serializable {
    private EffectType type;
    private boolean onFire;
    private boolean isFrozen;
    private boolean doubleMovement;
    private boolean backMovement;

    public Effect(EffectType type, boolean onFire, boolean isFrozen, boolean doubleMovement, boolean backMovement) {
        setEffectType(type);
        setStyle(style);
        setFixed(fixed);
    }

    public boolean isOnFire() {
        return this.onFire;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
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

    public boolean isBackMovement() {
        return this.backMovement;
    }

    public void setBackMovement(boolean backMovement) {
        this.backMovement = backMovement;
    }

}
// I understand that I'm missing the class hierachies stuff I will get to this immediately
// after this meeting e.g. separating the tile effects from the player effects & implementing how long
// the effects will last for etc ...
}
