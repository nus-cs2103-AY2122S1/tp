package seedu.address.model.residency.exceptions;

public class AlreadyCheckedOutException extends RuntimeException {
    public AlreadyCheckedOutException() {
        super("This Residency has already been checked out.");
    }
}
