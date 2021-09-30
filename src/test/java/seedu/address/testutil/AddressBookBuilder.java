package seedu.address.testutil;

import seedu.address.model.ModuleTracker;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ModuleTracker ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ModuleTracker moduleTracker;

    public AddressBookBuilder() {
        moduleTracker = new ModuleTracker();
    }

    public AddressBookBuilder(ModuleTracker addressBook) {
        this.moduleTracker = addressBook;
    }

    /**
     * Adds a new {@code Module} to the {@code ModuleTracker} that we are building.
     */
    public AddressBookBuilder withModule(Module module) {
        moduleTracker.addModule(module);
        return this;
    }

    public ModuleTracker build() {
        return moduleTracker;
    }
}
