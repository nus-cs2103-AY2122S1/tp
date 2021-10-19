package seedu.tracker.logic.parser;

import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.tracker.logic.commands.UntakeCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the UntakeCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class UntakeCommandParserTest {

    private UntakeCommandParser parser = new UntakeCommandParser();

    @Test
    public void parse_validArgs_returnsUntakeCommand() {
        assertParseSuccess(parser, "1", new UntakeCommand(INDEX_FIRST_MODULE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntakeCommand.MESSAGE_USAGE));
    }
}
