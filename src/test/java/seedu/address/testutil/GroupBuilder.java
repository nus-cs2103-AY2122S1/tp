package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.student.Group;
import seedu.address.model.student.ID;
import seedu.address.model.student.Student;

public class GroupBuilder {
    public static final String DEFAULT_NAME = "T01A";

    private String name;
    private List<ID> students;
    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        name = DEFAULT_NAME;
        students = new ArrayList<>();
    }

    /**
     * Initializes the GroupBuilder with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        name = groupToCopy.name;
        students = new ArrayList<>(groupToCopy.students);
    }

    /**
     * Sets the {@code Name} of the {@code Group} that we are building.
     */
    public GroupBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code students} of the {@code Group} that we are building.
     */
    public GroupBuilder withStudents(Student... students) {
        List<Student> studentList = new ArrayList<>(Arrays.asList(students));
        this.students = studentList.stream()
                .map(student -> student.getId())
                .collect(Collectors.toList());
        return this;
    }

    public Group build() {
        return new Group(name, students);
    }
}
