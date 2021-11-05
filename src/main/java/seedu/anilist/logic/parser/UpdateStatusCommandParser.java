package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.commons.core.Messages.MESSAGE_OUT_OF_RANGE_INDEX;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Optional;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.UpdateStatusCommand;
import seedu.anilist.logic.parser.exceptions.IntegerOutOfRangeException;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Status;


/**
 * Parses input arguments and creates a new UpdateStatusCommand object
 */
public class UpdateStatusCommandParser implements Parser<UpdateStatusCommand> {
    private static final String MESSAGE_INVALID_COMMAND_STATUS = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UpdateStatusCommand.MESSAGE_USAGE);

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateStatusCommand
     * and returns an UpdateStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap;

        try {
            argMultimap = ParserUtil.tokenizeWithCheck(args, UpdateStatusCommand.REQUIRES_PREAMBLE,
                    UpdateStatusCommand.REQUIRED_PREFIXES, UpdateStatusCommand.OPTIONAL_PREFIXES);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_STATUS);
        }
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IntegerOutOfRangeException e) {
            throw new ParseException(MESSAGE_OUT_OF_RANGE_INDEX);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_STATUS, pe);
        }
        UpdateStatusCommand.StatusDescriptor statusDescriptor = new UpdateStatusCommand.StatusDescriptor();
        Optional<String> statusParam = argMultimap.getValue(PREFIX_STATUS);
        Status status = ParserUtil.parseStatus(statusParam.get());

        statusDescriptor.setStatus(status);

        if (!statusDescriptor.isStatusEdited()) {
            throw new ParseException(UpdateStatusCommand.MESSAGE_NOT_EDITED);
        }

        return new UpdateStatusCommand(index, statusDescriptor);
    }

}
