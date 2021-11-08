package seedu.address.logic.parser.teacher;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.teacher.DeleteTeacherCommand;

/**
 * Similar to deleteStudentCommand,
 * as we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteTeacherCommand code.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteTeacherCommandParserTest {
    private final DeleteTeacherCommandParser parser = new DeleteTeacherCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteTeacherCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteTeacherCommand.MESSAGE_USAGE));
    }
}
