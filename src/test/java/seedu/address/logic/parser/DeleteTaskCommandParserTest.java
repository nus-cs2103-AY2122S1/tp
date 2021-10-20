package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand;

class DeleteTaskCommandParserTest {

    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    void parse_validArgs_returnsDeleteTaskCommand() {
        List<Index> indexList = new ArrayList<>();
        Index taskIndex = Index.fromOneBased(2);
        indexList.add(taskIndex);
        assertParseSuccess(parser, "1 ti/2", new DeleteTaskCommand(INDEX_FIRST_PERSON,
                indexList));
    }

    @Test
    void parse_invalidArgs_throwParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteTaskCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1ti/2", expectedMessage);
    }
}
