package seedu.address.model.history_states.exceptions;

public class NoHistoryStatesException extends RuntimeException {
    public NoHistoryStatesException(String message) {
        super(message);
    }

    public NoHistoryStatesException() {
        super();
    }
}
