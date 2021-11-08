package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.ListCommand;


public class ListCommandParserTest {
    public static final String EMPTY_ARGUMENT = "list";
    public static final String INVALID_ARGUMENT = EMPTY_ARGUMENT + " abc/";

    // todo: implement a test with successful output
    public static final String VALID_ARGUMENT = EMPTY_ARGUMENT + " note/";
    private final ListCommandParser parser = new ListCommandParser();

    @Test
    void parse_withEmptyArgs_throwParseException() {
        assertParseFailure(parser, EMPTY_ARGUMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_withInvalidArgs_throwParseException() {
        assertParseFailure(parser, INVALID_ARGUMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}
