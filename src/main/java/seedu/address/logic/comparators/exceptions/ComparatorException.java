package seedu.address.logic.comparators.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a comparison error encountered by a comparator.
 */
public class ComparatorException extends IllegalValueException {

    public ComparatorException(String message) {
        super(message);
    }

    public ComparatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
