package seedu.unify.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.unify.commons.exceptions.IllegalValueException;
import seedu.unify.model.ReadOnlyUniFy;
import seedu.unify.model.UniFy;
import seedu.unify.model.task.Task;

/**
 * An Immutable UniFy that is serializable to JSON format.
 */
@JsonRootName(value = "unify")
class JsonSerializableUniFy {

    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableUniFy} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableUniFy(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyUniFy} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableUniFy}.
     */
    public JsonSerializableUniFy(ReadOnlyUniFy source) {
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this unify into the model's {@code UniFy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UniFy toModelType() throws IllegalValueException {
        UniFy uniFy = new UniFy();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (uniFy.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            uniFy.addTask(task);
        }
        return uniFy;
    }

}
