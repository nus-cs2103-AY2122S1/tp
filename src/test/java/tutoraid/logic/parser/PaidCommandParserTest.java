package tutoraid.logic.parser;

import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tutoraid.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.PaidCommand;

public class PaidCommandParserTest {

    private PaidCommandParser parser = new PaidCommandParser();

    @Test
    public void parse_validArgs_returnsPaidCommand() {
        assertParseSuccess(parser, "1", new PaidCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PaidCommand.MESSAGE_USAGE));
    }
}
