package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.DeleteCommand;
import seedu.fast.logic.parser.exceptions.ParseException;

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
        String[] multipleIndexes = args.trim().split(" ");
        Index[] result = checkAndParseArgs(multipleIndexes);
        return new DeleteCommand(result);
    }

    private Index[] checkAndParseArgs(String[] args) throws ParseException {
        Index[] result = new Index[args.length];
        int count = 0;

        for (String indexString : args) {
            try {
                Index index = ParserUtil.parseIndex(indexString);
                result[count++] = index;
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
            }
        }

        return result;
    }
}
