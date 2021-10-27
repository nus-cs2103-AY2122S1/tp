package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowCommand;

public class ShowCommandParserTest {

    private ShowCommandParser parser = new ShowCommandParser();

    @Test
    public void parse_emptyInput_throwsParseException() {

        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_blankInput_throwsParseException() {

        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {

        assertParseFailure(parser, "x/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseOneValidPrefix_returnsShowCommandWithPrefix() {

        ShowCommand expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_NAME);
        assertParseSuccess(parser, "n/", expectedShowCommand);
        // whitespaces before and after prefix
        assertParseSuccess(parser, "  n/   ", expectedShowCommand);

        expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_EMAIL);
        assertParseSuccess(parser, "e/", expectedShowCommand);
        // whitespaces before and after prefix
        assertParseSuccess(parser, "  e/   ", expectedShowCommand);

        expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_PHONE);
        assertParseSuccess(parser, "p/", expectedShowCommand);
        // whitespaces before and after prefix
        assertParseSuccess(parser, "  p/   ", expectedShowCommand);

        expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_ROLE);
        assertParseSuccess(parser, "r/", expectedShowCommand);
        // whitespaces before and after prefix
        assertParseSuccess(parser, "  r/   ", expectedShowCommand);

        expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_EMPLOYMENT_TYPE);
        assertParseSuccess(parser, "et/", expectedShowCommand);
        // whitespaces before and after prefix
        assertParseSuccess(parser, "  et/   ", expectedShowCommand);

        expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_EXPECTED_SALARY);
        assertParseSuccess(parser, "s/", expectedShowCommand);
        // whitespaces before and after prefix
        assertParseSuccess(parser, "  s/   ", expectedShowCommand);

        expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_LEVEL_OF_EDUCATION);
        assertParseSuccess(parser, "l/", expectedShowCommand);
        // whitespaces before and after prefix
        assertParseSuccess(parser, "  l/   ", expectedShowCommand);

        expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_EXPERIENCE);
        assertParseSuccess(parser, "y/", expectedShowCommand);
        // whitespaces before and after prefix
        assertParseSuccess(parser, "  y/   ", expectedShowCommand);

        expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_TAG);
        assertParseSuccess(parser, "t/", expectedShowCommand);
        // whitespaces before and after prefix
        assertParseSuccess(parser, "  t/   ", expectedShowCommand);

        expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_INTERVIEW);
        assertParseSuccess(parser, "i/", expectedShowCommand);
        // whitespaces before and after prefix
        assertParseSuccess(parser, "  i/   ", expectedShowCommand);

    }

    @Test
    public void parse_multipleValidPrefixes_returnsShowCommandWithFirstPrefix() {

        ShowCommand expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_NAME);
        assertParseSuccess(parser, "n/ r/ et/", expectedShowCommand);

        // multiple whitespaces between prefixes
        assertParseSuccess(parser, "n/   r/    et/", expectedShowCommand);

        // multiple whitespaces before and after prefixes
        assertParseSuccess(parser, "    n/ r/ et/    ", expectedShowCommand);

        // Prefixes with inputs
        assertParseSuccess(parser, "n/J@hn r/Softw@re 3ngin33r$$ et/25/7", expectedShowCommand);
    }

}
