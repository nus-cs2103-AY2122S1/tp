package seedu.address.model.person.exceptions;

public class CannotAttendException extends RuntimeException {
    public CannotAttendException(String cause) {
        super("Person cannot attend the lesson because: " + cause);
    }
}
