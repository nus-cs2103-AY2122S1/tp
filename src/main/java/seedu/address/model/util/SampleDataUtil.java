package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.ID;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new ID("E0534794"),
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"), new ID("E0481923"),
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), new ID("E0348902"),
                getTagSet("neighbours")),
            new Student(new Name("David Li"), new ID("E0345242"),
                getTagSet("family")),
            new Student(new Name("Irfan Ibrahim"), new ID("E0593933"),
                getTagSet("classmates")),
            new Student(new Name("Roy Balakrishnan"), new ID("E0539221"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addStudent(sampleStudent);
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
