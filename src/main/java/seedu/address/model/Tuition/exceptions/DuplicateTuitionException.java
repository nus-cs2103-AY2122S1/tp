package seedu.address.model.Tuition.exceptions;

public class DuplicateTuitionException extends RuntimeException{
    public DuplicateTuitionException() {
        super("Operation would result in duplicate persons");
    }
}
