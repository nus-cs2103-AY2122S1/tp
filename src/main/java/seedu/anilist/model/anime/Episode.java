package seedu.anilist.model.anime;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.util.AppUtil.checkArgument;

import seedu.anilist.commons.util.StringUtil;

/**
 * Represents an Anime's episode number in the anime list.
 * Guarantees: immutable; is valid as declared in {@link #isValidEpisode(String)}
 */
public class Episode {

    public static final int MAX_EPISODE = 99999;
    public static final String MESSAGE_CONSTRAINTS =
        "Episodes should be a non-negative integer less than or equals to " + MAX_EPISODE;
    public static final String VALIDATION_REGEX = "\\d+";
    public static final String DEFAULT_EPISODE = "0";
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
        return test.matches(VALIDATION_REGEX)
            && StringUtil.isIntegerInRange(test, 0, MAX_EPISODE);
    }

    @Override
    public String toString() {
        return String.format("%d", episodeNumber);
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
