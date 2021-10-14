package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_validListArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "list";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help list", expectedHelpCommand);
    }

    @Test
    public void parse_validAddArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "add";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help add", expectedHelpCommand);
    }

    @Test
    public void parse_validEditArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "edit";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help edit", expectedHelpCommand);
    }

    @Test
    public void parse_validDeleteArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "delete";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help delete", expectedHelpCommand);
    }

    @Test
    public void parse_validClearArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "clear";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help clear", expectedHelpCommand);
    }

    @Test
    public void parse_validFindArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "find";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help find", expectedHelpCommand);
    }

    @Test
    public void parse_validSortArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "sort";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help sort", expectedHelpCommand);
    }

    @Test
    public void parse_validExitArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "exit";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help exit", expectedHelpCommand);
    }

    @Test
    public void parse_validStartOrderArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "start order";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help sorder", expectedHelpCommand);
    }

    @Test
    public void parse_validAddToOrderArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "add to order";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help iorder", expectedHelpCommand);
    }

    @Test
    public void parse_validRemoveFromOrderArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "remove from order";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help corder", expectedHelpCommand);
    }

    @Test
    public void parse_validEndAndTransactionOrderArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "end and transaction order";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help eorder", expectedHelpCommand);
    }

    @Test
    public void parse_validEmptyArgs_returnsHelpCommand() {
        // asking help for list command
        final String message = "";
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "help", expectedHelpCommand);
    }


}
