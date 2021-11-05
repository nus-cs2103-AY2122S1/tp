package seedu.address.model.friend.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Signals that an invalid day or time was being parsed
 */
public class InvalidDayTimeException extends IllegalValueException {
    public InvalidDayTimeException(String message) {
        super(message);
    }
}
