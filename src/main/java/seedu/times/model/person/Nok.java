package seedu.times.model.person;

/**
 * Represents a students Next of Kin (ie parent etc.).
 */
public class Nok extends Person {

    /**
     * Every field must be present and not null.
     */
    public Nok(Name name, Phone phone, Email email, Address address) {
        super(name, phone, email, address);
    }
}
