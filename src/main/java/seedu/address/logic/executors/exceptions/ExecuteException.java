package seedu.address.logic.executors.exceptions;

/**
 * Represents a parse error encountered by a parser.
 */
public class ExecuteException extends Exception {

    public ExecuteException(String message) {
        super(message);
    }

    public ExecuteException(String message, Throwable cause) {
        super(message, cause);
    }
}
