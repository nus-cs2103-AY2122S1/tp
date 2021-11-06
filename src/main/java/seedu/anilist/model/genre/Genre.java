package seedu.anilist.model.genre;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.util.AppUtil.checkArgument;

import seedu.anilist.logic.commands.GenreListCommand;

/**
 * Represents a Genre of an Anime in the anime list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidGenreName(String)}
 */
public class Genre implements Comparable<Genre> {

    public static final String MESSAGE_CONSTRAINTS = "Genre(s) must be from the list of available genres.\n"
            + "To view the list, enter: "
            + GenreListCommand.COMMAND_WORD;

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
        requireNonNull(test);
        return GenreList.contains(test.toLowerCase().trim());
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

    /**
     * Compares the two genreNames, returns 0 if the other is null.
     */
    @Override
    public int compareTo(Genre o) {
        if (o == null) {
            return 0;
        }
        return this.genreName.compareTo(o.genreName);
    }
}
