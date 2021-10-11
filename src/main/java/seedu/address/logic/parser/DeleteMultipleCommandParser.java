package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMultipleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteMultipleCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMultipleCommand
     * and returns a DeleteMultipleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMultipleCommand parse(String args) throws ParseException {
        try {
            String[] indexes = args.split("-");
            Index indexStart = ParserUtil.parseIndex(indexes[0]);
            Index indexEnd = ParserUtil.parseIndex(indexes[1]);
            return new DeleteMultipleCommand(indexStart, indexEnd);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMultipleCommand.MESSAGE_USAGE), pe);
        }
    }
}
