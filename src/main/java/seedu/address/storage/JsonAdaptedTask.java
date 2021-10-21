package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
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
                : source.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String taskTime = source.getTime() == null
                ? " "
                : source.getTime().format(DateTimeFormatter.ISO_LOCAL_TIME);
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

        final LocalDate modelDate;
        if (!taskDate.equals(" ")) {
            try {
                modelDate = LocalDate.parse(taskDate);
            } catch (DateTimeParseException e) {
                throw new IllegalValueException("Task date should be in the format YYYY-MM-DD");
            }
        } else {
            modelDate = null;
        }

        final LocalTime modelTime;
        if (!taskTime.equals(" ")) {
            try {
                modelTime = LocalTime.parse(taskTime);
            } catch (DateTimeParseException e) {
                throw new IllegalValueException("Task time should be in the format HH:MM");
            }
        } else {
            modelTime = null;
        }

        final Venue modelVenue;
        if (!taskVenue.equals(" ")) {
            if (Venue.isValidVenue(taskVenue)) {
                modelVenue = new Venue(taskVenue);
            } else {
                throw new IllegalValueException(Venue.MESSAGE_CONSTRAINTS);
            }
        } else {
            modelVenue = null;
        }

        return new Task(modelName, modelDate, modelTime, modelVenue);
    };
}
