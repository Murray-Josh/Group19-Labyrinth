public class IceEffect extends Effect.TileEffect {

    protected IceEffect(boolean isFrozen) {
        super(isFrozen);
    }
    public void setIsFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
        System.out.println("This tile is frozen");
    }

    public boolean isFrozen() {
        return this.isFrozen;
    }
}
