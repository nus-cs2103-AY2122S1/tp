package seedu.address.model.tag.exceptions;

/**
 * Signals that the operation will result in duplicate Tag.
 * Tags are considered duplicates if they have the same tag name.
 */
public class DuplicateTagException extends RuntimeException {
    public DuplicateTagException() {
        super("Operation would result in duplicate tags.");
    }
}
