package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowCommand;

public class ShowCommandParserTest {

    private ShowCommandParser parser = new ShowCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "x/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleValidPrefixes_returnsShowCommandWithLastPrefix() {

        ShowCommand expectedShowCommand = new ShowCommand(CliSyntax.PREFIX_EMPLOYMENT_TYPE);
        assertParseSuccess(parser, "n/ r/ et/", expectedShowCommand);

        // multiple whitespaces between prefixes
        assertParseSuccess(parser, "n/   r/    et/", expectedShowCommand);

    }
}
