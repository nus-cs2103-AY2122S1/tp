package seedu.fast.logic.parser.exceptions;

import seedu.fast.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class HelpParseException extends IllegalValueException {

    public HelpParseException(String message) {
        super(message);
    }
}
