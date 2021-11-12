package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePersonException extends RuntimeException {
    /**
     * This class is created if there is a duplicated person.
     */
    public DuplicatePersonException() {
        super("Operation would result in duplicate persons");
    }
}
