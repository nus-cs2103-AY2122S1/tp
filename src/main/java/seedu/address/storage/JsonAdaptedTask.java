package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.Description;
import seedu.address.model.task.EventTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskType;
import seedu.address.model.task.TodoTask;

public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final TaskType type;
    private final String name;
    private String date;
    private String description;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final boolean isComplete;
    private final Task.Priority priority;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("dateString") String date,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("isComplete") boolean isComplete,
                           @JsonProperty("taskType")TaskType type,
                           @JsonProperty("priority") Task.Priority priority,
                           @JsonProperty("description") String description) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.isComplete = isComplete;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.priority = priority;
        this.description = description;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().toString();
        try {
            description = source.getDescription();
        } catch (NullPointerException e) {
            description = "No description";
        }

        if (source instanceof DeadlineTask) {
            this.type = TaskType.DEADLINE;
            DeadlineTask task = (DeadlineTask) source;
            date = task.getDeadline().toString();
        } else if (source instanceof EventTask) {
            this.type = TaskType.EVENT;
            EventTask task = (EventTask) source;
            date = task.getTaskDate().toString();
        } else {
            this.type = TaskType.TODO;
        }

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        this.isComplete = source.checkIsDone();
        this.priority = source.getPriority();
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName()));
        }

        if (priority == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Task.Priority.class.getSimpleName()));
        }

        if (!TaskName.isValidName(name)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelName = new TaskName(name);

        final Set<Tag> modelTags = new HashSet<>(taskTags);

        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (type == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskType.class.getSimpleName()));
        }

        switch (type) {
        case TODO:
            return new TodoTask(modelName, modelTags, isComplete, modelDescription, priority);
        case DEADLINE:
            if (date == null) {
                throw new IllegalValueException(
                        String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDate.class.getSimpleName()));
            }

            if (!TaskDate.isValidDeadline(date)) {
                throw new IllegalValueException(TaskDate.MESSAGE_CONSTRAINTS);
            }
            final TaskDate modelDeadlineDate = new TaskDate(date);
            return new DeadlineTask(modelName, modelTags, isComplete, modelDeadlineDate, modelDescription, priority);
        case EVENT:
            if (date == null) {
                throw new IllegalValueException(
                        String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDate.class.getSimpleName()));
            }

            if (!TaskDate.isValidDeadline(date)) {
                throw new IllegalValueException(TaskDate.MESSAGE_CONSTRAINTS);
            }
            final TaskDate modelEventDate = new TaskDate(date);
            return new EventTask(modelName, modelTags, isComplete, modelEventDate, modelDescription, priority);
        default:
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskType.class.getSimpleName()));
        }
    }
}
