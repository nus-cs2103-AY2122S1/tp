package seedu.address.model.task;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Description of the task
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should only contain ASCII characters.";

    public static final Description NO_DESCRIPTION =
            new Description("");

    public static final String REGEX = "[\\x00-\\x7F]*";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }


    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        if (test == null) {
            return true;
        } else {
            return test.matches(REGEX);
        }
    }


    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
