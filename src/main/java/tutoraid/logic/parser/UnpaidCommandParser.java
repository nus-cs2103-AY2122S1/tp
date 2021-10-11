package tutoraid.logic.parser;

import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.UnpaidCommand;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.commons.core.Messages;

/**
 * Parses input arguments and creates a new UnpaidCommand object
 */
public class UnpaidCommandParser implements Parser<UnpaidCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of UnpaidCommand
     * and returns a UnpaidCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnpaidCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnpaidCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UnpaidCommand.MESSAGE_USAGE), pe);
        }
    }
}
