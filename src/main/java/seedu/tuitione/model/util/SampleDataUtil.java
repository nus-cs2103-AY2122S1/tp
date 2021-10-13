package seedu.tuitione.model.util;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.tuitione.model.ReadOnlyTuitione;
import seedu.tuitione.model.Tuitione;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonTime;
import seedu.tuitione.model.lesson.Price;
import seedu.tuitione.model.lesson.Subject;
import seedu.tuitione.model.student.Address;
import seedu.tuitione.model.student.Email;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.ParentContact;
import seedu.tuitione.model.student.Student;
import seedu.tuitione.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Tuitione} with sample data.
 */
public class SampleDataUtil {

    /**
     * Returns an array of sample students.
     */
    public static Student[] getSampleStudents() {
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
            new Lesson(new Subject("Science"), new Grade("S1"),
                    new LessonTime(DayOfWeek.FRIDAY, LocalTime.of(13, 30)), new Price(10.5)),
            new Lesson(new Subject("Math"), new Grade("P4"),
                    new LessonTime(DayOfWeek.WEDNESDAY, LocalTime.of(18, 0)), new Price(15.9)),
            new Lesson(new Subject("English"), new Grade("P2"),
                    new LessonTime(DayOfWeek.MONDAY, LocalTime.of(9, 0)), new Price(8.5)),
            new Lesson(new Subject("Biology"), new Grade("S4"),
                    new LessonTime(DayOfWeek.SATURDAY, LocalTime.of(9, 0)), new Price(12.9))
        };
    }

    /**
     * Returns a sample addressbook.
     */
    public static ReadOnlyTuitione getSampleTuitione() {
        Student[] students = getSampleStudents();
        Lesson[] lessons = getSampleLessons();

        Student alex = students[0];
        Student bernice = students[1];
        Lesson science = lessons[0];
        Lesson math = lessons[1];
        science.addStudent(alex);
        math.addStudent(bernice);

        Tuitione sampleAb = new Tuitione();
        for (Student sampleStudent : students) {
            sampleAb.addStudent(sampleStudent);
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
