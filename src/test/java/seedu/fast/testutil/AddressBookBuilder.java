package seedu.fast.testutil;

import seedu.fast.model.Fast;
import seedu.fast.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Fast addressBook;

    public AddressBookBuilder() {
        addressBook = new Fast();
    }

    public AddressBookBuilder(Fast addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public Fast build() {
        return addressBook;
    }
}
