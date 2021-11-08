package seedu.address.commons.exceptions;

public class InvalidShiftTimeException extends Exception {
    public InvalidShiftTimeException() {
        super("The input start time and end time is invalid for the target shift");
    }
}
