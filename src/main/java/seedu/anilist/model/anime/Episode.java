package seedu.anilist.model.anime;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.util.AppUtil.checkArgument;

/**
 * Represents an Anime's episode number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEpisode(String)}
 */
public class Episode {

    public static final String MESSAGE_CONSTRAINTS = "Episodes should only contain numbers";
    public static final String VALIDATION_REGEX = "\\d+";
    public final int episodeNumber;

    /**
     * Constructs a {@code Episode}.
     *
     * @param episodeNumber A valid episode number.
     */
    public Episode(String episodeNumber) {
        requireNonNull(episodeNumber);
        checkArgument(isValidEpisode(episodeNumber), MESSAGE_CONSTRAINTS);
        this.episodeNumber = Integer.parseInt(episodeNumber);
    }

    /**
     * Returns true if a given string is a valid episode number.
     */
    public static boolean isValidEpisode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("Episode %d", episodeNumber);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Episode // instanceof handles nulls
            && episodeNumber == ((Episode) other).episodeNumber); // state check
    }

    @Override
    public int hashCode() {
        return episodeNumber;
    }

}
