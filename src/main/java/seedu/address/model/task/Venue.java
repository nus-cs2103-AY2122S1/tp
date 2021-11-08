package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's venue in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidVenue(String)}
 */
public class Venue {
    public static final String MESSAGE_CONSTRAINTS =
            "Task address should contain at least 1 non-whitespace character";

    /*
     * The first character of the venue must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\S+.*";

    public final String venue;

    /**
     * Constructs a {@code Venue}.
     *
     * @param venue A valid name.
     */
    public Venue(String venue) {
        requireNonNull(venue);
        checkArgument(isValidVenue(venue), MESSAGE_CONSTRAINTS);
        this.venue = venue;
    }

    /**
     * Returns true if a given string is a valid Venue.
     */
    public static boolean isValidVenue(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return venue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Venue // instanceof handles nulls
                && venue.equals(((Venue) other).venue)); // state check
    }

    @Override
    public int hashCode() {
        return venue.hashCode();
    }
}
