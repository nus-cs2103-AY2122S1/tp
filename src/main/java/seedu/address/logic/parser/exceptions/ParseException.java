package seedu.address.logic.parser.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {
    /**
     * Parses the exception with string message {@code message}.
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * Parses the exception with string message {@codec message}
     * amd throwable cause {@code cause}.
     *
     * @param message the related error message.
     * @param cause of the main exception.
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
