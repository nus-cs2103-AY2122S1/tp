package dash.logic.parser.taskcommand;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.logic.commands.taskcommand.DeleteTaskCommand;
import dash.logic.parser.CommandParserTestUtil;
import dash.testutil.TypicalIndexes;

class DeleteTaskCommandParserTest {
    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTaskCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "1",
                new DeleteTaskCommand(TypicalIndexes.INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteTaskCommand.MESSAGE_USAGE));
    }

}