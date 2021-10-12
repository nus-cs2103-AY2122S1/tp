package seedu.tracker.model.module.exceptions;

public class AcademicCalendarNotExistException extends RuntimeException {
    public AcademicCalendarNotExistException() {
        super("This module does not contain Academic calendar.");
    }
}
