package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.commands.CommandTestUtil.KEYWORD_DESC_C;
import static safeforhall.logic.commands.CommandTestUtil.KEYWORD_DESC_F;
import static safeforhall.logic.commands.CommandTestUtil.KEYWORD_DESC_LC;
import static safeforhall.logic.commands.CommandTestUtil.KEYWORD_DESC_LF;
import static safeforhall.logic.commands.CommandTestUtil.LAST_DATE1_DESC_OCT;
import static safeforhall.logic.commands.CommandTestUtil.LAST_DATE2_DESC_OCT;
import static safeforhall.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.DeadlineCommand;
import safeforhall.model.person.LastDate;

public class DeadlineCommandParserTest {
    private final DeadlineCommandParser parser = new DeadlineCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "      ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeadlineCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        // no leading and trailing whitespaces
        DeadlineCommand expectedDeadlineCommand = new DeadlineCommand("f",
                new LastDate("10-10-2021"), new LastDate("15-10-2021"));
        assertParseSuccess(parser, KEYWORD_DESC_F + LAST_DATE1_DESC_OCT + LAST_DATE2_DESC_OCT,
                expectedDeadlineCommand);

        expectedDeadlineCommand = new DeadlineCommand("c", new LastDate("10-10-2021"),
                new LastDate("15-10-2021"));
        assertParseSuccess(parser, KEYWORD_DESC_C + LAST_DATE1_DESC_OCT + LAST_DATE2_DESC_OCT,
                expectedDeadlineCommand);

        expectedDeadlineCommand = new DeadlineCommand("f", new LastDate("10-10-2021"),
                new LastDate("15-10-2021"));
        assertParseSuccess(parser, KEYWORD_DESC_F + LAST_DATE1_DESC_OCT
                + LAST_DATE2_DESC_OCT, expectedDeadlineCommand);

        expectedDeadlineCommand = new DeadlineCommand("c", new LastDate("10-10-2021"),
                new LastDate("15-10-2021"));
        assertParseSuccess(parser, KEYWORD_DESC_C + LAST_DATE1_DESC_OCT
                + LAST_DATE2_DESC_OCT, expectedDeadlineCommand);

        expectedDeadlineCommand = new DeadlineCommand("lf", new LastDate("10-10-2021"));
        assertParseSuccess(parser, KEYWORD_DESC_LF + LAST_DATE1_DESC_OCT, expectedDeadlineCommand);

        expectedDeadlineCommand = new DeadlineCommand("lc", new LastDate("10-10-2021"));
        assertParseSuccess(parser, KEYWORD_DESC_LC + LAST_DATE1_DESC_OCT, expectedDeadlineCommand);

        // multiple whitespaces between keywords
        expectedDeadlineCommand = new DeadlineCommand("f", new LastDate("10-10-2021"),
                new LastDate("15-10-2021"));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + KEYWORD_DESC_F
                + LAST_DATE1_DESC_OCT + LAST_DATE2_DESC_OCT, expectedDeadlineCommand);

        expectedDeadlineCommand = new DeadlineCommand("c", new LastDate("10-10-2021"),
                new LastDate("15-10-2021"));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + KEYWORD_DESC_C
                + LAST_DATE1_DESC_OCT + LAST_DATE2_DESC_OCT, expectedDeadlineCommand);

        expectedDeadlineCommand = new DeadlineCommand("f", new LastDate("10-10-2021"),
                new LastDate("15-10-2021"));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + KEYWORD_DESC_F + LAST_DATE1_DESC_OCT
                + LAST_DATE2_DESC_OCT, expectedDeadlineCommand);

        expectedDeadlineCommand = new DeadlineCommand("c", new LastDate("10-10-2021"),
                new LastDate("15-10-2021"));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + KEYWORD_DESC_C + LAST_DATE1_DESC_OCT
                + LAST_DATE2_DESC_OCT, expectedDeadlineCommand);

        expectedDeadlineCommand = new DeadlineCommand("lf", new LastDate("10-10-2021"));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + KEYWORD_DESC_LF
                + LAST_DATE1_DESC_OCT, expectedDeadlineCommand);

        expectedDeadlineCommand = new DeadlineCommand("lc", new LastDate("10-10-2021"));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + KEYWORD_DESC_LC
                + LAST_DATE1_DESC_OCT, expectedDeadlineCommand);
    }
}
