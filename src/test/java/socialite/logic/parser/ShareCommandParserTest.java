package socialite.logic.parser;

import org.junit.jupiter.api.Test;

import socialite.commons.core.Messages;
import socialite.logic.commands.ShareCommand;
import socialite.testutil.TypicalIndexes;

public class ShareCommandParserTest {

    private ShareCommandParser parser = new ShareCommandParser();

    @Test
    public void parse_validArgs_returnsShareCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "1", new ShareCommand(TypicalIndexes.INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_returnsShareCommand() {
        CommandParserTestUtil.assertParseFailure(
                parser, "a", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ShareCommand.MESSAGE_HELP_GUIDE)
        );
    }
}
