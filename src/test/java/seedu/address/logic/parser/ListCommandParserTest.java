package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.display.DisplayMode;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);

    @Test
    public void parse_noArgs_returnsListCommand() {
        // asking help for list command
        ListCommand expectedListCommand = new ListCommand(DisplayMode.DISPLAY_INVENTORY);

        assertParseSuccess(parser,"", expectedListCommand);
    }

    @Test
    public void parse_orderKeyword_returnsListCommand() {
        // asking help for list command
        ListCommand expectedListCommand = new ListCommand(DisplayMode.DISPLAY_OPEN_ORDER);

        assertParseSuccess(parser, ListCommand.ORDER_KEYWORD, expectedListCommand);
    }

    @Test
    public void parse_transactionKeyword_returnsListCommand() {
        // asking help for list command
        ListCommand expectedListCommand = new ListCommand(DisplayMode.DISPLAY_TRANSACTIONS);

        assertParseSuccess(parser, ListCommand.TRANSACTIONS_KEYWORD, expectedListCommand);
    }

    @Test
    public void parse_otherArgs_failure() {
        // asking help for list command
        assertParseFailure(parser, ListCommand.COMMAND_WORD + "extra words", MESSAGE_INVALID_FORMAT);
    }
}
