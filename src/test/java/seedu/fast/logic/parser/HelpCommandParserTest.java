package seedu.fast.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.fast.logic.commands.HelpCommand;
import seedu.fast.logic.parser.HelpCommandParser;



public class HelpCommandParserTest {


    private final String validHelpArg1 = "Add";

    private final String validHelpArg2 = "priority tag";

    private final String invalidHelpArg = "incorrect";
    private final String noArg = "";


    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_withArgsHelp_success() {

        HelpCommand expectedCommand1 = new HelpCommand(validHelpArg1);
        assertParseSuccess(parser, "add", expectedCommand1);

        HelpCommand expectedCommand2 = new HelpCommand(validHelpArg2);
        assertParseSuccess(parser, "priority tag", expectedCommand1);
    }

    @Test
    public void parse_noArgsHelp_success() {
        HelpCommand expectedCommand1 = new HelpCommand("");
        assertParseSuccess(parser, noArg, expectedCommand1);
    }

    @Test
    public void parse_invalidArgsHelp_success() {
        HelpCommand expectedCommand1 = new HelpCommand("");
        assertParseSuccess(parser, invalidHelpArg, expectedCommand1);
    }

}
