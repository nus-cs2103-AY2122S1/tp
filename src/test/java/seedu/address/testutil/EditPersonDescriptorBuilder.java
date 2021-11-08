package seedu.address.testutil;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.done.Done;
import seedu.address.model.interview.Interview;
import seedu.address.model.notes.Notes;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.Experience;
import seedu.address.model.person.LevelOfEducation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setRole(person.getRole());
        descriptor.setEmploymentType(person.getEmploymentType());
        descriptor.setExpectedSalary(person.getExpectedSalary());
        descriptor.setLevelOfEducation(person.getLevelOfEducation());
        descriptor.setExperience(person.getExperience());
        descriptor.setTags(person.getTags());
        descriptor.setInterview(person.getInterview());
        descriptor.setNotes(person.getNotes());
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
     * Sets the {@code Role} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Sets the {@code EmploymentType} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmploymentType(String employmentType) {
        descriptor.setEmploymentType(new EmploymentType(employmentType));
        return this;
    }

    /**
     * Sets the {@code ExpectedSalary} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withExpectedSalary(String expectedSalary) {
        descriptor.setExpectedSalary(new ExpectedSalary(expectedSalary));
        return this;
    }

    /**
     * Sets the {@code Level of Education} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withLevelOfEducation(String levelOfEducation) {
        descriptor.setLevelOfEducation(new LevelOfEducation(levelOfEducation));
        return this;
    }

    /**
     * Sets the {@code Experience} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withExperience(String experience) {
        descriptor.setExperience(new Experience(experience));
        return this;
    }

    /**
     * Sets the {@code Interview} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withInterview(String interview) {
        descriptor.setInterview(Optional.ofNullable(new Interview(interview)));
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
     * Sets the {@code Notes} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNotes(String notes) {
        descriptor.setNotes(Optional.ofNullable(new Notes(notes)));
        return this;
    }

    /**
     * Sets the {@code Done} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDone(String done) {
        descriptor.setDone(new Done(done));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
