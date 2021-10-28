package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDate(String)}
 */
public class TaskDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Task date should be of format YYYY-MM-DD";

    public final LocalDate taskDate;

    /**
     * Constructs a {@code TaskDate}.
     *
     * @param taskDate A valid date.
     */
    public TaskDate(String taskDate) {
        requireNonNull(taskDate);
        checkArgument(isValidTaskDate(taskDate));
        this.taskDate = LocalDate.parse(taskDate);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidTaskDate(String test) {
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return taskDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDate // instanceof handles nulls
                && taskDate.equals(((TaskDate) other).taskDate)); // state check
    }

    @Override
    public int hashCode() {
        return taskDate.hashCode();
    }
}
