package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

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
        assertParseSuccess(parser, "1 l/Science-P5-Wed-1230",
                new UnenrollCommand(INDEX_FIRST_STUDENT, "Science-P5-Wed-1230"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnenrollCommand.MESSAGE_USAGE));
    }
}
