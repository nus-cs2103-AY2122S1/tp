package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTeachingAssistantBuddy;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.module.Module;
import seedu.address.model.module.student.Student;

/**
 * An Immutable TeachingAssistantBuddy that is serializable to JSON format.
 */
@JsonRootName(value = "assistantbuddy")
class JsonSerializableAssistantBuddy {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAssistantBuddy} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAssistantBuddy(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                          @JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.students.addAll(students);
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyTeachingAssistantBuddy} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAssistantBuddy}.
     */
    public JsonSerializableAssistantBuddy(ReadOnlyTeachingAssistantBuddy source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TeachingAssistantBuddy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     * todo add modules to JSON, and when converting to TAB object need to add both students and modules to TAB,
     * todo as well as adding students to the module
     * todo as well as adding tasks to modules with their done/undone status
     */
    public TeachingAssistantBuddy toModelType() throws IllegalValueException {
        TeachingAssistantBuddy teachingAssistantBuddy = new TeachingAssistantBuddy();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (teachingAssistantBuddy.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            teachingAssistantBuddy.addModule(module);
        }
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (teachingAssistantBuddy.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            teachingAssistantBuddy.addStudent(student);
        }
        return teachingAssistantBuddy;
    }

}
