package seedu.plannermd.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.plannermd.logic.commands.editcommand.EditDoctorCommand;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.BirthDate;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.tag.Tag;

/**
 * A utility class to help with building EditDoctorDescriptor objects.
 */
public class EditDoctorDescriptorBuilder {

    private EditDoctorCommand.EditDoctorDescriptor descriptor;

    public EditDoctorDescriptorBuilder() {
        descriptor = new EditDoctorCommand.EditDoctorDescriptor();
    }

    public EditDoctorDescriptorBuilder(EditDoctorCommand.EditDoctorDescriptor descriptor) {
        this.descriptor = new EditDoctorCommand.EditDoctorDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDoctorDescriptor} with fields containing
     * {@code Doctor}'s details
     */
    public EditDoctorDescriptorBuilder(Doctor doctor) {
        descriptor = new EditDoctorCommand.EditDoctorDescriptor();
        descriptor.setName(doctor.getName());
        descriptor.setPhone(doctor.getPhone());
        descriptor.setEmail(doctor.getEmail());
        descriptor.setAddress(doctor.getAddress());
        descriptor.setBirthDate(doctor.getBirthDate());
        descriptor.setTags(doctor.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditDoctorDescriptor} that we are
     * building.
     */
    public EditDoctorDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditDoctorDescriptor} that we are
     * building.
     */
    public EditDoctorDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditDoctorDescriptor} that we are
     * building.
     */
    public EditDoctorDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditDoctorDescriptor} that we are
     * building.
     */
    public EditDoctorDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code BirthDate} of the {@code EditDoctorDescriptor}
     * that we are building.
     */
    public EditDoctorDescriptorBuilder withBirthDate(String birthDate) {
        descriptor.setBirthDate(new BirthDate(birthDate));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code EditDoctorDescriptor} that we are building.
     */
    public EditDoctorDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditDoctorCommand.EditDoctorDescriptor build() {
        return descriptor;
    }
}
