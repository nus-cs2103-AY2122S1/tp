package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskTime(String)}
 */
public class TaskTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Task time should be of format HH:MM";

    public final LocalTime taskTime;

    /**
     * Constructs a {@code TaskTime}.
     *
     * @param taskTime A valid time.
     */
    public TaskTime(String taskTime) {
        requireNonNull(taskTime);
        checkArgument(isValidTaskTime(taskTime));
        this.taskTime = LocalTime.parse(taskTime);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTaskTime(String test) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime.parse(test, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return taskTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskTime // instanceof handles nulls
                && taskTime.equals(((TaskTime) other).taskTime)); // state check
    }

    @Override
    public int hashCode() {
        return taskTime.hashCode();
    }
}
