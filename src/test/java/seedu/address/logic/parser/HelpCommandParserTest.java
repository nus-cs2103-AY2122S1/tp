package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_validAddArgs_returnsHelpCommand() {
        // asking help for add command
        HelpCommand expectedHelpCommand =
                new HelpCommand("add found");
        assertParseSuccess(parser, "add found", expectedHelpCommand);
    }
}
