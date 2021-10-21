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
import seedu.address.model.task.TodoTask;

public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final int i;
    private final String name;
    private String deadline;
    private String description;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final boolean isComplete;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("isComplete") boolean isComplete,
                           @JsonProperty("i") int i) {
        this.name = name;
        this.deadline = deadline;
        this.i = i;
        this.isComplete = isComplete;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        i = 0;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().toString();
        try {
            description = source.getDescription().toString();
        } catch (NullPointerException e) {
            description = "No description";
        }
        if (source instanceof DeadlineTask) {
            this.i = 1;
            DeadlineTask task = (DeadlineTask) source;
            deadline = task.getDeadline().toString();
        } else if (source instanceof EventTask) {
            this.i = 2;
            EventTask task = (EventTask) source;
            deadline = task.getTaskDate().toString();
        } else {
            this.i = 0;
        }

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        this.isComplete = source.checkIsDone();
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

        if (!TaskName.isValidName(name)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelName = new TaskName(name);

        final Set<Tag> modelTags = new HashSet<>(taskTags);

        final Description modelDescription = new Description(description);

        //if (task instanceof DeadlineTask) {
        if (i == 1) {
            if (deadline == null) {
                throw new IllegalValueException(
                        String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDate.class.getSimpleName()));
            }

            if (!TaskDate.isValidDeadline(deadline)) {
                throw new IllegalValueException(TaskDate.MESSAGE_CONSTRAINTS);
            }
            final TaskDate modelTaskDate = new TaskDate(deadline);
            return new DeadlineTask(modelName, modelTags, isComplete, modelTaskDate, modelDescription);
        }
        //if (task instanceof EventTask) {
        if (i == 2) {
            if (deadline == null) {
                throw new IllegalValueException(
                        String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDate.class.getSimpleName()));
            }

            if (!TaskDate.isValidDeadline(deadline)) {
                throw new IllegalValueException(TaskDate.MESSAGE_CONSTRAINTS);
            }
            final TaskDate modelTaskDate = new TaskDate(deadline);
            return new EventTask(modelName, modelTags, isComplete, modelTaskDate, modelDescription);
        }

        return new TodoTask(modelName, modelTags, isComplete, modelDescription);
    }
}
