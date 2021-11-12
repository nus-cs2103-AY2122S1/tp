package seedu.anilist.logic.parser;

import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.commons.core.Messages.MESSAGE_OUT_OF_RANGE_INDEX;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.DeleteCommand;
import seedu.anilist.logic.parser.exceptions.IntegerOutOfRangeException;
import seedu.anilist.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    private static final String MESSAGE_INVALID_COMMAND_DELETE = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteCommand.MESSAGE_USAGE);

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (IntegerOutOfRangeException e) {
            throw new ParseException(MESSAGE_OUT_OF_RANGE_INDEX);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_DELETE, pe);
        }
    }

}
