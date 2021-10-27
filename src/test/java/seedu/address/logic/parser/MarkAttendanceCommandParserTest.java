package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkAttendanceCommand;

class MarkAttendanceCommandParserTest {
    private MarkAttendanceCommandParser parser = new MarkAttendanceCommandParser();

    @Test
    public void parse_validArgs_returnsMarkAttendanceCommand() {
        assertParseSuccess(parser, "1 3", new MarkAttendanceCommand(Arrays.asList(INDEX_FIRST,
                INDEX_THIRD)));

        assertParseSuccess(parser, "1 \n   3", new MarkAttendanceCommand(Arrays.asList(INDEX_FIRST,
                INDEX_THIRD)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-1", MESSAGE_INVALID_INDEX);
    }

}
