package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOMING_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOMING_WEEK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {
    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parser_noFieldPresent_success() {
        ListCommand listCommandAll = new ListCommand(false, false);

        // no prefix means list all
        assertParseSuccess(parser, "", listCommandAll);
    }

    @Test
    public void parser_oneValidField_success() {
        ListCommand listCommandNextMonth = new ListCommand(true, true);
        ListCommand listCommandNextWeek = new ListCommand(true, false);

        assertParseSuccess(parser, String.format(" %s", PREFIX_INCOMING_MONTH), listCommandNextMonth);
        assertParseSuccess(parser, String.format(" %s", PREFIX_INCOMING_WEEK), listCommandNextWeek);
    }

    @Test
    public void parser_bothFieldsPresent_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);

        // both flag -> invalid
        assertParseFailure(parser,
                String.format(" %s %s", PREFIX_INCOMING_MONTH, PREFIX_INCOMING_WEEK), expectedMessage);
    }

    @Test
    public void parser_extraArgs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);

        String argsTrailingExtra = String.format(" %s123", PREFIX_INCOMING_WEEK);
        String argsPrecedingExtra = String.format("abc %s", PREFIX_INCOMING_WEEK);
        assertParseFailure(parser, argsTrailingExtra, expectedMessage);
        assertParseFailure(parser, argsPrecedingExtra, expectedMessage);
    }
}
