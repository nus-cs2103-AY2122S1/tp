package seedu.address.model.staff.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateStaffException extends RuntimeException {
    public DuplicateStaffException() {
        super("Operation would result in duplicate persons");
    }
}
