package manageme.logic.parser.module;

import static manageme.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageme.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageme.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static manageme.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import manageme.logic.commands.module.DeleteModuleCommand;

public class DeleteModuleCommandParserTest {
    private DeleteModuleCommandParser parser = new DeleteModuleCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteModuleCommand() {
        assertParseSuccess(parser, "1", new DeleteModuleCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteModuleCommand.MESSAGE_USAGE));
    }
}
