package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ROOM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CheckOutCommand;

public class CheckOutCommandParserTest {

    private CheckOutCommandParser parser = new CheckOutCommandParser();

    @Test
    public void parse_validArgs_returnsCheckOutCommand() {
        assertParseSuccess(parser, "1", new CheckOutCommand(INDEX_FIRST_ROOM));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckOutCommand.MESSAGE_USAGE));
    }

}
