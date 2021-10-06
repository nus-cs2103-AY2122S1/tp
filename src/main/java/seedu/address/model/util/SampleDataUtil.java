package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), getModuleCodeSet("CS1231"),
                    new Phone("87438807"), new TeleHandle("@alexyeoh"), getTagSet("local")),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@example.com"), getModuleCodeSet("CS2030S"),
                    new Phone("99272758"), new TeleHandle("@berniceyu"), getTagSet("local")),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"), getModuleCodeSet("CS1231"),
                    new Phone("93210283"), new TeleHandle("@charlotteO"), getTagSet("International")),
            new Person(new Name("David Li"), new Email("lidavid@example.com"), getModuleCodeSet("CS2030S", "CS2040"),
                    new Phone("91031282"), new TeleHandle("@davidli"), getTagSet("local")),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), getModuleCodeSet("CS2040"),
                    new Phone("92492021"), new TeleHandle("@irfan"), getTagSet("local")),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@example.com"), getModuleCodeSet("CS2103T"),
                    new Phone("92624417"), new TeleHandle("@royBala"), getTagSet("local"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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

    /**
     * Returns a module code set containing the list of strings given.
     */
    public static Set<ModuleCode> getModuleCodeSet(String... strings) {
        return Arrays.stream(strings)
                .map(ModuleCode::new)
                .collect(Collectors.toSet());
    }

}
