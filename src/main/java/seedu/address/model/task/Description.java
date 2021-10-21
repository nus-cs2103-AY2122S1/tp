package seedu.address.model.task;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Description of the task
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should only contain alphanumeric characters and spaces.";

    public static final Description NO_DESCRIPTION =
            new Description("No Description");

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

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
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidDescription(String test) {
        try {
            return test.matches(VALIDATION_REGEX);
        //If there is no description, it still is considered valid.
        } catch (NullPointerException e) {
            return true;
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
