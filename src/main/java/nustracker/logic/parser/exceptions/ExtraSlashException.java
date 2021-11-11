package nustracker.logic.parser.exceptions;

import nustracker.commons.exceptions.IllegalValueException;

/**
 * Represents an error where the values contains extraneous backslashes.
 */
public class ExtraSlashException extends IllegalValueException {

    /**
     * Creates an ExtraSlashException with a default message.
     */
    public ExtraSlashException() {
        super("Invalid command format! Commands that have arguments cannot have extraneous"
                + " backslashes or prefixes. ");
    }

    public ExtraSlashException(String message) {
        super(message);
    }

    public ExtraSlashException(String message, Throwable cause) {
        super(message, cause);
    }

}
