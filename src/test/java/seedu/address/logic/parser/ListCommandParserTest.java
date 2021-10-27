package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand();
        assertParseSuccess(parser, "", expectedListCommand);
    }

    @Test
    public void parse_validArgsWithSpaces_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand();
        assertParseSuccess(parser, "   ", expectedListCommand);
    }

    @Test
    public void parse_invalidArgs_throwParseException() {
        assertParseFailure(parser, " apple", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}