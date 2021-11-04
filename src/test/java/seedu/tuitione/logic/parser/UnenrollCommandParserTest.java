package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_LESSON;

import org.junit.jupiter.api.Test;

import seedu.tuitione.logic.commands.UnenrollCommand;

public class UnenrollCommandParserTest {
    private UnenrollCommandParser parser = new UnenrollCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        UnenrollCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsUnenrollCommand() {
        assertParseSuccess(parser, "1 l/2",
                new UnenrollCommand(INDEX_FIRST_STUDENT, INDEX_SECOND_LESSON));
        assertParseSuccess(parser, "1 l/       1",
                new UnenrollCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_LESSON));
        assertParseSuccess(parser, "1     l/1",
                new UnenrollCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_LESSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnenrollCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnenrollCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 l/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnenrollCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 l/0", MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "0 l/1", MESSAGE_INVALID_INDEX);
    }
}
