package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Homework for a Lesson in the address book.
 */
public class Homework {
    public static final String MESSAGE_CONSTRAINTS = "Homework description can have a maximum of "
            + "50 characters.";
    public static final String VALIDATION_REGEX = ".{0,50}";

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
     * Returns true if a given string is a valid homework description.
     *
     * @param test The string to test.
     * @return True if test is a valid homework description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Homework // instanceof handles nulls
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
        return description;
    }

}

