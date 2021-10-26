package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.*;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_validListArgs_returnsHelpCommand() {
        // asking help for list command
        HelpCommand expectedHelpCommand = new HelpCommand(ListCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, "list", expectedHelpCommand);
    }

    @Test
    public void parse_validAddArgs_returnsHelpCommand() {
        // asking help for add command
        final String message = AddCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "add", expectedHelpCommand);
    }

    @Test
    public void parse_validEditArgs_returnsHelpCommand() {
        // asking help for edit command
        final String message = EditCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "edit", expectedHelpCommand);
    }

    @Test
    public void parse_validDeleteArgs_returnsHelpCommand() {
        // asking help for delete command
        final String message = DeleteCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "delete", expectedHelpCommand);
    }

    @Test
    public void parse_validClearArgs_returnsHelpCommand() {
        // asking help for clear command
        final String message = ClearCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "clear", expectedHelpCommand);
    }

    @Test
    public void parse_validFindArgs_returnsHelpCommand() {
        // asking help for find command
        final String message = FindCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "find", expectedHelpCommand);
    }

    @Test
    public void parse_validSortArgs_returnsHelpCommand() {
        // asking help for sort command
        final String message = SortCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "sort", expectedHelpCommand);
    }

    @Test
    public void parse_validExitArgs_returnsHelpCommand() {
        // asking help for exit command
        final String message = ExitCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "exit", expectedHelpCommand);
    }

    @Test
    public void parse_validStartOrderArgs_returnsHelpCommand() {
        // asking help for start order command
        final String message = StartOrderCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "sorder", expectedHelpCommand);
    }

    @Test
    public void parse_validAddToOrderArgs_returnsHelpCommand() {
        // asking help for add to command
        final String message = AddToOrderCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "iorder", expectedHelpCommand);
    }

    @Test
    public void parse_validRemoveFromOrderArgs_returnsHelpCommand() {
        // asking help for remove from order command
        final String message = RemoveFromOrderCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "corder", expectedHelpCommand);
    }

    @Test
    public void parse_validEndAndTransactionOrderArgs_returnsHelpCommand() {
        // asking help for end and transaction command
        final String message = EndAndTransactOrderCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "eorder", expectedHelpCommand);
    }

    @Test
    public void parse_validViewOrderArgs_returnsHelpCommand() {
        // asking help for view order command
        final String message = ViewOrderCommand.MESSAGE_USAGE;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "vieworder", expectedHelpCommand);
    }

    @Test
    public void parse_validEmptyArgs_success() {
        // asking help for empty command
        final String userGuide = "https://github.com/AY2122S1-CS2103-F10-2/tp/blob/master/docs/UserGuide.md";
        final String message = "\nRefer to the user guide: " + userGuide;
        HelpCommand expectedHelpCommand = new HelpCommand(message);
        assertParseSuccess(parser, "", expectedHelpCommand);
    }



}
