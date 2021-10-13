package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), new StudentNumber("A0123436B"),
                    getTagSet("friends"), getAttendanceList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
            new Student(new Name("Bernice Yu"), new Email("berniceyu@example.com"), new StudentNumber("A0123456A"),
                    getTagSet("colleagues", "friends"),
                    getAttendanceList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
            new Student(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"),
                    new StudentNumber("A0123450B"), getTagSet("neighbours"),
                    getAttendanceList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
            new Student(new Name("David Li"), new Email("lidavid@example.com"), new StudentNumber("A0123956B"),
                    getTagSet("family"), getAttendanceList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
            new Student(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), new StudentNumber("A0823456B"),
                    getTagSet("classmates"), getAttendanceList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
            new Student(new Name("Roy Balakrishnan"), new Email("royb@example.com"), new StudentNumber("A1123456B"),
                    getTagSet("colleagues"), getAttendanceList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
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


    /**
     * Returns a attendance list containing the list of integers given.
     */
    public static Attendance getAttendanceList(Integer... integers) {
        return new Attendance(new ArrayList<>(Arrays.asList(integers)));
    }

}
