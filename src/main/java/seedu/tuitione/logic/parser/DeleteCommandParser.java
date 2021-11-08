package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.commons.util.StringUtil;
import seedu.tuitione.logic.commands.DeleteCommand;
import seedu.tuitione.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        if (!StringUtil.isAStringedNumber(args)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(args);
        return new DeleteCommand(index);
    }

}
