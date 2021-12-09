package seedu.times.model.tuitionclass.exceptions;

/**
 * Signals that the operation will result in a invalid class (Classes are considered invalid if they have overlapping
 * timings with existing classes in the list).
 */
public class OverlappingClassException extends RuntimeException {
    public static final String OVERLAP_ERROR_MESSAGE = "Operation would result in overlapping class timings";
    public OverlappingClassException() {
        super(OVERLAP_ERROR_MESSAGE);
    }
}
