package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand;

class DeleteTaskCommandParserTest {

    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    void parse_validArgs_returnsDeleteTaskCommand() {
        assertParseSuccess(parser, "1 ti/2", new DeleteTaskCommand(INDEX_FIRST_PERSON,
                Index.fromOneBased(2)));
    }

    @Test
    void parse_invalidArgs_throwParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteTaskCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1ti/2", expectedMessage);
    }
}
