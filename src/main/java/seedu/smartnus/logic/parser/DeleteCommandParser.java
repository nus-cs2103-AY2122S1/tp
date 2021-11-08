package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INDEX_ERROR;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.DeleteCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;

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
        try {
            String[] inputWords;
            inputWords = args.split("\\s+");
            if (inputWords.length == 3) {
                String deleteItem = inputWords[1];
                Index index = ParserUtil.parseIndex(inputWords[2]);
                if (ParserUtil.isValidListArgument(deleteItem.trim())) {
                    return new DeleteCommand(deleteItem, index);
                }
            }
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INDEX_ERROR);
        }

        throw new ParseException(
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
