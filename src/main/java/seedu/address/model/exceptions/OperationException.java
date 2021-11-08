package seedu.address.model.exceptions;

/**
 * Represents an error encountered by a operation
 */
public class OperationException extends Exception {

    public OperationException(String message) {
        super(message);
    }
}
