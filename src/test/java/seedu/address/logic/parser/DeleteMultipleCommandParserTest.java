package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.DeleteMultipleCommand.INDEX_SPLITTER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_EIGHTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_ELEVENTH_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteMultipleCommand;

public class DeleteMultipleCommandParserTest {
    private DeleteMultipleCommandParser parser = new DeleteMultipleCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "8" + INDEX_SPLITTER + "11",
                new DeleteMultipleCommand(INDEX_EIGHTH_PERSON, INDEX_ELEVENTH_PERSON));
        assertParseSuccess(parser, "8 " + INDEX_SPLITTER + " 8",
                new DeleteMultipleCommand(INDEX_EIGHTH_PERSON, INDEX_EIGHTH_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMultipleCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "14 -- 18",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMultipleCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "9 -1 3",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMultipleCommand.MESSAGE_USAGE));
    }
}
