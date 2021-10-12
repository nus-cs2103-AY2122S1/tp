package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMultipleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteMultipleCommandParser implements Parser<DeleteMultipleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMultipleCommand
     * and returns a DeleteMultipleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMultipleCommand parse(String args) throws ParseException {
        try {
            int indexOfDash = ParserUtil.getIndexOfSubstring(args, "-");
            Index indexStart = ParserUtil.parseIndex(args.substring(0, indexOfDash));
            Index indexEnd = ParserUtil.parseIndex(args.substring(indexOfDash + 1));
            return new DeleteMultipleCommand(indexStart, indexEnd);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMultipleCommand.MESSAGE_USAGE), pe);
        }
    }
}
