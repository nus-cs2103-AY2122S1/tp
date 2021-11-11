package seedu.address.model.residency.exceptions;

public class DuplicateRoomRegException extends RuntimeException {
    public static final String MESSAGE = "This room is currently occupied.";
    public DuplicateRoomRegException() {
        super(MESSAGE);
    }
}
