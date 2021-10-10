package seedu.address.testutil;

import seedu.address.logic.commands.EditClientCommand.EditClientDescriptor;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;

/**
 * A utility class to help with building EditClientDescriptor objects.
 */
public class EditClientDescriptorBuilder {
    private EditClientDescriptor descriptor;

    public EditClientDescriptorBuilder() {
        descriptor = new EditClientDescriptor();
    }

    public EditClientDescriptorBuilder(EditClientDescriptor descriptor) {
        this.descriptor = new EditClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditClientDescriptor} with fields containing {@code client}'s details
     */
    public EditClientDescriptorBuilder(Client client) {
        descriptor = new EditClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhoneNumber(client.getPhoneNumber());
        descriptor.setEmail(client.getEmail());
        descriptor.setAddress(client.getAddress());
    }

    /**
     * Sets the {@code Name} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code PhoneNumber} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withPhoneNumber(String phone) {
        descriptor.setPhoneNumber(new PhoneNumber(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    public EditClientDescriptor build() {
        return descriptor;
    }
}
