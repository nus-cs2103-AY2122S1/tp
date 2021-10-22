package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's deadline, must only contain alphanumeric characters.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDeadline(String)}
 */
public class TaskDeadline {


    public static final String MESSAGE_CONSTRAINTS =
            "TaskDeadline must only contain alphanumeric characters and dashes";
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9-]*";
    public final String value;

    /**
     * Constructs a {@code taskDeadline}.
     *
     * @param taskDeadline A valid taskDeadline.
     */
    public TaskDeadline(String taskDeadline) {
        requireNonNull(taskDeadline);
        checkArgument(isValidTaskDeadline(taskDeadline), MESSAGE_CONSTRAINTS);
        value = taskDeadline;
    }

    /**
     * Returns true if a given string is a valid taskDeadline.
     */
    public static boolean isValidTaskDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadline // instanceof handles nulls
                && value.equals(((TaskDeadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
