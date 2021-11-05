package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.commons.core.Messages.MESSAGE_OUT_OF_RANGE_INDEX;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_EPISODE;

import java.util.Optional;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.UpdateEpisodeCommand;
import seedu.anilist.logic.parser.exceptions.IntegerOutOfRangeException;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Episode;


public class UpdateEpisodeCommandParser implements Parser<UpdateEpisodeCommand> {
    private static final String MESSAGE_INVALID_COMMAND_UPDATE = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UpdateEpisodeCommand.MESSAGE_USAGE);

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateEpisodeCommand
     * and returns an UpdateEpisodeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateEpisodeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap;

        try {
            argMultimap = ParserUtil.tokenizeWithCheck(args, UpdateEpisodeCommand.REQUIRES_PREAMBLE,
                    UpdateEpisodeCommand.REQUIRED_PREFIXES, UpdateEpisodeCommand.OPTIONAL_PREFIXES);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_UPDATE);
        }

        Index index;

        try {
            String preamble = argMultimap.getPreamble();
            index = ParserUtil.parseIndex(preamble);
        } catch (IntegerOutOfRangeException e) {
            throw new ParseException(MESSAGE_OUT_OF_RANGE_INDEX);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_UPDATE, pe);
        }

        UpdateEpisodeCommand.EpisodeDescriptor episodeDescriptor = new UpdateEpisodeCommand.EpisodeDescriptor();
        Optional<String> episodeParam = argMultimap.getValue(PREFIX_EPISODE);
        Episode episode = ParserUtil.parseEpisode(episodeParam.get());
        episodeDescriptor.setEpisode(episode);

        if (!episodeDescriptor.isEpisodeUpdated()) {
            throw new ParseException(UpdateEpisodeCommand.MESSAGE_NOT_UPDATED);
        }

        return new UpdateEpisodeCommand(index, episodeDescriptor);
    }

}
