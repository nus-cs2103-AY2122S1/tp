package seedu.placebook.model.schedule.exceptions;

/**
 * Signals that the operation will result in an invalid {@code TimePeriod}.
 * The endTime of the time period is before the startTime of the time period.
 */
public class EndTimeBeforeStartTimeException extends RuntimeException {
    public EndTimeBeforeStartTimeException(String message) {
        super("Not a valid time period: endDateTime is before startDateTime." + message);
    }
}
