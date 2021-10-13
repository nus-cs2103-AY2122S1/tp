package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.commons.util.StringUtil;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.genre.Genre;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String genre} into a {@code Genre}.
     * Leading and trailing whitespaces will be trimmed, and it will be set to lowercase.
     *
     * @throws ParseException if the given {@code genre} is invalid.
     */
    public static Genre parseGenre(String genre) throws ParseException {
        requireNonNull(genre);
        String trimmedGenre = genre.trim().toLowerCase(Locale.ROOT);
        if (!Genre.isValidGenreName(trimmedGenre)) {
            throw new ParseException(Genre.MESSAGE_CONSTRAINTS);
        }
        return new Genre(trimmedGenre);
    }

    /**
     * Parses {@code Collection<String> genres} into a {@code Set<Genre>}.
     */
    public static Set<Genre> parseGenres(Collection<String> genres) throws ParseException {
        requireNonNull(genres);
        final Set<Genre> genreSet = new HashSet<>();
        for (String genreName : genres) {
            genreSet.add(parseGenre(genreName));
        }
        return genreSet;
    }

    /**
     * Parses a {@code String episodeNumber} into a {@code Episode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code episodeNumber} is invalid.
     */
    public static Episode parseEpisode(String episodeNumber) throws ParseException {
        requireNonNull(episodeNumber);
        String trimmedEpisode = episodeNumber.trim();
        if (!Episode.isValidEpisode(trimmedEpisode)) {
            throw new ParseException(Episode.MESSAGE_CONSTRAINTS);
        }
        return new Episode(trimmedEpisode);
    }


    /**
     * Parse a {@Code String actionString} into a {@Code Action}.
     * Leading and trailing whitespaces will be trimmed, and it will be set to lowercase.
     * @param actionString string to be parsed
     * @return a {@Code Action}
     * @throws ParseException if the given {@code actionString} is invalid.
     */
    public static Action parseAction(String actionString) throws ParseException {
        requireNonNull(actionString);
        String trimmedActionLowerCase = actionString.trim().toLowerCase(Locale.ROOT);
        Action result;
        if (!Action.ADD.isValidAction(trimmedActionLowerCase)) {
            throw new ParseException(String.format(Action.MESSAGE_INVALID_ACTION_FORMAT, trimmedActionLowerCase));
        }

        switch (trimmedActionLowerCase) {
        case "add" :
            result = Action.ADD;
            break;
        case "delete" :
            result = Action.DELETE;
            break;
        default:
            result = Action.DEFAULT;
        }

        return result;
    }
}
