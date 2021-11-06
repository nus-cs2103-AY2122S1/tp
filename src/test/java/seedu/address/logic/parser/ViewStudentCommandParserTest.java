package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewStudentCommand;

class ViewStudentCommandParserTest {
    private ViewStudentCommandParser parser = new ViewStudentCommandParser();

    @Test
    public void parse_validArgs_returnsViewStudentCommand() {
        assertParseSuccess(parser, "1", new ViewStudentCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(
                MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + MESSAGE_INVALID_COMMAND_FORMAT,
                ViewStudentCommand.MESSAGE_USAGE));
    }
}
