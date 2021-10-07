package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.ClassId;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new StudentId("A0212425H"), new ClassId("B01"), new Grade("A")),
            new Person(new Name("Bernice Yu"), new StudentId("A0212425H"), new ClassId("B02"), new Grade("A")),
            new Person(new Name("Charlotte Oliveiro"), new StudentId("A0212425H"), new ClassId("B03"), new Grade("A")),
            new Person(new Name("David Li"), new StudentId("A0212425H"), new ClassId("B04"), new Grade("A")),
            new Person(new Name("Irfan Ibrahim"), new StudentId("A0212425H"), new ClassId("B05"), new Grade("A")),
            new Person(new Name("Roy Balakrishnan"), new StudentId("A0212425H"), new ClassId("B06"), new Grade("A"))
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

}
