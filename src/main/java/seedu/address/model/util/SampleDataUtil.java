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
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.Description;
import seedu.address.model.task.EventTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TodoTask;

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
                    new UserName("byunice"), new RepoName("ip"), getTagSet("year2", "cs"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("W14-4")),
            new Student(new Name("Charlotte Oliveiro"), new Email("charlotte@u.nus.edu"),
                    new StudentNumber("A0123450B"),
                    new UserName("charoliveiro"), new RepoName("ip"), getTagSet("year2", "engineering"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("W14-4")),
            new Student(new Name("David Li"), new Email("lidavid@u.nus.edu"), new StudentNumber("A0123956B"),
                    new UserName("davidli"), new RepoName("ip"), getTagSet("year3"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("W14-4")),
            new Student(new Name("Eunice Sim"), new Email("eunsim@u.nus.edu"), new StudentNumber("A3332345K"),
                    new UserName("eunsim"), new RepoName("ip"), getTagSet("year1"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("W14-4")),
            new Student(new Name("Irfan Ibrahim"), new Email("irfan@u.nus.edu"), new StudentNumber("A0221393F"),
                    new UserName("IrIb"), new RepoName("ip"), getTagSet("year3"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("F07-3")),
            new Student(new Name("Justin Keong"), new Email("juskeong@u.nus.edu"), new StudentNumber("A0223948G"),
                    new UserName("juskeong"), new RepoName("ip"), getTagSet("year1"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("F07-3")),
            new Student(new Name("Kenneth Wong"), new Email("kenwong@u.nus.edu"), new StudentNumber("A0246794L"),
                    new UserName("kenwong"), new RepoName("ip"), getTagSet("year3"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("F07-3")),
            new Student(new Name("Roy Balakrishnan"), new Email("royb@u.nus.edu"), new StudentNumber("A0363348B"),
                    new UserName("roybalak"), new RepoName("ip"), getTagSet("year1"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("F07-3")),
            new Student(new Name("Sylvia Law"), new Email("sylsylnoc@u.nus.edu"), new StudentNumber("A0998723P"),
                    new UserName("sylsylnoc"), new RepoName("ip"), getTagSet("year1"),
                    getDefaultAttendance(), getDefaultParticipation(), new GroupName("F07-3")),
        };
    }

    public static Group[] getSampleGroups() {
        return new Group[] {
            new Group(new GroupName("W14-4"), getMemberList(0, 1, 2, 3, 4), new LinkYear("AY2122"),
                    new RepoName("tp"), getTagSet("monday")),
            new Group(new GroupName("F07-3"), getMemberList(5, 6, 7, 8, 9), new LinkYear("AY2122"),
                    new RepoName("tp"), getTagSet("wednesday")),
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new TodoTask(new TaskName("Discuss assignment 7 with team"), getTagSet("lab"),
                    new Description("Need to set up a zoom meeting first!!!"), Task.Priority.HIGH),
            new DeadlineTask(new TaskName("Submit CS2100 Assignment"), getTagSet("sohard"),
                    new Description("Read through lecture on pipelining again"), Task.Priority.MEDIUM,
                    new TaskDate("2020-11-08")),
            new EventTask(new TaskName("Visit grandmother"), getTagSet("yay"),
                    new Description("Recently moved house, need to visit house"), Task.Priority.LOW,
                    new TaskDate("2020-11-07")),
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
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
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
