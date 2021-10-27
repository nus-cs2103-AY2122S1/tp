package seedu.address.testutil;

import static seedu.address.testutil.TypicalStudents.getTypicalStudents;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.group.Description;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Group objects.
 */
public class GroupBuilder {

    public static final String DEFAULT_GROUPNAME = "CS2103T";
    public static final String DEFAULT_DESCRIPTION = "software eng mod";

    private GroupName groupName;
    private Description description;
    private Set<Name> studentNames;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        groupName = new GroupName(DEFAULT_GROUPNAME);
        description = new Description(DEFAULT_DESCRIPTION);
        studentNames = new HashSet<>();
    }

    /**
     * Intitialzes the GroupBuilder with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        groupName = groupToCopy.getGroupName();
        description = groupToCopy.getDescription();
        studentNames = groupToCopy.getStudentNames();
    }

    /**
     * Sets the {@code GroupName} of the {@code Group} that we are building.
     */
    public GroupBuilder withGroupName(String groupName) {
        this.groupName = new GroupName(groupName);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Group} that we are building.
     */
    public GroupBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Adds a reference of the {@code Student} that belongs to this group
     */
    public GroupBuilder withStudent(Name studentName) {
        studentNames.add(studentName);
        return this;
    }

    /**
     * Sets the {@code Students} of the {@code Group} that we are building
     * with typical students.
     */
    public GroupBuilder withTypicalStudents() {
        studentNames.addAll(getTypicalStudents().stream().map(student -> student.getName())
                .collect(Collectors.toList()));
        return this;
    }

    /**
     * Sets the {@code Students} of the {@code Group} that we are building
     * with sample students.
     */
    public GroupBuilder withSampleStudents() {
        Student[] studentsArray = SampleDataUtil.getSampleStudents();
        HashSet<Name> studentNames = new HashSet<>();
        studentNames.addAll(Arrays.asList(studentsArray).stream()
                .map(student -> student.getName())
                .collect(Collectors.toList()));
        this.studentNames = studentNames;
        return this;
    }

    /**
     * Builds a group
     * @return built group
     */
    public Group build() {
        Group group = new Group(groupName, description);
        for (Name studentName : studentNames) {
            group.addStudentName(studentName);
        }
        return group;
    }
}
