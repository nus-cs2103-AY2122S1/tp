package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditParticipantDescriptor;
import seedu.address.model.participant.Address;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.Phone;

public class EditParticipantDescriptorBuilder {

    private EditParticipantDescriptor descriptor;

    public EditParticipantDescriptorBuilder() {
        descriptor = new EditParticipantDescriptor();
    }

    public EditParticipantDescriptorBuilder(EditParticipantDescriptor descriptor) {
        this.descriptor = new EditParticipantDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditParticipantDescriptor} with fields containing {@code participant}'s details
     */
    public EditParticipantDescriptorBuilder(Participant participant) {
        descriptor = new EditParticipantDescriptor();
        descriptor.setName(participant.getName());
        descriptor.setPhone(participant.getPhone());
        descriptor.setEmail(participant.getEmail());
        descriptor.setAddress(participant.getAddress());
        descriptor.setBirthDate(participant.getBirthDate());
        descriptor.setNextOfKins(participant.getNextOfKins());
    }

    /**
     * Sets the {@code Name} of the {@code EditParticipantDescriptor} that we are building.
     */
    public EditParticipantDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditParticipantDescriptor} that we are building.
     */
    public EditParticipantDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditParticipantDescriptor} that we are building.
     */
    public EditParticipantDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditParticipantDescriptor} that we are building.
     */
    public EditParticipantDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    public EditParticipantDescriptor build() {
        return descriptor;
    }
}
