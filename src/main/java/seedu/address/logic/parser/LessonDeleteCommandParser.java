package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.INDEX_ARGS_COUNT_STUDENT_LESSON;
import static seedu.address.logic.parser.ParserUtil.LESSON_INDEX_ZERO_BASED;
import static seedu.address.logic.parser.ParserUtil.STUDENT_INDEX_ZERO_BASED;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LessonDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class LessonDeleteCommandParser implements Parser<LessonDeleteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LessonDeleteCommand
     * and returns a LessonDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LessonDeleteCommand parse(String args) throws ParseException {
        String[] preamble = ParserUtil.parsePreamble(args);
        if (preamble.length != INDEX_ARGS_COUNT_STUDENT_LESSON) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonDeleteCommand.MESSAGE_USAGE));
        }
        Index studentIndex = ParserUtil.parseStudentIndex(preamble[STUDENT_INDEX_ZERO_BASED]);
        Index lessonIndex = ParserUtil.parseLessonIndex(preamble[LESSON_INDEX_ZERO_BASED]);

        return new LessonDeleteCommand(studentIndex, lessonIndex);
    }
}
