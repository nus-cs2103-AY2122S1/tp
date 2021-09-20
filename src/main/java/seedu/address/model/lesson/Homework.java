package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Homework in the address book.
 */
public class Homework {
    public static final String MESSAGE_CONSTRAINTS = "Homework description should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String description;

    /**
     * Constructs a {@code Homework}.
     *
     * @param description A valid description.
     */
    public Homework(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.lesson.Homework // instanceof handles nulls
                && description.equals(((Homework) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + description + ']';
    }

}

