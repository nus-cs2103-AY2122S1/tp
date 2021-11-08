package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteBlockedSlotCommand;

public class DeleteBlockedSlotParserTest {

    private DeleteBlockedSlotCommandParser parser = new DeleteBlockedSlotCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteBlockedSlotCommand() {
        assertParseSuccess(parser, "1", new DeleteBlockedSlotCommand(INDEX_FIRST_EVENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteBlockedSlotCommand.MESSAGE_USAGE));
    }
}
