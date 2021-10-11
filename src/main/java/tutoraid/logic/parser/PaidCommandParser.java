package tutoraid.logic.parser;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.PaidCommand;
import tutoraid.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new PaidCommand object
 */
public class PaidCommandParser implements Parser<PaidCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of PaidCommand
     * and returns a PaidCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PaidCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new PaidCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, PaidCommand.MESSAGE_USAGE), pe);
        }
    }
}
