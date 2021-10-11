package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.student.Group;
import seedu.address.model.student.ID;

/**
 * A utility class to help with building Group objects.
 */
public class GroupBuilder {

    public static final String DEFAULT_VALUE = "T02A";

    private List<ID> students;
    private String value;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        students = new ArrayList<>();
        value = DEFAULT_VALUE;
    }

    /**
     * Initializes the GroupBuilder with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        students = groupToCopy.getStudents();
        value = groupToCopy.getValue();
    }

    /**
     * Sets the {@code students} of the {@code group} that we are building.
     */
    public GroupBuilder withStudents(List<ID> students) {
        this.students = students;
        return this;
    }

    /**
     * Sets the {@code value} of the {@code group} that we are building.
     */
    public GroupBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public Group build() {
        Group group = new Group(value);
        group.getStudents().addAll(students);
        return group;
    }
}
