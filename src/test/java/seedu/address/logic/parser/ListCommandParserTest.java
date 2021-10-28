package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListInventoryCommand;
import seedu.address.logic.commands.ListTransactionCommand;
import seedu.address.model.display.DisplayMode;

public class ListCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListInventoryCommand.MESSAGE_USAGE + "\n" + ListTransactionCommand.MESSAGE_USAGE);

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_noArgs_returnsListCommand() {
        // asking help for list command
        ListInventoryCommand expectedListCommand = new ListInventoryCommand(DisplayMode.DISPLAY_INVENTORY);
        assertParseSuccess(parser, "", expectedListCommand);
    }

    @Test
    public void parse_orderKeyword_returnsListCommand() {
        // asking help for list command
        ListInventoryCommand expectedListCommand = new ListInventoryCommand(DisplayMode.DISPLAY_OPEN_ORDER);

        assertParseSuccess(parser, ListInventoryCommand.ORDER_KEYWORD, expectedListCommand);
    }

    @Test
    public void parse_transactionKeyword_returnsListCommand() {
        // asking help for list command
        ListTransactionCommand expectedListCommand = new ListTransactionCommand("");

        assertParseSuccess(parser, ListTransactionCommand.TRANSACTIONS_KEYWORD, expectedListCommand);
    }

    @Test
    public void parse_transactionKeywordWithId_returnsListCommand() {
        // asking help for list command
        String testId = "qwerty1234";
        ListTransactionCommand expectedListCommand = new ListTransactionCommand(testId);

        assertParseSuccess(parser, ListTransactionCommand.TRANSACTIONS_KEYWORD + " " + testId, expectedListCommand);
    }

    @Test
    public void parse_otherArgs_failure() {
        // asking help for list command
        assertParseFailure(parser, ListInventoryCommand.COMMAND_WORD + "extra words", MESSAGE_INVALID_FORMAT);
    }
}
