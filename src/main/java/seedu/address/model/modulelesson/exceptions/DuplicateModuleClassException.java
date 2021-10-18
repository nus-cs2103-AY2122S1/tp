package seedu.address.model.modulelesson.exceptions;

public class DuplicateModuleClassException extends RuntimeException {
    /**
     * Signals that the operation will result in duplicate module classes (Classes are considered duplicates if they
     * have the same identity).
     */
    public DuplicateModuleClassException() {
        super("Operation would result in duplicate module class");
    }
}
