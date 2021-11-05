package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOMING_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOMING_WEEK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NORMAL_LIST;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {
    private final ListCommandParser parser = new ListCommandParser();
    private final String expectedFailureMessage =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);

    @Test
    public void parser_noFieldPresent_failure() {
        // no prefix present
        assertParseFailure(parser, "", expectedFailureMessage);
        assertParseFailure(parser, "abc", expectedFailureMessage);

        // other invalid prefix present
        assertParseFailure(parser, " a/", expectedFailureMessage);
        assertParseFailure(parser, " v/2021-01-01 12:00", expectedFailureMessage);
    }

    @Test
    public void parser_invalidFieldsPresent_failure() {
        // invalid value although correct flag
        assertParseFailure(parser, " w/random", expectedFailureMessage);
        assertParseFailure(
                parser, String.format(" %s%s", PREFIX_INCOMING_MONTH, PREFIX_NORMAL_LIST), expectedFailureMessage);

        // multiple flags
        assertParseFailure(
                parser, String.format(" %s %s", PREFIX_INCOMING_MONTH, PREFIX_NORMAL_LIST), expectedFailureMessage);
    }

    @Test
    public void parser_oneValidField_success() {
        ListCommand listCommandNextMonth = new ListCommand(true, true);
        ListCommand listCommandNextWeek = new ListCommand(true, false);
        ListCommand listCommandAll = new ListCommand();

        assertParseSuccess(parser, String.format(" %s", PREFIX_NORMAL_LIST), listCommandAll);
        assertParseSuccess(parser, String.format(" %s", PREFIX_INCOMING_MONTH), listCommandNextMonth);
        assertParseSuccess(parser, String.format(" %s", PREFIX_INCOMING_WEEK), listCommandNextWeek);

        // with extra strings in front -> accept
        assertParseSuccess(parser, String.format(" v/ %s", PREFIX_INCOMING_MONTH), listCommandNextMonth);
        assertParseSuccess(parser, String.format(" p/12345678 %s", PREFIX_NORMAL_LIST), listCommandAll);
        assertParseSuccess(parser, String.format(" abc %s", PREFIX_INCOMING_WEEK), listCommandNextWeek);

        // with trailing whitespaces -> accept
        assertParseSuccess(parser, String.format(" %s       ", PREFIX_INCOMING_WEEK), listCommandNextWeek);
    }
}
