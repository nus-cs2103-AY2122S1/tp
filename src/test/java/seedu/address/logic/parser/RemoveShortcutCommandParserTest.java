package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveShortcutCommand;

public class RemoveShortcutCommandParserTest {

    private RemoveShortcutCommandParser parser = new RemoveShortcutCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", RemoveShortcutCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_validShortcutArgs_returnsRemoveShortcutCommand() {
        // no leading and trailing whitespaces
        RemoveShortcutCommand expectedRemoveShortcutCommand = new RemoveShortcutCommand("a");
        assertParseSuccess(parser, "a", expectedRemoveShortcutCommand);
    }

}
