package seedu.plannermd.testutil.patient;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.plannermd.logic.commands.editcommand.EditPatientCommand;
import seedu.plannermd.logic.commands.editcommand.EditPatientCommand.EditPatientDescriptor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.patient.Risk;
import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.BirthDate;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.tag.Tag;

/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditPatientDescriptorBuilder {

    private EditPatientCommand.EditPatientDescriptor descriptor;

    public EditPatientDescriptorBuilder() {
        descriptor = new EditPatientCommand.EditPatientDescriptor();
    }

    public EditPatientDescriptorBuilder(EditPatientCommand.EditPatientDescriptor descriptor) {
        this.descriptor = new EditPatientCommand.EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing
     * {@code Patient}'s details
     */
    public EditPatientDescriptorBuilder(Patient patient) {
        descriptor = new EditPatientCommand.EditPatientDescriptor();
        descriptor.setName(patient.getName());
        descriptor.setPhone(patient.getPhone());
        descriptor.setEmail(patient.getEmail());
        descriptor.setAddress(patient.getAddress());
        descriptor.setBirthDate(patient.getBirthDate());
        descriptor.setTags(patient.getTags());
        descriptor.setRisk(patient.getRisk());
    }

    /**
     * Sets the {@code Name} of the {@code EditPatientDescriptor} that we are
     * building.
     */
    public EditPatientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPatientDescriptor} that we are
     * building.
     */
    public EditPatientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPatientDescriptor} that we are
     * building.
     */
    public EditPatientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPatientDescriptor} that we are
     * building.
     */
    public EditPatientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code BirthDate} of the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withBirthDate(String birthDate) {
        descriptor.setBirthDate(new BirthDate(birthDate));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code EditPersonDescriptor} ======= Parses the {@code tags} into a
     * {@code Set<Tag>} and set it to the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Risk} of the {@code EditPatientDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withRisk(String risk) {
        descriptor.setRisk(Risk.getUnclassifiableRisk(risk));
        return this;
    }

    public EditPatientDescriptor build() {
        return descriptor;
    }
}
