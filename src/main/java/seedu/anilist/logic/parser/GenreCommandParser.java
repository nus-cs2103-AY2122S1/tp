package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.commons.core.Messages.MESSAGE_OUT_OF_RANGE_INDEX;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_ACTION;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.anilist.logic.parser.ParserUtil.parseAction;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.Action;
import seedu.anilist.logic.commands.GenreAddCommand;
import seedu.anilist.logic.commands.GenreCommand;
import seedu.anilist.logic.commands.GenreDeleteCommand;
import seedu.anilist.logic.parser.exceptions.IntegerOutOfRangeException;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.genre.Genre;


/**
 * Parses input arguments and creates a new GenreCommand object
 */
public class GenreCommandParser implements Parser<GenreCommand> {
    private static final String MESSAGE_INVALID_COMMAND_GENRE = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            GenreCommand.MESSAGE_USAGE);
    private static final String MESSAGE_UNSUPPORTED_ACTION = String.format(Action.MESSAGE_ACTION_NOT_SUPPORTED_FORMAT,
            GenreCommand.COMMAND_WORD);

    /**
     * Parses the given {@code String} of arguments in the context of the GenreCommand
     * and returns a GenreCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GenreCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap;

        try {
            argMultimap = ParserUtil.tokenizeWithCheck(args, GenreCommand.REQUIRES_PREAMBLE,
                    GenreCommand.REQUIRED_PREFIXES, GenreCommand.OPTIONAL_PREFIXES);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_GENRE);
        }

        Index index;
        try {
            String preamble = argMultimap.getPreamble();
            index = ParserUtil.parseIndex(preamble);
        } catch (IntegerOutOfRangeException e) {
            throw new ParseException(MESSAGE_OUT_OF_RANGE_INDEX);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_GENRE, pe);
        }

        GenreCommand.GenresDescriptor genresDescriptor = new GenreCommand.GenresDescriptor();
        Optional<String> actionParam = argMultimap.getValue(PREFIX_ACTION);
        Action action = parseAction(actionParam.get());
        List<String> genreParams = argMultimap.getAllValues(PREFIX_GENRE);
        parseGenresForEdit(genreParams).ifPresent(genresDescriptor::setGenres);

        switch (action) {
        case ADD :
            return new GenreAddCommand(index, genresDescriptor);
        case DELETE :
            return new GenreDeleteCommand(index, genresDescriptor);
        default :
            throw new ParseException(MESSAGE_UNSUPPORTED_ACTION);
        }
    }

    /**
     * Parses {@code Collection<String> genres} into a {@code Set<Genre>} if {@code genres} is non-empty.
     * If {@code genres} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Genre>} containing zero genres.
     */
    private Optional<Set<Genre>> parseGenresForEdit(Collection<String> genres) throws ParseException {
        assert genres != null;
        return Optional.of(ParserUtil.parseGenres(genres));
    }
}
