package seedu.address.model.task.exceptions;

/**
 * Signals that the operation will result in duplicate Tasks.
 * Tasks are considered duplicates if they share the same module and the same name.
 */
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException() {
        super("Operation would result in duplicate tasks");
    }
}
