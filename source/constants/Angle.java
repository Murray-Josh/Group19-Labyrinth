package constants;

import static java.lang.Math.abs;

/**
 * Specifies and finalised specific angles entities can use
 *
 * @author Joseph Omar
 * @version 1.1
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
     * @return Angle corresponding to specified value
     */
    public static Angle toAngle(double value) {
        /* make the inputted value between 0-360 and positive */
        value = abs(value % 360);
        switch ((int) value) {
            case 180:
                return Angle.UP;
            case 90:
                return Angle.LEFT;
            case 270:
                return Angle.RIGHT;
            default:
                return Angle.DOWN;
        }
    }

    /**
     * Gets the angle of the entity
     *
     * @return The double value of the angle
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
