package seedu.notor.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.notor.logic.executors.person.PersonEditExecutor.PersonEditDescriptor;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Name;
import seedu.notor.model.person.Person;
import seedu.notor.model.person.Phone;
import seedu.notor.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class PersonEditDescriptorBuilder {

    private final PersonEditDescriptor descriptor;

    public PersonEditDescriptorBuilder() {
        descriptor = new PersonEditDescriptor();
    }

    public PersonEditDescriptorBuilder(PersonEditDescriptor descriptor) {
        this.descriptor = new PersonEditDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public PersonEditDescriptorBuilder(Person person) {
        descriptor = new PersonEditDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setNote(person.getNote());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public PersonEditDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public PersonEditDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public PersonEditDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public PersonEditDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public PersonEditDescriptor build() {
        return descriptor;
    }
}
