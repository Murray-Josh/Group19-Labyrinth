package holdables;

public class FireEffect extends TileEffect {

    public FireEffect(boolean onFire, boolean isFrozen) {
        super(onFire, isFrozen);
    }

    public boolean isOnFire() {
        return this.onFire;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
        System.out.print("This tile is on fire");
    }

}
