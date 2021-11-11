package seedu.intern.logic.parser;

import static seedu.intern.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intern.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.intern.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.intern.logic.commands.ViewCommand;

public class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgNoTag_returnsViewCommand() {
        assertParseSuccess(parser, "1", new ViewCommand(INDEX_FIRST_APPLICANT, false));
    }

    @Test
    public void parse_validArgWithTag_returnsViewCommand() {
        assertParseSuccess(parser, "1 T", new ViewCommand(INDEX_FIRST_APPLICANT, true));
    }

    @Test
    public void parse_validArgsNoTagTwo_returnsViewCommand() {
        assertParseSuccess(parser, "2", new ViewCommand(INDEX_SECOND_APPLICANT, false));
    }

    @Test
    public void parse_validArgsWithTagTwo_returnsViewCommand() {
        assertParseSuccess(parser, "2 T", new ViewCommand(INDEX_SECOND_APPLICANT, true));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsTwo_throwsParseException() {
        assertParseFailure(parser, "T",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsNoInput_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsLongSpace_throwsParseException() {
        assertParseFailure(parser, "        ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_invalidArgsShortSpaceWithValidInput_throwsParseException() {
        assertParseFailure(parser, "1  T",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsLongSpaceWithValidInput_throwsParseException() {
        assertParseFailure(parser, "1      T",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
}
