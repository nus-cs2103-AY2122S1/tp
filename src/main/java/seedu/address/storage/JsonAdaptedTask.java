package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

public class JsonAdaptedTask {

    private final String taskName;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}.
     */
    @JsonCreator
    public JsonAdaptedTask(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getDescription().description;
    }

    @JsonValue
    public String getTaskName() {
        return taskName;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Task toModelType() throws IllegalValueException {
        if (!Description.isValidDescription(taskName)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        Description taskDescription = new Description(taskName);
        return new Task(taskDescription);
    };
}
