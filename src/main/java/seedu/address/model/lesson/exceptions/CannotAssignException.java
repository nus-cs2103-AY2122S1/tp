package seedu.address.model.lesson.exceptions;

public class CannotAssignException extends RuntimeException {
    public static final String MESSAGE = "Cannot assign the lesson because: ";

    public CannotAssignException(String cause) {
        super(MESSAGE + cause);
    }
}
