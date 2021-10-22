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
        List<Index> indexList1 = new ArrayList<>();
        Index taskIndex2 = Index.fromOneBased(2);
        Index taskIndex1 = Index.fromOneBased(1);
        indexList1.add(taskIndex2);

        List<Index> indexList2 = new ArrayList<>();
        indexList2.add(taskIndex1);
        indexList2.add(taskIndex2);
        assertParseSuccess(parser, "1 -ti 2", new DeleteTaskCommand(INDEX_FIRST_PERSON,
                indexList1));
        assertParseSuccess(parser, "1 -ti 2 -ti 2 -ti 2", new DeleteTaskCommand(INDEX_FIRST_PERSON,
                indexList1));
        assertParseSuccess(parser, "1 -ti 2 -ti 1", new DeleteTaskCommand(INDEX_FIRST_PERSON,
                indexList2));
    }

    @Test
    void parse_invalidArgs_throwParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteTaskCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1ti/2", expectedMessage);
        assertParseFailure(parser, "1 ti2", expectedMessage);
        assertParseFailure(parser, "", expectedMessage);
        assertParseFailure(parser, "1 ti/", expectedMessage);
        assertParseFailure(parser, "1 ti/ew", expectedMessage);
        assertParseFailure(parser, "1 ti/1 ew", expectedMessage);
        assertParseFailure(parser, "ti/2", expectedMessage);
        assertParseFailure(parser, "ex ti/2", expectedMessage);
        assertParseFailure(parser, "1 ti/2 n/Bob", expectedMessage);
    }
}
