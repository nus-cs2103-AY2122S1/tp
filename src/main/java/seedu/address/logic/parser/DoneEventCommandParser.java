package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DoneEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DoneEventCommand object
 */
public class DoneEventCommandParser implements Parser<DoneEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DoneEventCommand
     * and returns a DoneEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoneEventCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DoneEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneEventCommand.MESSAGE_USAGE), pe);
        }
    }

}
