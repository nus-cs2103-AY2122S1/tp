package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;
/**
 * Jackson-friendly version pf {@link Task}
 */
class JsonAdaptedTask {
    private final String taskName;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName, isDone}.
     */
    @JsonCreator
    public JsonAdaptedTask(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getTaskName();
        isDone = source.isDone();
    }

    @JsonValue
    public String getTaskName() {
        return taskName;
    }

    @JsonValue
    public boolean isDone() {
        return isDone;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Task toModelType() throws IllegalValueException {
        if (!Task.isValidTaskName(taskName)) {
            throw new IllegalValueException(Task.MESSAGE_CONSTRAINTS);
        }
        return new Task(taskName, isDone);
    }
}
