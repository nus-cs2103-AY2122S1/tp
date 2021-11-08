package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Module;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("E1234567@u.nus.edu"),
                new Module("CS2100"),
                getAssignmentList(new String[] {"Assignment 1", "15 Sep 2021, 01:00 PM", "COMPLETED"},
                                  new String[] {"Assignment 2", "20 Oct 2021, 01:00 PM", "PENDING"},
                                  new String[] {"Lab 1", "08 Oct 2021, 01:00 PM", "COMPLETED"},
                                  new String[] {"Lab 2", "15 Oct 2021, 01:00 PM", "COMPLETED"},
                                  new String[] {"Lab 3", "22 Oct 2021, 01:00 PM", "PENDING"}),
                getTagSet("T17", "L13")),
            new Person(new Name("Bernice Yu"), new Email("E9876543@u.nus.edu"),
                new Module("CS2100"),
                getAssignmentList(new String[] {"Assignment 1", "15 Sep 2021, 01:00 PM", "COMPLETED"},
                                  new String[] {"Assignment 2", "20 Oct 2021, 01:00 PM", "COMPLETED"}),
                getTagSet("T17")),
            new Person(new Name("Charlotte Oliveiro"), new Email("E2468135@u.nus.edu"),
                new Module("CS1231"),
                getAssignmentList(new String[] {"Assignment 1", "13 Sep 2021, 11:59 PM", "COMPLETED"},
                                  new String[] {"Assignment 2", "19 Oct 2021, 11:59 PM", "PENDING"}),
                getTagSet("T5")),
            new Person(new Name("David Li"), new Email("E9753124u@u.nus.edu"),
                new Module("CS1231"),
                getAssignmentList(new String[] {"Assignment 1", "13 Sep 2021, 11:59 PM", "COMPLETED"},
                                  new String[] {"Assignment 2", "19 Oct 2021, 11:59 PM", "COMPLETED"}),
                getTagSet("T5")),
            new Person(new Name("Irfan Ibrahim"), new Email("E3698521@u.nus.edu"),
                new Module("CS2100"),
                getAssignmentList(new String[] {"Assignment 1", "15 Sep 2021, 01:00 PM", "COMPLETED"},
                                  new String[] {"Assignment 2", "20 Oct 2021, 01:00 PM", "COMPLETED"}),
                getTagSet("T8")),
            new Person(new Name("Roy Balakrishnan"), new Email("E7539518@u.nus.edu"),
                new Module("CS2100"),
                getAssignmentList(new String[] {"Lab 1", "08 Oct 2021, 01:00 PM", "COMPLETED"},
                                  new String[] {"Lab 2", "15 Oct 2021, 01:00 PM", "COMPLETED"},
                                  new String[] {"Lab 3", "22 Oct 2021, 01:00 PM", "PENDING"}),
                getTagSet("L13"))
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
     * Returns an assignment list containing the list of strings given.
     */
    public static List<Assignment> getAssignmentList(String[]... strings) {
        return Arrays.stream(strings)
                .map(Assignment::new)
                .collect(Collectors.toList());
    }

}
