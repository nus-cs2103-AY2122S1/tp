package seedu.address.model.historystates.exceptions;

public class NoHistoryStatesException extends RuntimeException {
    public NoHistoryStatesException(String message) {
        super(message);
    }

    public NoHistoryStatesException() {
        super();
    }
}
