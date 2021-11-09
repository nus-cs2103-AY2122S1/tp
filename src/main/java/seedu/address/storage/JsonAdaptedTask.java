package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleName;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;

/**
 * Jackson-friendly version of {@link Task}
 */
public class JsonAdaptedTask {

    public static final String TASK_MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String moduleName;
    private final String taskId;
    private final String taskName;
    private final String taskDeadline;
    private final boolean isComplete;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("moduleName") String moduleName, @JsonProperty("taskId") String taskId,
                              @JsonProperty("taskName") String taskName,
                              @JsonProperty("taskDeadline") String taskDeadline,
                              @JsonProperty("isComplete") boolean isComplete) {
        this.moduleName = moduleName;
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDeadline = taskDeadline;
        this.isComplete = isComplete;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        moduleName = source.getModuleNameString();
        taskId = source.getTaskId().value;
        taskName = source.getTaskName().taskName;
        taskDeadline = source.getTaskDeadline().value;
        isComplete = source.isComplete();
    }

    /**
     * Converts this Jackson-friendly adapted tasl object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Task toModelType() throws IllegalValueException {

        if (moduleName == null) {
            throw new IllegalValueException(String.format(TASK_MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleName.class.getSimpleName()));
        }
        if (!ModuleName.isValidName(moduleName)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ModuleName modelModuleName = new ModuleName(moduleName);

        if (taskId == null) {
            throw new IllegalValueException(String.format(TASK_MISSING_FIELD_MESSAGE_FORMAT,
                    TaskId.class.getSimpleName()));
        }
        if (!TaskId.isValidTaskId(taskId)) {
            throw new IllegalValueException(TaskId.MESSAGE_CONSTRAINTS);
        }
        final TaskId modelTaskId = new TaskId(taskId);

        if (taskName == null) {
            throw new IllegalValueException(String.format(TASK_MISSING_FIELD_MESSAGE_FORMAT,
                    TaskName.class.getSimpleName()));
        }
        if (!TaskName.isValidTaskName(taskName)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelTaskName = new TaskName(taskName);

        if (taskDeadline == null) {
            throw new IllegalValueException(String.format(TASK_MISSING_FIELD_MESSAGE_FORMAT,
                    TaskDeadline.class.getSimpleName()));
        }
        if (!TaskDeadline.isValidTaskDeadline(taskDeadline)) {
            throw new IllegalValueException(TaskDeadline.MESSAGE_CONSTRAINTS);
        }
        final TaskDeadline modelTaskDeadline = new TaskDeadline(taskDeadline);
        return new Task(modelModuleName, modelTaskId, modelTaskName, modelTaskDeadline, isComplete);
    }
}
