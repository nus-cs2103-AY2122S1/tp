package seedu.address.storage;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.UniqueId;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;


/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String deadline;
    private final String uniqueId;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
                           @JsonProperty("uniqueId") String uniqueId) {
        this.name = name;
        this.deadline = deadline;
        this.uniqueId = uniqueId;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        deadline = source.getDeadline().deadline.format(formatter);
        uniqueId = source.getId().getUuid().toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidName(name)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(name);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (uniqueId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UniqueId.class.getSimpleName()));
        }

        final UniqueId modelUniqueId = UniqueId.generateId(uniqueId);

        Task task = new Task(modelDescription, modelDeadline, modelUniqueId);
        modelUniqueId.setOwner(task);
        return task;
    }
}
