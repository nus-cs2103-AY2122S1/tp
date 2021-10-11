package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's index number in the task's task list.
 */
public class TaskID {
    public static final String MESSAGE_CONSTRAINTS =
            "TaskID numbers should only contain numbers, and it should be at least 1 digit long";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String value;

    /**
     * Constructs a {@code TaskID}.
     *
     * @param taskID A valid taskID number.
     */
    public TaskID(String taskID) {
        requireNonNull(taskID);
        checkArgument(isValidTaskID(taskID), MESSAGE_CONSTRAINTS);
        value = taskID;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidTaskID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskID // instanceof handles nulls
                && value.equals(((TaskID) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
