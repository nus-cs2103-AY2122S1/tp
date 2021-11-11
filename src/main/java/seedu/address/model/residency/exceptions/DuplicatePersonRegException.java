package seedu.address.model.residency.exceptions;

import seedu.address.model.person.Person;

public class DuplicatePersonRegException extends RuntimeException {
    public DuplicatePersonRegException(Person person) {
        super(person.getName() + " is already checked into another room.");
    }
}
