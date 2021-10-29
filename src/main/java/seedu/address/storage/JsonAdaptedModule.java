package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.UniqueStudentList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Jackson-friendly version of {@link Module}
 */
public class JsonAdaptedModule {

    public static final String MODULE_MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String moduleName;
    private final List<JsonAdaptedStudent> studentList = new ArrayList<>();
    private final List<JsonAdaptedTask> taskList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("moduleName") String moduleName,
                             @JsonProperty("studentList") List<JsonAdaptedStudent> studentList,
                             @JsonProperty("taskList") List<JsonAdaptedTask> taskList) {
        this.moduleName = moduleName;
        this.studentList.addAll(studentList);
        this.taskList.addAll(taskList);
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        moduleName = source.getName().getModuleName();
        List<Student> tempStudentList = source.getStudentList();
        for (Student student : tempStudentList) {
            JsonAdaptedStudent studentToAdd = new JsonAdaptedStudent(student);
            studentList.add(studentToAdd);
        }
        List<Task> tempTaskList = source.getTaskList().asModifiableObservableList();
        for (Task task : tempTaskList) {
            JsonAdaptedTask taskToAdd = new JsonAdaptedTask(task);
            taskList.add(taskToAdd);
        }
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

        final UniqueStudentList students = new UniqueStudentList();
        final UniqueTaskList tasks = new UniqueTaskList();
        if (!studentList.isEmpty()) {
            for (JsonAdaptedStudent student : studentList) {
                Student modelStudent = student.toModelType();
                students.add(modelStudent);
            }
        }
        if (!taskList.isEmpty()) {
            setModuleTaskList(tasks);
        }
        return new Module(modelModuleName, students, tasks);
    }

    /**
     * Helper method to set the task list
     */
    public void setModuleTaskList(UniqueTaskList tasks) throws IllegalValueException {
        for (JsonAdaptedTask task : taskList) {
            Task modelTask = task.toModelType();
            tasks.add(modelTask);
        }
    }
}
