package seedu.address.model.util;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.ParentContact;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    /**
     * Returns an array of sample students.
     */
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new ParentContact("87438807"),
                    new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new Grade("S1"),
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"), new ParentContact("99272758"),
                    new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Grade("P4"),
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), new ParentContact("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Grade("S3"),
                getTagSet("neighbours")),
            new Student(new Name("David Li"), new ParentContact("91031282"),
                    new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Grade("P2"),
                getTagSet("family")),
            new Student(new Name("Irfan Ibrahim"), new ParentContact("92492021"),
                    new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), new Grade("S2"),
                getTagSet("classmates")),
            new Student(new Name("Roy Balakrishnan"), new ParentContact("92624417"),
                    new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new Grade("P3"),
                getTagSet("colleagues"))
        };
    }

    /**
     * Returns an array of sample lessons.
     */
    public static Lesson[] getSampleLessons() {
        return new Lesson[] {
            new Lesson("Science", new Grade("S1"), DayOfWeek.FRIDAY, LocalTime.of(13, 30), 10.5),
            new Lesson("Math", new Grade("P4"), DayOfWeek.WEDNESDAY, LocalTime.of(18, 0), 15.9),
            new Lesson("English", new Grade("P2"), DayOfWeek.MONDAY, LocalTime.of(9, 0), 8.5),
            new Lesson("Biology", new Grade("S4"), DayOfWeek.SATURDAY, LocalTime.of(9, 0), 12.9)
        };
    }

    /**
     * Returns a sample addressbook.
     */
    public static ReadOnlyAddressBook getSampleAddressBook() {
        Student[] students = getSamplePersons();
        Lesson[] lessons = getSampleLessons();

        Student alex = students[0];
        Student bernice = students[1];
        Lesson science = lessons[0];
        Lesson math = lessons[1];
        science.addStudent(alex);
        math.addStudent(bernice);

        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : students) {
            sampleAb.addPerson(sampleStudent);
        }
        for (Lesson sampleLesson : lessons) {
            sampleAb.addLesson(sampleLesson);
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
