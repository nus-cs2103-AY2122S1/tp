package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.common.Name;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person.Builder(new Name("Alex Yeoh"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"))
                    .withAddress(new Address("Blk 30 Geylang Street 29, #06-40"))
                    .withTags(getTagSet("friends"))
                    .build(),
            new Person.Builder(new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"))
                    .withAddress(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"))
                    .withTags(getTagSet("colleagues", "friends"))
                    .build(),
            new Person.Builder(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"))
                    .withAddress(new Address("Blk 11 Ang Mo Kio Street 74, #11-04"))
                    .withTags(getTagSet("neighbours"))
                    .build(),
            new Person.Builder(new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"))
                    .withAddress(new Address("Blk 436 Serangoon Gardens Street 26, #16-43"))
                    .withTags(getTagSet("family"))
                    .build(),
            new Person.Builder(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"))
                    .withAddress(new Address("Blk 47 Tampines Street 20, #17-35"))
                    .withTags(getTagSet("classmates"))
                    .build(),
            new Person.Builder(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"))
                    .withAddress(new Address("Blk 45 Aljunied Street 85, #11-31"))
                    .withTags(getTagSet("colleagues"))
                    .build()
        };
    }

    public static Group[] getSampleGroups() {
        return new Group[] {
            new Group(new Name("CS2103T")),
            new Group(new Name("CS2100"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Group sampleGroup : getSampleGroups()) {
            sampleAb.addGroup(sampleGroup);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
