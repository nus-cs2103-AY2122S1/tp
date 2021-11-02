package seedu.anilist.logic.parser.exceptions;

/**
 * Represents an error when an Integer goes out of a certain range;
 */
public class IntegerOutOfRangeException extends IllegalArgumentException {
    public static final String OUT_OF_RANGE_MESSAGE =
        "The value should only be within %d and %d";

    public IntegerOutOfRangeException(int start, int end) {
        super(String.format(OUT_OF_RANGE_MESSAGE, start, end));
    }

}
