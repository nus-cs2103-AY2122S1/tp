package seedu.address.commons.exceptions;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class IllegalValueException extends Exception {
    /**
     * Constructor for a IllegalValueException for a given message.
     *
     * @param message Message should contain relevant information on the failed constraint(s).
     */
    public IllegalValueException(String message) {
        super(message);
    }

    /**
     * Constructor for a IllegalValueException for a given message and cause.
     *
     * @param message Message should contain relevant information on the failed constraint(s).
     * @param cause Cause of the main exception.
     */
    public IllegalValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
