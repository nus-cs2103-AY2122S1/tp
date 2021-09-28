package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.participant.Participant;

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
     * Adds a new {@code Participant} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withParticipant(Participant participant) {
        addressBook.addParticipant(participant);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
