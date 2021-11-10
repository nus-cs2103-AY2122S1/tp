package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShortcutCommand;

public class ShortcutCommandParserTest {

    private ShortcutCommandParser parser = new ShortcutCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", ShortcutCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_validShortArgs_returnsShortcutCommand() {
        // no leading and trailing whitespaces
        ShortcutCommand expectedShortcutCommand = new ShortcutCommand("a");
        assertParseSuccess(parser, "a", expectedShortcutCommand);
    }

}
