package seedu.address.model.facility.exceptions;

/**
 * Signals that the operation will result in duplicate Facilities (Facilities are considered duplicates if they
 * have the same name and location.
 */
public class DuplicateFacilityException extends RuntimeException {
    public DuplicateFacilityException() {
        super("Operation would result in duplicate facilities");
    }
}
