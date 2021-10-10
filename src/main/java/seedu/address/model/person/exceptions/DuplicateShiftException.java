package seedu.address.model.person.exceptions;

/**
 * Represents an error caused by user wants to add a duplicate shift inside a staff's schedule.
 */
public class DuplicateShiftException extends RuntimeException {
    public DuplicateShiftException() {
        super("Operation would result in duplicate shift for a staff.");
    }
}
