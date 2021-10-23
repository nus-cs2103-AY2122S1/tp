package seedu.address.commons.exceptions;

import java.time.DateTimeException;

public class InvalidTimeException extends DateTimeException {
    public InvalidTimeException() {
        super("message");
    }
}
