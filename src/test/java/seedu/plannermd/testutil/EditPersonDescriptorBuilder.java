package seedu.plannermd.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.plannermd.logic.commands.editcommand.EditPatientCommand;
import seedu.plannermd.logic.commands.editcommand.EditPatientCommand.EditPatientDescriptor;
import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.BirthDate;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.tag.Tag;

/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPatientCommand.EditPatientDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPatientCommand.EditPatientDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPatientCommand.EditPatientDescriptor descriptor) {
        this.descriptor = new EditPatientCommand.EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing
     * {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPatientCommand.EditPatientDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setBirthDate(person.getBirthDate());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPatientDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPatientDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPatientDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPatientDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code BirthDate} of the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withBirthDate(String birthDate) {
        descriptor.setBirthDate(new BirthDate(birthDate));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code EditPersonDescriptor} ======= Parses the {@code tags} into a
     * {@code Set<Tag>} and set it to the {@code EditPatientDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPatientDescriptor build() {
        return descriptor;
    }
}
