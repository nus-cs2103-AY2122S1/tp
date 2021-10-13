package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_ACTION;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.anilist.logic.parser.ParserUtil.parseAction;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.GenreAddCommand;
import seedu.anilist.logic.commands.GenreCommand;
import seedu.anilist.logic.commands.GenreDeleteCommand;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.genre.Genre;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class GenreCommandParser implements Parser<GenreCommand> {
    @Override
    public GenreCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ACTION, PREFIX_GENRE);

        if (!argMultimap.getValue(PREFIX_ACTION).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenreCommand.MESSAGE_USAGE));
        }
        if (!(argMultimap.getAllValues(PREFIX_GENRE).size() > 0)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenreCommand.MESSAGE_USAGE));
        }

        Index index;
        Action action;
        String actionString;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GenreCommand.MESSAGE_USAGE), pe);
        }

        GenreCommand.GenresDescriptor genresDescriptor = new GenreCommand.GenresDescriptor();

        actionString = argMultimap.getValue(PREFIX_ACTION).get();
        action = parseAction(actionString);
        parseGenresForEdit(argMultimap.getAllValues(PREFIX_GENRE)).ifPresent(genresDescriptor::setGenres);

        switch (action) {
        case ADD :
            return new GenreAddCommand(index, genresDescriptor);
        case DELETE :
            return new GenreDeleteCommand(index, genresDescriptor);
        default :
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenreCommand.MESSAGE_USAGE));
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
