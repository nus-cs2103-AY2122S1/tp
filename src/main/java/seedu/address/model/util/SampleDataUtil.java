package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.commons.RepoName;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Participation;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.student.UserName;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Email("alexyeoh@u.nus.edu"), new StudentNumber("A0123436B"),
                    new UserName("ayeoh"), new RepoName("ip"), getTagSet("year2", "cs"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName()),
            new Student(new Name("Bernice Yu"), new Email("berniceyu@u.nus.edu"), new StudentNumber("A0123456A"),
                    new UserName("BYU"), null, getTagSet("year2", "cs"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName()),
            new Student(new Name("Charlotte Oliveiro"), new Email("charlotte@u.nus.edu"),
                    new StudentNumber("A0123450B"), null, null, getTagSet("year2", "engineering"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName()),
            new Student(new Name("David Li"), new Email("lidavid@u.nus.edu"), new StudentNumber("A0123956B"),
                    null, new RepoName("repo"), getTagSet("year3"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName()),
            new Student(new Name("Irfan Ibrahim"), new Email("irfan@u.nus.edu"), new StudentNumber("A0823456B"),
                    new UserName("IrIb"), new RepoName("myRepo"), getTagSet("year3"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName()),
            new Student(new Name("Roy Balakrishnan"), new Email("royb@u.nus.edu"), new StudentNumber("A1123456B"),
                    null, null, getTagSet("year1"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName()),
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
     * Returns an attendance list containing the list of integers given.
     */
    public static Attendance getAttendanceList(Integer... integers) {
        return new Attendance(new ArrayList<>(Arrays.asList(integers)));
    }

    /**
     * Returns a participation list containing the list of integers given.
     */
    public static Participation getParticipationList(Integer... integers) {
        return new Participation(new ArrayList<>(Arrays.asList(integers)));
    }

    /**
     * Returns the default attendance list.
     */
    public static Attendance getDefaultAttendance() {
        return new Attendance();
    }

    /**
     * Returns the default participation list.
     */
    public static Participation getDefaultParticipation() {
        return new Participation();
    }
}
