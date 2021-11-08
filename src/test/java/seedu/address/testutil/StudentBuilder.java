package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_STUDENTNUMBER = "A0123456B";
    public static final String DEFAULT_USERNAME = "username";
    public static final String DEFAULT_REPO = "ip";

    private Name name;
    private Email email;
    private StudentNumber studentNumber;
    private UserName userName;
    private RepoName repoName;
    private Set<Tag> tags;
    private Attendance attendance;
    private Participation participation;
    private GroupName groupName;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        studentNumber = new StudentNumber(DEFAULT_STUDENTNUMBER);
        userName = new UserName(DEFAULT_USERNAME);
        repoName = new RepoName(DEFAULT_REPO);
        tags = new HashSet<>();
        attendance = new Attendance();
        participation = new Participation();
        groupName = new GroupName();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        email = studentToCopy.getEmail();
        studentNumber = studentToCopy.getStudentNumber();
        userName = studentToCopy.getUserName();
        repoName = studentToCopy.getRepoName();
        tags = new HashSet<>(studentToCopy.getTags());
        attendance = new Attendance(new ArrayList<>(studentToCopy.getAttendance().attendanceList));
        participation = new Participation(new ArrayList<>(studentToCopy.getParticipation().participationList));
        groupName = studentToCopy.getGroupName();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code StudentNumber} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentNumber(String studentNumber) {
        this.studentNumber = new StudentNumber(studentNumber);
        return this;
    }

    /**
     * Sets the {@code userName} of the {@code Student} that we are building.
     */
    public StudentBuilder withUserName(String userName) {
        this.userName = new UserName(userName);
        return this;
    }

    /**
     * Sets the {@code repoName} of the {@code Student} that we are building.
     */
    public StudentBuilder withRepoName(String repoName) {
        this.repoName = new RepoName(repoName);
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Student} that we are building.
     */
    public StudentBuilder withAttendance(Integer... integers) {
        this.attendance = SampleDataUtil.getAttendanceList(integers);
        return this;
    }

    /**
     * Sets the {@code Participation} of the {@code Student} that we are building.
     */
    public StudentBuilder withParticipation(Integer... integers) {
        this.participation = SampleDataUtil.getParticipationList(integers);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Student} that we are building.
     */
    public StudentBuilder withGroupName(String groupName) {
        this.groupName = new GroupName(groupName);
        return this;
    }

    public Student build() {
        return new Student(name, email, studentNumber, userName, repoName , tags, attendance, participation, groupName);
    }
}
