package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.UpdateStatusCommand;
import seedu.anilist.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class UpdateStatusCommandParser implements Parser<UpdateStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateStatusCommand
     * and returns an UpdateStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STATUS);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateStatusCommand.MESSAGE_USAGE), pe);
        }

        UpdateStatusCommand.StatusDescriptor statusDescriptor = new UpdateStatusCommand.StatusDescriptor();
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            statusDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }

        if (!statusDescriptor.isStatusEdited()) {
            throw new ParseException(UpdateStatusCommand.MESSAGE_NOT_EDITED);
        }

        return new UpdateStatusCommand(index, statusDescriptor);
    }

}
