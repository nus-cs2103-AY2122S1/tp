package seedu.address.model.folder.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateFolderException extends RuntimeException {
    public DuplicateFolderException() {
        super("Operation would result in duplicate folders");
    }
}
