package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's student id in TAB.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskId(String)}
 */
public class TaskId {

    public static final String MESSAGE_CONSTRAINTS = "Task Id must not be blank and start with a capital T, "
            + "followed by a number representing its index number \n"
            + "(number must be a positive integer)";

    public static final String VALIDATION_REGEX = "T[1-9][0-9]*";

    public final String value;

    /**
     * Constructs an {@code TaskId}.
     *
     * @param taskId A valid taskId.
     */
    public TaskId(String taskId) {
        requireNonNull(taskId);
        checkArgument(isValidTaskId(taskId), MESSAGE_CONSTRAINTS);
        value = taskId;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidTaskId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskId // instanceof handles nulls
                && value.equals(((TaskId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
