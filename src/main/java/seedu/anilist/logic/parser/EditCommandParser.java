package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.EditCommand;
import seedu.anilist.logic.commands.EditCommand.EditAnimeDescriptor;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.genre.Genre;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GENRE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditAnimeDescriptor editAnimeDescriptor = new EditAnimeDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editAnimeDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        parseGenresForEdit(argMultimap.getAllValues(PREFIX_GENRE)).ifPresent(editAnimeDescriptor::setGenres);

        if (!editAnimeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editAnimeDescriptor);
    }

    /**
     * Parses {@code Collection<String> genres} into a {@code Set<Genre>} if {@code genres} is non-empty.
     * If {@code genres} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Genre>} containing zero genres.
     */
    private Optional<Set<Genre>> parseGenresForEdit(Collection<String> genres) throws ParseException {
        assert genres != null;

        if (genres.isEmpty()) {
            return Optional.empty();
        }

        Collection<String> genreSet = genres.size() == 1 && genres.contains("") ? Collections.emptySet() : genres;
        return Optional.of(ParserUtil.parseGenres(genreSet));
    }

}
