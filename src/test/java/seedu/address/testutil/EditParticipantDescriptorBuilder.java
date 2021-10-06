package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditParticipantDescriptor;
import seedu.address.model.participant.Address;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.Phone;
import seedu.address.model.tag.Tag;

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
        descriptor.setTags(participant.getTags());
        descriptor.setBirthDate(participant.getBirthDate());
        descriptor.setNotes(participant.getNotes());
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

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditParticipantDescriptor}
     * that we are building.
     */
    public EditParticipantDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditParticipantDescriptor build() {
        return descriptor;
    }
}
