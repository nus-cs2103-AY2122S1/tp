package seedu.anilist.logic.parser;

import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_EPISODE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.anilist.logic.commands.AddCommand;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.genre.Genre;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    private static final String MESSAGE_INVALID_COMMAND_ADD = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddCommand.MESSAGE_USAGE);

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap;
        try {
            argMultimap = ParserUtil.tokenizeWithCheck(args, AddCommand.REQUIRES_PREAMBLE,
                    AddCommand.REQUIRED_PREFIXES, AddCommand.OPTIONAL_PREFIXES);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_ADD);
        }

        Optional<String> nameParam = argMultimap.getValue(PREFIX_NAME);
        Name name = ParserUtil.parseName(nameParam.get());
        Optional<String> episodeParam = argMultimap.getValue(PREFIX_EPISODE);
        Episode episode;
        Optional<String> statusParam = argMultimap.getValue(PREFIX_STATUS);
        Status status;
        List<String> genreParams = argMultimap.getAllValues(PREFIX_GENRE);
        Set<Genre> genreList = ParserUtil.parseGenres(genreParams);
        if (episodeParam.isPresent()) {
            episode = ParserUtil.parseEpisode(episodeParam.get());
        } else {
            episode = new Episode(Episode.DEFAULT_EPISODE);
        }
        if (statusParam.isPresent()) {
            status = ParserUtil.parseStatus(statusParam.get());
        } else {
            status = new Status(Status.DEFAULT_STATUS);
        }

        Anime anime = new Anime(name, episode, status, genreList);
        return new AddCommand(anime);
    }
}
