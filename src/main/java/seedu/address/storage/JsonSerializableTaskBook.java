package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.TaskBook;
import seedu.address.model.task.Task;

/**
 * An Immutable TaskBook that is serializable to JSON format.
 */
@JsonRootName(value = "taskBook")
class JsonSerializableTaskBook {

    public static final String MESSAGE_DUPLICATE_TASK = "taskBook contains duplicate task(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskList} with the given task.
     */
    @JsonCreator
    public JsonSerializableTaskBook(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyTaskBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskBook}.
     */
    public JsonSerializableTaskBook(ReadOnlyTaskBook source) {
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this task book into the model's {@code TaskBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskBook toModelType() throws IllegalValueException {
        TaskBook taskList = new TaskBook();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task toAdd = jsonAdaptedTask.toModelType();
            if (taskList.hasTask(toAdd)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            taskList.addTask(toAdd);
        }
        return taskList;
    }

}
