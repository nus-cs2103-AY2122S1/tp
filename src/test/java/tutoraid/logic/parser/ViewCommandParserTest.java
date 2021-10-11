package tutoraid.logic.parser;

import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.ViewCommand;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "1", new ViewCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
}
