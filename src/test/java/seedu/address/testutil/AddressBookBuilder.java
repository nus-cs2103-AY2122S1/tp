package seedu.address.testutil;

import seedu.address.model.ModuleTracker;
import seedu.address.model.person.Module;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ModuleTracker ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ModuleTracker addressBook;

    public AddressBookBuilder() {
        addressBook = new ModuleTracker();
    }

    public AddressBookBuilder(ModuleTracker addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Module} to the {@code ModuleTracker} that we are building.
     */
    public AddressBookBuilder withPerson(Module person) {
        addressBook.addModule(person);
        return this;
    }

    public ModuleTracker build() {
        return addressBook;
    }
}
