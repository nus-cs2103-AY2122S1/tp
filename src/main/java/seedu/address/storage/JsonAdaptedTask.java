package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Name;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskDeadline;

/**
 * Jackson-friendly version pf {@link Task}
 */
class JsonAdaptedTask {
    public static final String MISSING_FIELD_TASK_FORMAT = "Task's %s field is missing!";

    private final String taskName;
    private final Boolean isDone;
    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName, isDone, deadline}.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName, @JsonProperty("isDone") boolean isDone,
                           @JsonProperty("deadline") String deadline) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getName().toString();
        isDone = source.isDone();
        deadline = source.getTaskDeadline().toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Task toModelType() throws IllegalValueException {
        if (taskName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_TASK_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(taskName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelTaskName = new Name(taskName);

        if (isDone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_TASK_FORMAT, Boolean.class.getSimpleName()));
        }

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_TASK_FORMAT,
                    TaskDeadline.class.getSimpleName()));
        }
        if (!TaskDeadline.isValidTaskDeadline(deadline)) {
            throw new IllegalValueException(TaskDeadline.MESSAGE_CONSTRAINTS);
        }
        final TaskDeadline modelTaskDeadline = new TaskDeadline(deadline);

        return new Task(modelTaskName, isDone, modelTaskDeadline);
    }
}
