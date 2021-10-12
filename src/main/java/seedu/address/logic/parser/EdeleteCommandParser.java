package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EdeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EdeleteCommand object
 */
public class EdeleteCommandParser implements Parser<EdeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EdeleteCommand
     * and returns a EdeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EdeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new EdeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EdeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
