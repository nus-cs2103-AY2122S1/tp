package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeallocateMemberCommand;

public class DeallocateMemberCommandParserTest {
    private DeallocateMemberCommandParser parser = new DeallocateMemberCommandParser();

    @Test
    public void parse_emptyArgs_exceptionThrown() {
        assertParseFailure(parser, "  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeallocateMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, " 1   1 1",
                new DeallocateMemberCommand(INDEX_FIRST, INDEX_FIRST, DayOfWeek.MONDAY));

        assertParseSuccess(parser, "1 1 1",
                new DeallocateMemberCommand(INDEX_FIRST, INDEX_FIRST, DayOfWeek.MONDAY));
    }

    @Test
    public void parse_someFieldsMissing_failure() {
        assertParseFailure(parser, "1 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeallocateMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, "1 +1 5", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "1 0 5", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "1 1 0", ParserUtil.MESSAGE_INVALID_DAY);
    }
}
