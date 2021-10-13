package seedu.anilist.model.genre;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.util.AppUtil.checkArgument;

/**
 * Represents a Genre of an Anime in the anime list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidGenreName(String)}
 */
public class Genre {

    public static final String MESSAGE_CONSTRAINTS = "Genres names should be alphanumeric with at most one space"
            + "separating each word.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}]+(?:[\\p{IsWhite_Space}][\\p{Alnum}]+)*";

    public final String genreName;

    /**
     * Constructs a {@code Genre}.
     *
     * @param genreName A valid genre name.
     */
    public Genre(String genreName) {
        requireNonNull(genreName);
        checkArgument(isValidGenreName(genreName), MESSAGE_CONSTRAINTS);
        this.genreName = genreName;
    }

    /**
     * Returns true if a given string is a valid genre name.
     */
    public static boolean isValidGenreName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Genre // instanceof handles nulls
                && genreName.equals(((Genre) other).genreName)); // state check
    }

    @Override
    public int hashCode() {
        return genreName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + genreName + ']';
    }

}
