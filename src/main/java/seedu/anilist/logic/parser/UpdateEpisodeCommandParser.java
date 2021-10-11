package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_EPISODE;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.UpdateEpisodeCommand;
import seedu.anilist.logic.parser.exceptions.ParseException;

public class UpdateEpisodeCommandParser implements Parser<UpdateEpisodeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateEpisodeCommand
     * and returns an UpdateEpisodeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateEpisodeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EPISODE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UpdateEpisodeCommand.MESSAGE_USAGE), pe);
        }

        UpdateEpisodeCommand.EpisodeDescriptor episodeDescriptor = new UpdateEpisodeCommand.EpisodeDescriptor();
        if (argMultimap.getValue(PREFIX_EPISODE).isPresent()) {
            episodeDescriptor.setEpisode(ParserUtil.parseEpisode(argMultimap.getValue(PREFIX_EPISODE).get()));
        }

        if (!episodeDescriptor.isEpisodeUpdated()) {
            throw new ParseException(UpdateEpisodeCommand.MESSAGE_NOT_UPDATED);
        }

        return new UpdateEpisodeCommand(index, episodeDescriptor);
    }

}
