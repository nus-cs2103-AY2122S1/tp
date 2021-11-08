package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    private String invalidArgs1 = "a/";
    private String validArgs1 = "";
    private String validArgs2 = "T/";
    private String validArgs3 = "-r";
    private String validArgs4 = "t/ -r";

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, invalidArgs1, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        assertParseSuccess(parser, validArgs1, new SortCommand(new Prefix("n/"), false));
        assertParseSuccess(parser, validArgs2, new SortCommand(new Prefix("T/"), false));
        assertParseSuccess(parser, validArgs3, new SortCommand(new Prefix("n/"), true));
        assertParseSuccess(parser, validArgs4, new SortCommand(new Prefix("t/"), true));
    }
}
