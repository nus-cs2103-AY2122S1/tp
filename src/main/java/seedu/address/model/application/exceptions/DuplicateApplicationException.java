package seedu.address.model.application.exceptions;

/**
 * Signals that the operation will result in duplicate Applications
 * (Applications are considered duplicates if they have the same company name and position).
 */
public class DuplicateApplicationException extends RuntimeException {
    public DuplicateApplicationException() {
        super("Operation would result in duplicate applications");
    }
}
