package seedu.address.model.modulelesson.exceptions;

public class DuplicateModuleLessonException extends RuntimeException {
    /**
     * Signals that the operation will result in duplicate module classes (Classes are considered duplicates if they
     * have the same identity).
     */
    public DuplicateModuleLessonException() {
        super("Operation would result in duplicate lessons");
    }
}
