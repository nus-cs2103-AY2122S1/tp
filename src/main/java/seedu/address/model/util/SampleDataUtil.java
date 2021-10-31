package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.commons.RepoName;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.LinkYear;
import seedu.address.model.group.Members;
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
        return new Student[]{
            new Student(new Name("Alex Yeoh"), new Email("alexyeoh@u.nus.edu"), new StudentNumber("A0123436B"),
                    new UserName("ayeoh"), new RepoName("ip"), getTagSet("year2", "cs"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("W14-4")),
            new Student(new Name("Bernice Yu"), new Email("berniceyu@u.nus.edu"), new StudentNumber("A0123456A"),
                    new UserName("BYU"), null, getTagSet("year2", "cs"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("W14-4")),
            new Student(new Name("Charlotte Oliveiro"), new Email("charlotte@u.nus.edu"),
                    new StudentNumber("A0123450B"), null, null, getTagSet("year2", "engineering"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("W10-2")),
            new Student(new Name("David Li"), new Email("lidavid@u.nus.edu"), new StudentNumber("A0123956B"),
                    null, new RepoName("repo"), getTagSet("year3"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("W10-2")),
            new Student(new Name("Irfan Ibrahim"), new Email("irfan@u.nus.edu"), new StudentNumber("A0823456B"),
                    new UserName("IrIb"), new RepoName("myRepo"), getTagSet("year3"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("W07-3")),
            new Student(new Name("Roy Balakrishnan"), new Email("royb@u.nus.edu"), new StudentNumber("A1123456B"),
                    null, null, getTagSet("year1"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("W07-3")),
        };
    }

    public static Group[] getSampleGroups() {
        return new Group[] {
            new Group(new GroupName("W14-4"), getMemberList(0, 1), new LinkYear("AY2122"),
                    new RepoName("tp"), getTagSet("monday")),
            new Group(new GroupName("W10-2"), getMemberList(2, 3), new LinkYear("AY2122"),
                    new RepoName("tp"), getTagSet("tuesday")),
            new Group(new GroupName("W07-3"), getMemberList(4, 5), new LinkYear("AY2122"),
                    new RepoName("tp"), getTagSet("wednesday")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        for (Group sampleGroup : getSampleGroups()) {
            sampleAb.addGroup(sampleGroup);
        }
        //for (Task sampleTask : getSampleTasks()) {
        //    sampleAb.addTask(sampleTask);
        //}
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
     * Returns a member set containing the list of students given.
     */
    public static Members getMemberList(Integer... indexes) {
        List<Student> students = List.of(getSampleStudents());
        List<Integer> indexList = List.of(indexes);
        List<Student> members = IntStream.range(0, students.size())
                        .filter(i -> indexList.contains(i))
                        .mapToObj(students::get)
                        .collect(Collectors.toList());
        return new Members(new ArrayList<>(members));
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
