package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CalledCommand;

public class CalledCommandParserTest {
    private CalledCommandParser parser = new CalledCommandParser();

    @Test
    public void parse_validArgs_returnsCalledCommand() {
        assertParseSuccess(parser, "2", new CalledCommand(INDEX_SECOND_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "*", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalledCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "c", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalledCommand.MESSAGE_USAGE));
    }
}
