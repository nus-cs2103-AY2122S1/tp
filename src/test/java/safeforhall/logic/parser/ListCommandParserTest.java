package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.commands.CommandTestUtil.KEYWORD_DESC_C;
import static safeforhall.logic.commands.CommandTestUtil.KEYWORD_DESC_F;
import static safeforhall.logic.commands.CommandTestUtil.LAST_DATE1_DESC_OCT;
import static safeforhall.logic.commands.CommandTestUtil.LAST_DATE2_DESC_OCT;
import static safeforhall.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.ListCommand;
import safeforhall.model.person.LastDate;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "      ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        // no leading and trailing whitespaces
        ListCommand expectedListCommand = new ListCommand("f", new LastDate("10-10-2021"));
        assertParseSuccess(parser, KEYWORD_DESC_F + LAST_DATE1_DESC_OCT, expectedListCommand);

        expectedListCommand = new ListCommand("c", new LastDate("10-10-2021"));
        assertParseSuccess(parser, KEYWORD_DESC_C + LAST_DATE1_DESC_OCT, expectedListCommand);

        expectedListCommand = new ListCommand("f", new LastDate("10-10-2021"), new LastDate("15-10-2021"));
        assertParseSuccess(parser, KEYWORD_DESC_F + LAST_DATE1_DESC_OCT
                + LAST_DATE2_DESC_OCT, expectedListCommand);

        expectedListCommand = new ListCommand("c", new LastDate("10-10-2021"), new LastDate("15-10-2021"));
        assertParseSuccess(parser, KEYWORD_DESC_C + LAST_DATE1_DESC_OCT
                + LAST_DATE2_DESC_OCT, expectedListCommand);

        // multiple whitespaces between keywords
        expectedListCommand = new ListCommand("f", new LastDate("10-10-2021"));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + KEYWORD_DESC_F
                + LAST_DATE1_DESC_OCT, expectedListCommand);

        expectedListCommand = new ListCommand("c", new LastDate("10-10-2021"));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + KEYWORD_DESC_C
                + LAST_DATE1_DESC_OCT, expectedListCommand);

        expectedListCommand = new ListCommand("f", new LastDate("10-10-2021"), new LastDate("15-10-2021"));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + KEYWORD_DESC_F + LAST_DATE1_DESC_OCT
                + LAST_DATE2_DESC_OCT, expectedListCommand);

        expectedListCommand = new ListCommand("c", new LastDate("10-10-2021"), new LastDate("15-10-2021"));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + KEYWORD_DESC_C + LAST_DATE1_DESC_OCT
                + LAST_DATE2_DESC_OCT, expectedListCommand);
    }
}
