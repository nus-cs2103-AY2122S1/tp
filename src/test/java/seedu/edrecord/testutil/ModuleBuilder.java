package seedu.edrecord.testutil;

import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;

/**
 * A utility class to help with building {@code Module} objects.
 */
public class ModuleBuilder {
    private Module module;

    public ModuleBuilder(String code) {
        this.module = new Module(code);
    }

    /**
     * Adds a new {@code Assignment} to the {@code Module} that we are building.
     */
    public ModuleBuilder withAssignment(Assignment a) {
        module.addAssignment(a);
        return this;
    }

    /**
     * Adds a new {@code Group} to the {@code Module} that we are building.
     */
    public ModuleBuilder withGroup(Group group) {
        module.addGroup(group);
        return this;
    }


    /**
     * Sets the {@code assignmentCounter} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCounter(int i) {
        module.setAssignmentCounter(i);
        return this;
    }

    public Module build() {
        return module;
    }
}
