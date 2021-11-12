package seedu.address.model.time.exceptions;

public class InvalidHourOfDayException extends Exception {
    private static final String MESSAGE_INVALID_HOUR_OF_DAY = "Hour of day provided is invalid.";

    public InvalidHourOfDayException() {
        super(MESSAGE_INVALID_HOUR_OF_DAY);
    }
}
