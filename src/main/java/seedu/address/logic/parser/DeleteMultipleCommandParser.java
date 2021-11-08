package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.DeleteMultipleCommand.INDEX_SPLITTER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMultipleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteMultipleCommand} object.
 */
public class DeleteMultipleCommandParser implements Parser<DeleteMultipleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMultipleCommand
     * and returns a DeleteMultipleCommand object for execution.
     *
     * @param args user input.
     * @return {@code DeleteMultipleCommand} which deletes the contact at the specific indexes.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMultipleCommand parse(String args) throws ParseException {
        try {
            int indexOfDash = ParserUtil.getIndexOfSubstring(args, INDEX_SPLITTER);
            Index indexStart = ParserUtil.parseIndex(args.substring(0, indexOfDash));
            Index indexEnd = ParserUtil.parseIndex(args.substring(indexOfDash + 1));
            validateIndexes(indexStart, indexEnd);
            return new DeleteMultipleCommand(indexStart, indexEnd);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, pe.getMessage()), pe);
        }
    }

    private void validateIndexes(Index indexStart, Index indexEnd) throws ParseException {
        if (!DeleteMultipleCommand.areValidIndexes(indexStart, indexEnd)) {
            throw new ParseException(DeleteMultipleCommand.MESSAGE_CONSTRAINTS);
        }
    }
}
