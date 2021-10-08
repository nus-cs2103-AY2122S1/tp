package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Role("Software Engineer"),
                new EmploymentType("Full time"),
                new ExpectedSalary("3500"),
                new LevelOfEducation("PhD"),
                new Experience(0),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Role("Software Tester"),
                new EmploymentType("Part time"),
                new ExpectedSalary("1300"),
                new LevelOfEducation("Masters"),
                new Experience(1),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Role("Software Developer"),
                new EmploymentType("Temporary"),
                new ExpectedSalary("0"),
                new LevelOfEducation("Elementary"),
                new Experience(2),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Role("Software Developer"),
                new EmploymentType("Internship"),
                new ExpectedSalary("5700"),
                new LevelOfEducation("Bachelors"),
                new Experience(3),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Email("irfan@example.com"),
                new Role("Software Tester"),
                new EmploymentType("Full time"),
                new ExpectedSalary("2200"),
                new LevelOfEducation("Middle School"),
                new Experience(4),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"),
                new Role("Software Engineer"),
                new EmploymentType("Part time"),
                new ExpectedSalary("4000"),
                new LevelOfEducation("High School"),
                new Experience(5),
                getTagSet("colleagues"))
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
