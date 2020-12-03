package constants;

/**
 * Specifies and finalised specific angles entities can use
 *
 * @author Joseph Omar
 * @version 1.0
 */
public enum Angle {
    DOWN(0),
    LEFT(90),
    UP(180),
    RIGHT(270);

    private final double ANGLE;

    /**
     * Creates Angle
     *
     * @param angle Angle in degrees (0-270)
     */
    Angle(double angle) {
        this.ANGLE = angle;
    }

    /**
     * Converts a Double to an Angle type
     *
     * @param value Angle to be converted
     *
     * @return Angle corresponding to specified value
     */
    public static Angle toAngle(double value) {
        switch ((int) value) {
            case 0:
            case 360:
                return Angle.DOWN;
            case 90:
                return Angle.LEFT;
            case 180:
                return Angle.UP;
            case 270:
                return Angle.RIGHT;
            default:
                throw new IllegalArgumentException("Not an Angle Constant");
        }
    }

    /**
     * Gets the angle of the entity
     *
     * @return
     */
    public double get() {
        return this.ANGLE;
    }

    /**
     * Converts the angle to String
     *
     * @return Angle as String
     */
    @Override
    public String toString() {
        return String.valueOf(this.ANGLE);
    }

}
