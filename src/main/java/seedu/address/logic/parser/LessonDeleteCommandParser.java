package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LessonDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class LessonDeleteCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the LessonDeleteCommand
     * and returns a LessonDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LessonDeleteCommand parse(String args) throws ParseException {
        try {
            // There will be 2 index arguments
            // 1st is person, 2nd is lesson
            String[] indices = args.trim().split(" ", 2);
            Index index = ParserUtil.parseIndex(indices[0]);
            Index lessonIndex = ParserUtil.parseIndex(indices[1]);
            return new LessonDeleteCommand(index, lessonIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonDeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
