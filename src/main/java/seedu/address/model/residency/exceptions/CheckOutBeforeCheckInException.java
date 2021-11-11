package seedu.address.model.residency.exceptions;

public class CheckOutBeforeCheckInException extends RuntimeException {
    public CheckOutBeforeCheckInException() {
        super("The check-out time of this residency would be before the check-in time!");
    }
}
