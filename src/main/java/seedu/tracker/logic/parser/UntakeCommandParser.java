package seedu.tracker.logic.parser;

import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tracker.commons.core.index.Index;
import seedu.tracker.logic.commands.DeleteCommand;
import seedu.tracker.logic.commands.UntakeCommand;
import seedu.tracker.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UntakeCommand object
 */
public class UntakeCommandParser implements Parser<UntakeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UntakeCommand
     * and returns a UntakeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UntakeCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UntakeCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntakeCommand.MESSAGE_USAGE), pe);
        }
    }

}
