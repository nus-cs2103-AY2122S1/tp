package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.commons.util.StringUtil;
import seedu.anilist.logic.commands.Action;
import seedu.anilist.logic.parser.exceptions.IntegerOutOfRangeException;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.anime.Status;
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
        if (!StringUtil.isValidNumber(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new IntegerOutOfRangeException(1, Integer.MAX_VALUE);
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
        String trimmedGenre = genre.trim().toLowerCase();
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
     * Leading and trailing whitespaces will be trimmed.
     * @param actionString string to be parsed
     * @return a {@Code Action}
     * @throws ParseException if the given {@code actionString} is invalid.
     */
    public static Action parseAction(String actionString) throws ParseException {
        requireNonNull(actionString);
        String trimmedAction = actionString.trim();
        Action result;
        if (!Action.isValidAction(trimmedAction)) {
            throw new ParseException(String.format(Action.MESSAGE_INVALID_ACTION_FORMAT, trimmedAction));
        }

        result = Action.actionFromString(trimmedAction);

        return result;
    }

    /**
     * Parses a {@code String status} into a {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(trimmedStatus);
    }

    /**
     * Tokenizes a string into a map of parameters.
     * Checks if unused params are present.
     * Checks if preamble is present.
     * @param args the string to tokenize
     * @param requiresPreamble whether the command requires a preamble
     * @param requiredPrefixes the Set of required prefixes used in the command
     * @param optionalPrefixes the Set of optional prefixes used in the command
     * @return A argumentMultimap
     * @throws ParseException if there are unused params present
     */
    public static ArgumentMultimap tokenizeWithCheck(String args, boolean requiresPreamble, Prefix[] requiredPrefixes,
                                                     Prefix... optionalPrefixes) throws ParseException {
        Prefix[] allPrefixes = CliSyntax.getAllPrefixes();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, allPrefixes);
        Set<Prefix> requiredPrefixesSet = new HashSet<>(Arrays.asList(requiredPrefixes));
        Set<Prefix> optionalPrefixesSet = new HashSet<>(Arrays.asList(optionalPrefixes));
        String preamble = argMultimap.getPreamble();
        boolean hasPreamble = !preamble.isEmpty();
        if (!requiresPreamble && hasPreamble) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        if (requiresPreamble && !hasPreamble) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        boolean areRequiredPrefixesPresent = arePrefixesPresent(argMultimap, requiredPrefixes);
        if (!areRequiredPrefixesPresent) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        for (Prefix p : allPrefixes) {
            boolean isRequiredPrefix = requiredPrefixesSet.contains(p);
            boolean isOptionalPrefix = optionalPrefixesSet.contains(p);
            boolean isValidPrefix = isOptionalPrefix || isRequiredPrefix;
            boolean isPrefixPresent = isPrefixPresent(argMultimap, p);
            if (isPrefixPresent && !isValidPrefix) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }
        }
        return argMultimap;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> isPrefixPresent(argumentMultimap, prefix));
    }

    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}
