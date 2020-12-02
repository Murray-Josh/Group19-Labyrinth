package constants;

/**
 * Specicifies and finalised specific angles entities can use
 * @author Joseph Omar
 * @version 1.0
 */
public enum Angle {
    DOWN(0), LEFT(90), UP(180), RIGHT(270);

    private final double ANGLE;

    /**
     * Creates Angle
     * @param angle Angle in degrees (0-270)
     */
    Angle(double angle) {
        this.ANGLE = angle;
    }

    /**
     * Gets the angle of the entity
     * @return
     */
    public double get(){
        return this.ANGLE;
    }

    /**
     * Converts the angle to String
     * @return Angle as String
     */
    @Override
    public String toString() {
        return String.valueOf(this.ANGLE);
    }
}
