package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitHubId;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetworkId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TutorialId;
import seedu.address.model.person.Type;
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
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
        descriptor.setGitHubId(person.getGitHubId());
        descriptor.setNusNetworkId(person.getNusNetworkId());
        descriptor.setType(person.getType());
        descriptor.setStudentId(person.getStudentId());
        descriptor.setTutorialId(person.getTutorialId());
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
     * Sets the {@code GitHubID} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGitHubId(String gitHubId) {
        descriptor.setGitHubId(new GitHubId(gitHubId));
        return this;
    }

    /**
     * Sets the {@code NusNetworkId} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNusNetworkId(String nusNetworkId) {
        descriptor.setNusNetworkId(new NusNetworkId(nusNetworkId));
        descriptor.setEmail(new Email(nusNetworkId + "@u.nus.edu"));
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withType(String type) {
        descriptor.setType(new Type(type));
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withStudentId(String studentId) {
        descriptor.setStudentId(new StudentId(studentId));
        return this;
    }

    /**
     * Sets the {@code TutorialId} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTutorialId(String tutorialId) {
        descriptor.setTutorialId(new TutorialId(tutorialId));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
