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
import seedu.address.model.task.*;

public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final int i = 0;
    private final Task task;
    private final String name;
    private String deadline;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String status;
    private boolean isComplete;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           Task task, @JsonProperty("status") String status) {
        this.name = task.getName().toString();
        this.deadline = deadline;
        this.task = task;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.status = status;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        this.task = source;
        name = source.getName().toString();
        if (source instanceof DeadlineTask) {
            DeadlineTask task = (DeadlineTask) source;
            deadline = task.getDeadline().toString();
        } 
//        else {
//            deadline = "2222-01-01";
//        }
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        status = source.getStatusString();
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
        
        if (task instanceof DeadlineTask) {
            if (deadline == null) {
                throw new IllegalValueException(
                        String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDate.class.getSimpleName()));
            }

            if (!TaskDate.isValidDeadline(deadline)) {
                throw new IllegalValueException(TaskDate.MESSAGE_CONSTRAINTS);
            }
            final TaskDate modelTaskDate = new TaskDate(deadline);
            return new DeadlineTask(modelName, modelTags, isComplete, modelTaskDate);
        }
        if (task instanceof EventTask) {
            if (deadline == null) {
                throw new IllegalValueException(
                        String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDate.class.getSimpleName()));
            }

            if (!TaskDate.isValidDeadline(deadline)) {
                throw new IllegalValueException(TaskDate.MESSAGE_CONSTRAINTS);
            }
            final TaskDate modelTaskDate = new TaskDate(deadline);
            return new EventTask(modelName, modelTags, isComplete, modelTaskDate);
        }
        
        return new TodoTask(modelName, modelTags, isComplete);
    }
}
