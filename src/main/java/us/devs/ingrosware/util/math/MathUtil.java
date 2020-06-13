package us.devs.ingrosware.util.math;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
public class MathUtil {

    public static <T extends Number> T clamp(T value, T minimum, T maximum) {
        return value.floatValue() <= minimum.floatValue() ? minimum : (value.floatValue() >= maximum.floatValue() ? maximum : value);
    }

}
