package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.facility.Facility;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Facility} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withFacility(Facility facility) {
        addressBook.addFacility(facility);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
