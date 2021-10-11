package tutoraid.logic.parser;

import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.DeleteProgressCommand;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.commons.core.Messages;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteProgressCommandParser implements Parser<DeleteProgressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProgressCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteProgressCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteProgressCommand.MESSAGE_USAGE), pe);
        }
    }

}
