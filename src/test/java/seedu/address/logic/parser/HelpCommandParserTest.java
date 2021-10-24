package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.HelpCommand;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_noPreamble_success() {
        String userInput = "";
        HelpCommand expectedCommand = new HelpCommand("");
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validPreamble_success() {
        String userInput = AddCommand.COMMAND_WORD;
        HelpCommand expectedCommand = new HelpCommand(AddCommand.COMMAND_WORD);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
