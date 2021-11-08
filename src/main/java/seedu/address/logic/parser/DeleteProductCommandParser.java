package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteProductCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteProductCommand object
 */
public class DeleteProductCommandParser implements Parser<DeleteProductCommand> {
    private static final Logger logger = LogsCenter.getLogger("DeleteProductParserLogger");

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteProductCommand
     * and returns a DeleteProductCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProductCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            logger.log(Level.INFO, String.format("Deleting product %1$s", index));

            return new DeleteProductCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProductCommand.MESSAGE_USAGE), pe);
        }
    }
}
