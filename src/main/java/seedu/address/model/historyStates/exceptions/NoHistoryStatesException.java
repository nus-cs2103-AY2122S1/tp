package seedu.address.model.historyStates.exceptions;

public class NoHistoryStatesException extends RuntimeException {
    public NoHistoryStatesException(String message) {
        super(message);
    }

    public NoHistoryStatesException() {
        super();
    }
}
