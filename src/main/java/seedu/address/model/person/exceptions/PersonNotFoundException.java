package seedu.address.model.person.exceptions;

/**
 * Signals that the operation is unable to find the specified staff in the address book.
 */
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super("Target person does not exist in the address book.");
    }
}
