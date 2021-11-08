package seedu.edrecord.model.module.exceptions;

/**
 * Signals that the operation will result in duplicate Modules (Modules are considered duplicates if they have the same
 * module code).
 */
public class DuplicateModuleException extends RuntimeException {
    public DuplicateModuleException() {
        super("Operation would result in duplicate modules");
    }
}
