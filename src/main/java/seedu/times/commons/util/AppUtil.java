package seedu.times.commons.util;

import static java.util.Objects.requireNonNull;

import javafx.scene.image.Image;
import seedu.times.MainApp;

/**
 * A container for App specific utility functions
 */
public class AppUtil {

    /**
     * Gets an {@code Image} from the specified path.
     */
    public static Image getImage(String imagePath) {
        requireNonNull(imagePath);
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    /**
     * Checks that {@code condition} is true. Used for validating arguments to methods.
     *
     * @throws IllegalArgumentException if {@code condition} is false.
     */
    public static void checkArgument(Boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks that {@code condition} is true. Used for validating arguments to methods.
     *
     * @throws IllegalArgumentException with {@code errorMessage} if {@code condition} is false.
     */
    public static void checkArgument(Boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Checks that {@code test} doesn't exceed {@code maxLength}. Part of argument validation.
     */
    public static boolean isWithinLength(String test, int maxLength) {
        if (test.length() <= maxLength) {
            return true;
        }
        return false;
    }
}
