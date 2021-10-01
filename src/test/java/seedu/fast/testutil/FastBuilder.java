package seedu.fast.testutil;

import seedu.fast.model.Fast;
import seedu.fast.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class FastBuilder {

    private Fast addressBook;

    public FastBuilder() {
        addressBook = new Fast();
    }

    public FastBuilder(Fast addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public FastBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public Fast build() {
        return addressBook;
    }
}
