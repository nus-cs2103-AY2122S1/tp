package seedu.tracker.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's title in the module tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class Title {

    public static final String MESSAGE_CONSTRAINTS = "Title can take any values, but the first character should "
            + "be alphanumeric. It should not be blank or more than 60 characters";

    /*
     * The first character of the title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9A-Za-z].*";

    public final String value;

    /**
     * Constructs an {@code title}.
     *
     * @param title A valid title.
     */
    public Title(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        this.value = title;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 60;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.tracker.model.module.Title // instanceof handles nulls
                && value.equals(((Title) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
