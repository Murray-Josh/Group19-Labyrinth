package holdables;

public class IceEffect extends TileEffect {

    protected IceEffect(boolean isFrozen) {
        super(isFrozen);
    }

    public IceEffect(boolean onFire, boolean isFrozen) {
        super(onFire, isFrozen);
    }

    public void setIsFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
        System.out.println("This tile is frozen");
    }

    public boolean isFrozen() {
        return this.isFrozen;
    }
}
