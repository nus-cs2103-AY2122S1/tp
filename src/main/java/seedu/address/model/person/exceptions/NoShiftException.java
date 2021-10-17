package seedu.address.model.person.exceptions;

/**
 * Represents an error caused when a user wants to delete a shift that does not exist for the staff.
 */
public class NoShiftException extends RuntimeException {
    public NoShiftException() {
        super("Shift does not exist for the staff.");
    }
}
