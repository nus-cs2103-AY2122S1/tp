package seedu.programmer.logic.parser.exceptions;

/**
 * Represents a parse error encountered by a parser when inputted with invalid prefix/flag argument.
 */
public class InvalidArgFlagsException extends ParseException {

    public InvalidArgFlagsException(String message) {
        super(message);
    }

}
