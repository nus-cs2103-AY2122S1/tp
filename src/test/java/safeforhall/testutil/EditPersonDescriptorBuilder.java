package safeforhall.testutil;

//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;

import safeforhall.logic.commands.EditCommand.EditPersonDescriptor;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;
//import safeforhall.model.tag.Tag;

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
        descriptor.setRoom(person.getRoom());
        descriptor.setFaculty(person.getFaculty());
        descriptor.setVaccStatus(person.getVaccStatus());
        //TODO: lastfetdate and lastcollectiondate


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
     * Sets the {@code Room} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRoom(String room) {
        descriptor.setRoom(new Room(room));
        return this;
    }

    /**
     * Sets the {@code Faculty} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withFaculty(String faculty) {
        descriptor.setFaculty(new Faculty(faculty));
        return this;
    }

    /**
     * Sets the {@code VaccStatus} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withVaccStatus(String vaccStatus) {
        descriptor.setVaccStatus(new VaccStatus(vaccStatus));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    //public EditPersonDescriptorBuilder withTags(String... tags) {
    //    Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
    //    descriptor.setTags(tagSet);
    //    return this;
    //}

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
