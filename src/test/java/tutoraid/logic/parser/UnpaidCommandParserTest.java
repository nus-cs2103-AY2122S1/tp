package tutoraid.logic.parser;

import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.UnpaidCommand;

public class UnpaidCommandParserTest {

    private UnpaidCommandParser parser = new UnpaidCommandParser();

    @Test
    public void parse_validArgs_returnsUnpaidCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "1", new UnpaidCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnpaidCommand.MESSAGE_USAGE));
    }
}
