package seedu.programmer.testutil;

import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ProgrammerError programmerError;

    public AddressBookBuilder() {
        programmerError = new ProgrammerError();
    }

    public AddressBookBuilder(ProgrammerError programmerError) {
        this.programmerError = programmerError;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        programmerError.addPerson(person);
        return this;
    }

    public ProgrammerError build() {
        return programmerError;
    }
}
