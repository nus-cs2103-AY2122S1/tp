package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.id.UniqueId;

/**
 * Jackson-friendly version of individual mappings in {@code tasksCompletion}.
 */
public class JsonAdaptedTaskCompletion {
    private final String taskIdString;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedTaskCompletion} with the given {@code taskIdString} and {@code isDone}.
     */
    @JsonCreator
    public JsonAdaptedTaskCompletion(@JsonProperty("taskIdString") String taskIdString,
            @JsonProperty("isDone") boolean isDone) {
        this.taskIdString = taskIdString;
        this.isDone = isDone;
    }

    /**
     * Converts this Jackson-friendly task id String into the model's {@code UniqueId} object.
     */
    public UniqueId getModelTaskId() {
        return UniqueId.generateId(taskIdString);
    }

    /**
     * Converts this Jackson-friendly isDone into the model's Boolean {@code isDone} object.
     */
    public Boolean getModelIsDone() {
        return isDone;
    }
}
