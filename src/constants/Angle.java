package constants;

/**
 * Specicifies and finalised specific angles entities can use
 * @author Joseph Omar
 * @version 1.0
 */
public enum Angle {
    UP(0), LEFT(270), RIGHT(90), DOWN(180);

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
