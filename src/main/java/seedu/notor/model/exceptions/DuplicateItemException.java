package seedu.notor.model.exceptions;

/**
 * Signals that the operation will result in duplicate Persons/Groups (Persons/Groups are considered duplicates if they
 * have the same identity).
 */
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException() {
        super("Operation would result in duplicated persons/groups.");
    }
}
