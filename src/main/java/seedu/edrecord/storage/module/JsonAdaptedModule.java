package seedu.edrecord.storage.module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.edrecord.commons.exceptions.IllegalValueException;
import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.group.GroupSystem;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.storage.JsonAdaptedAssignment;
import seedu.edrecord.storage.group.JsonAdaptedGroup;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "Assignment list for this module contains duplicates.";
    public static final String INVALID_COUNTER = "Assignment counter for this module is invalid.";
    public static final String INVALID_ID = "ID for this assignment is invalid.";

    private final String code;
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();
    private final List<JsonAdaptedAssignment> assignments = new ArrayList<>();
    private final Integer assignmentCounter;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module code, groups and assignments.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("code") String code,
                             @JsonProperty("groups") List<JsonAdaptedGroup> groups,
                             @JsonProperty("assignments") List<JsonAdaptedAssignment> assignments,
                             @JsonProperty("id") Integer assignmentCounter) {
        this.code = code;
        if (groups != null) {
            this.groups.addAll(groups);
        }
        if (assignments != null) {
            this.assignments.addAll(assignments);
        }
        this.assignmentCounter = assignmentCounter;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        code = source.getCode();
        groups.addAll(source.getGroupSystem().getGroupList().stream()
                .map(JsonAdaptedGroup::new)
                .collect(Collectors.toList()));
        assignments.addAll(source.getAssignmentList().stream()
                .map(JsonAdaptedAssignment::new)
                .collect(Collectors.toList()));
        assignmentCounter = source.getAssignmentCounter();
    }

    /**
     * Converts this {@code JsonAdaptedModule} object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "module code"));
        }
        if (!Module.isValidModuleCode(code)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        if (!Module.isValidSavedModuleCode(code)) {
            throw new IllegalValueException(Module.MESSAGE_INVALID_JSON);
        }
        if (Module.MODULE_SYSTEM.hasModule(code)) {
            throw new IllegalValueException(Module.MESSAGE_DUPLICATE);
        }

        GroupSystem groupSystem = new GroupSystem();
        for (JsonAdaptedGroup group : groups) {
            groupSystem.addGroup(group.toModelType());
        }

        Module module = new Module(code, groupSystem);

        if (assignmentCounter == null || assignmentCounter <= 0) {
            throw new IllegalValueException(INVALID_COUNTER);
        } else {
            module.setAssignmentCounter(assignmentCounter);
        }

        for (JsonAdaptedAssignment jsonAdaptedAssignment : assignments) {
            Assignment asg = jsonAdaptedAssignment.toModelType();
            if (module.hasSameNameAssignment(asg) || module.hasSameIdAssignment(asg)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ASSIGNMENT);
            } else if (asg.getId() < 1 || asg.getId() > assignmentCounter) {
                throw new IllegalValueException(INVALID_ID);
            }
            module.addAssignment(asg);
        }
        return module;

    }

}
