package seedu.address.model.module.event.exceptions;

/**
 * Signals that the operation is unable to find the specified event.
 */
public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("Operation unable to find stated event");
    }
}
