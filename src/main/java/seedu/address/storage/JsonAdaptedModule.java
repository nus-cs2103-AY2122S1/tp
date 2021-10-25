package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Module}
 */
public class JsonAdaptedModule {

    public static final String MODULE_MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String moduleName;
    private final List<Student> studentList;
    private final List<Task> taskList;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("moduleName") String moduleName,
                             @JsonProperty("uniqueStudentList") List<Student> studentList,
                             @JsonProperty("uniqueTaskList") List<Task> taskList) {
        this.moduleName = moduleName;
        this.studentList = studentList;
        this.taskList = taskList;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        moduleName = source.getName().getModuleName();
        studentList = source.getStudentList();
        taskList = source.getTaskList().asModifiableObservableList();
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Module toModelType() throws IllegalValueException {

        if (moduleName == null) {
            throw new IllegalValueException(String.format(MODULE_MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleName.class.getSimpleName()));
        }
        if (!ModuleName.isValidName(moduleName)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ModuleName modelModuleName = new ModuleName(moduleName);
        return new Module(modelModuleName);
    }
}
