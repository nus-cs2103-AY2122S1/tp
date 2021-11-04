package seedu.anilist.logic.parser;

import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_EPISODE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;

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

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap;
        try {
            argMultimap = ParserUtil.tokenizeWithCheck(args, false,
                    new Prefix[] {PREFIX_NAME}, new Prefix[] {PREFIX_EPISODE, PREFIX_STATUS, PREFIX_GENRE});
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Episode episode;
        Status status;
        if (argMultimap.getValue(PREFIX_EPISODE).isPresent()) {
            episode = ParserUtil.parseEpisode(argMultimap.getValue(PREFIX_EPISODE).get());
        } else {
            episode = new Episode(Episode.DEFAULT_EPISODE);
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        } else {
            status = new Status(Status.DEFAULT_STATUS);
        }
        Set<Genre> genreList = ParserUtil.parseGenres(argMultimap.getAllValues(PREFIX_GENRE));
        Anime anime = new Anime(name, episode, status, genreList);
        return new AddCommand(anime);
    }
}
