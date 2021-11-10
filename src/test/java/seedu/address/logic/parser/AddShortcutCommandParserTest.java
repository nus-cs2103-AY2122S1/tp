package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddShortcutCommand;

public class AddShortcutCommandParserTest {
    private static final String COMMAND_STRING = "list";
    private static final String KEY = "k";

    private AddShortcutCommandParser parser = new AddShortcutCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        assertParseSuccess(parser, KEY + " c/" + COMMAND_STRING, new AddShortcutCommand(KEY, COMMAND_STRING));
    }

    @Test
    public void parse_emptyEntry_failure() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noCommand_failure() {
        assertParseFailure(parser, KEY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noKey_failure() {
        assertParseFailure(parser, " c/" + COMMAND_STRING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_blankCommand_failure() {
        assertParseFailure(parser, KEY + " c/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
    }
}
