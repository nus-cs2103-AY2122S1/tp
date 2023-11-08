package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SplitCommand;

public class SplitCommandParserTest {
    private SplitCommandParser parser = new SplitCommandParser();

    @Test
    public void parse_emptyArgs_exceptionThrown() {
        assertParseFailure(parser, "      ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SplitCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSplitCommand() {
        SplitCommand expectedSplitCommand = new SplitCommand(1);
        assertParseSuccess(parser, "1", expectedSplitCommand);
        assertParseSuccess(parser, "\n  \t 1  \t ", expectedSplitCommand);
    }

    @Test
    public void parse_invalidArgs_returnsSplitCommand() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SplitCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "0", expectedMessage);
        assertParseFailure(parser, "8", expectedMessage);
        assertParseFailure(parser, "one", expectedMessage);
        assertParseFailure(parser, "seven", expectedMessage);
    }
}
