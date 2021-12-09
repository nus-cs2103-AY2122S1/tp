package seedu.times.model.tuitionclass.exceptions;

/**
 * Signals that the TuitionClass was not found.
 */
public class TuitionClassNotFoundException extends RuntimeException {
    public TuitionClassNotFoundException() {
        super("Tuition Class not found.");
    }
}
