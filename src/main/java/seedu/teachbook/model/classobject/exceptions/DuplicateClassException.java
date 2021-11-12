package seedu.teachbook.model.classobject.exceptions;

/**
 * Signals that the operation will result in duplicate classes
 * (classes are considered duplicates if they have the same {@code ClassName}).
 */
public class DuplicateClassException extends RuntimeException {
    public DuplicateClassException() {
        super("Operation would result in duplicate classes");
    }
}
