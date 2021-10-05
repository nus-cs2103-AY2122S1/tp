package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteProductCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DeleteProductCommand object
 */
public class DeleteProductCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteProductCommand
     * and returns a DeleteProductCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProductCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteProductCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProductCommand.MESSAGE_USAGE), pe);
        }
    }
}
