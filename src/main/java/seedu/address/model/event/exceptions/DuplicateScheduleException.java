package seedu.address.model.event.exceptions;

public class DuplicateScheduleException extends RuntimeException {
    public DuplicateScheduleException() {
        super("Operation would result in duplicate schedule");
    }
}
