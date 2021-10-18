package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.DeleteLessonCommand;
import seedu.tuitione.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteLessonCommandParser implements Parser<DeleteLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteLessonCommand
     * and returns a DeleteLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteLessonCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteLessonCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLessonCommand.MESSAGE_USAGE), pe);
        }
    }

}
