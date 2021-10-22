package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskTime;
import seedu.address.model.task.Venue;

public class JsonAdaptedTask {

    public static final String MISSING_NAME_MESSAGE_FORMAT = "Task name field is missing!";

    private final String task;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code task}.
     */
    @JsonCreator
    public JsonAdaptedTask(String task) {
        this.task = task;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        String taskName = source.getTaskName().taskName;
        String taskDate = source.getDate() == null
                ? " "
                : source.getDate().taskDate.toString();
        String taskTime = source.getTime() == null
                ? " "
                : source.getTime().toString();
        String taskVenue = source.getVenue() == null
                ? " "
                : source.getVenue().venue;
        String seperator = "|";
        task = taskName + seperator + taskDate + seperator + taskTime + seperator + taskVenue;
    }

    @JsonValue
    public String getTask() {
        return task;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Task toModelType() throws IllegalValueException {
        String[] taskDetails = task.split("\\|");
        String taskName = taskDetails[0];
        String taskDate = taskDetails[1];
        String taskTime = taskDetails[2];
        String taskVenue = taskDetails[3];

        if (taskName.equals(" ")) {
            throw new IllegalValueException(MISSING_NAME_MESSAGE_FORMAT);
        }
        if (!TaskName.isValidTaskName(taskName)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelName = new TaskName(taskName);

        TaskDate modelDate = null;
        if (!taskDate.equals(" ")) {
            if (TaskDate.isValidTaskDate(taskDate)) {
                modelDate = new TaskDate(taskDate);
            } else {
                throw new IllegalValueException(TaskDate.MESSAGE_CONSTRAINTS);
            }
        }

        TaskTime modelTime = null;
        if (!taskTime.equals(" ")) {
            if (TaskTime.isValidTaskTime(taskTime)) {
                modelTime = new TaskTime(taskTime);
            } else {
                throw new IllegalValueException(TaskTime.MESSAGE_CONSTRAINTS);
            }
        }

        Venue modelVenue = null;
        if (!taskVenue.equals(" ")) {
            if (Venue.isValidVenue(taskVenue)) {
                modelVenue = new Venue(taskVenue);
            } else {
                throw new IllegalValueException(Venue.MESSAGE_CONSTRAINTS);
            }
        }

        return new Task(modelName, modelDate, modelTime, modelVenue);
    };
}
