package seedu.fast.logic.parser;


import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.fast.logic.commands.HelpCommand;


public class HelpCommandParserTest {


    private final String VALID_HELP_ARG_1 = "Add";

    private final String VALID_HELP_ARG_2 = "priority tag";

    private final String INVALID_HELP_ARG = "incorrect";
    private final String EMPTY_ARG = "";


    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_withArgsHelp_success() {

        HelpCommand expectedCommand1 = new HelpCommand(VALID_HELP_ARG_1);
        assertParseSuccess(parser, "add", expectedCommand1);

        HelpCommand expectedCommand2 = new HelpCommand(VALID_HELP_ARG_2);
        assertParseSuccess(parser, "priority tag", expectedCommand1);
    }

    @Test
    public void parse_noArgsHelp_success() {
        HelpCommand expectedCommand1 = new HelpCommand("");
        assertParseSuccess(parser, EMPTY_ARG, expectedCommand1);
    }

    @Test
    public void parse_invalidArgsHelp_success() {
        HelpCommand expectedCommand1 = new HelpCommand("");
        assertParseSuccess(parser, INVALID_HELP_ARG, expectedCommand1);
    }

}
