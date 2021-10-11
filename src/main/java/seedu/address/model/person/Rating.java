package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's rating in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)}
 */
public class Rating {


    public static final String MESSAGE_CONSTRAINTS =
        "Ratings should be a single integer between 1 to 5 inclusive";
    public static final String VALIDATION_REGEX = "$|[0-5]";
    public final String value;

    /**
     * Constructs a {@code Rating}.
     *
     * @param rating A valid phone number.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        value = rating;
    }

    /**
     * Returns true if a given string is a valid rating.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.address.model.person.Rating // instanceof handles nulls
            && value.equals(((seedu.address.model.person.Rating) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
