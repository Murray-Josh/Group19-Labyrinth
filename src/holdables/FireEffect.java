package holdables;

public class FireEffect extends Effect.TileEffect {

    protected FireEffect(boolean onFire) {
        super(onFire);
    }
    public boolean isOnFire() {
        return this.onFire;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
        System.out.print("This tile is on fire");
    }

}
