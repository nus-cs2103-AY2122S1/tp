package seedu.address.testutil;

import seedu.address.model.Module;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Module ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Module module;

    public AddressBookBuilder() {
        module = new Module();
    }

    public AddressBookBuilder(Module module) {
        this.module = module;
    }

    /**
     * Adds a new {@code Person} to the {@code Module} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        module.addPerson(person);
        return this;
    }

    public Module build() {
        return module;
    }
}
