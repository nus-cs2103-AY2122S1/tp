package safeforhall.testutil;

import safeforhall.model.AddressBook;
import safeforhall.model.event.Event;
import safeforhall.model.person.Person;

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
     * Adds a new {@code Event} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withEvent(Event event) {
        addressBook.addEvent(event);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
