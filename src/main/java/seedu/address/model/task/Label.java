package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's label in the application
 * Guarantee: The label is a non-empty string.
 */
public class Label {
    public static final String MESSAGE_CONSTRAINTS =
            "Label should be non-empty"; //temporary solution

    public final String checkedLabel;

    /**
     * Constructs a {@code Label}.
     *
     * @param label A valid label for a task.
     */
    public Label(String label) {
        requireNonNull(label);
        checkArgument(isValidLabel(label), MESSAGE_CONSTRAINTS);
        checkedLabel = label;
    }

    /**
     * Returns true if a given string is a valid name.
     *
     * @param label Label to be checked
     */
    public static boolean isValidLabel(String label) {
        return !label.equals("");
    }

    @Override
    public String toString() {
        return checkedLabel;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Label
                && checkedLabel.equals(((Label) other).checkedLabel));
    }

    @Override
    public int hashCode() {
        return checkedLabel.hashCode();
    }
}
