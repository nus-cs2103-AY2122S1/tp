package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.position.DeletePositionCommand;

class DeletePositionCommandParserTest {

    private DeletePositionCommandParser parser = new DeletePositionCommandParser();

    @Test
    public void parse_validArgs_returnsDeletePositionCommand() {
        assertParseSuccess(parser, "1", new DeletePositionCommand(INDEX_FIRST_POSITION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ParserUtil.MESSAGE_INVALID_INDEX) + DeletePositionCommand.MESSAGE_USAGE);
    }
}
