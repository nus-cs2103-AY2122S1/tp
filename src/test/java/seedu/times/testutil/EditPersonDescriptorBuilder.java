package seedu.times.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.times.logic.commands.EditCommand;
import seedu.times.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.times.model.person.Address;
import seedu.times.model.person.Email;
import seedu.times.model.person.Name;
import seedu.times.model.person.Phone;
import seedu.times.model.person.Student;
import seedu.times.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditPersonDescriptorBuilder(EditCommand.EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Student student) {
        descriptor = new EditCommand.EditStudentDescriptor();

        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setAddress(student.getAddress());
        descriptor.setTags(student.getTags());

        descriptor.setNokName(student.getNok().getName());
        descriptor.setNokPhone(student.getNok().getPhone());
        descriptor.setNokEmail(student.getNok().getEmail());
        descriptor.setNokAddress(student.getNok().getAddress());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code NokName} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNokName(String name) {
        descriptor.setNokName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code NokPhone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNokPhone(String phone) {
        descriptor.setNokPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code NokEmail} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNokEmail(String email) {
        descriptor.setNokEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code NokAddress} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNokAddress(String address) {
        descriptor.setNokAddress(new Address(address));
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
