package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RenameEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RenameEventCommand object
 */
public class RenameEventCommandParser implements Parser<RenameEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RenameEventCommand
     * and returns a RenameEventCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public RenameEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_NAME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RenameEventCommand.MESSAGE_USAGE), pe);
        }

        RenameEventCommand.RenameEventDescriptor renameEventDescriptor =
                new RenameEventCommand.RenameEventDescriptor();
        if (argMultiMap.getValue(PREFIX_EVENT_NAME).isPresent()) {
            renameEventDescriptor.setEventName(ParserUtil.parseEventName(argMultiMap
                    .getValue(PREFIX_EVENT_NAME)
                    .get()));
        }

        if (!renameEventDescriptor.isEventNameRenamed()) {
            throw new ParseException(RenameEventCommand.MESSAGE_NOT_RENAMED);
        }

        return new RenameEventCommand(index, renameEventDescriptor);
    }
}
