package seedu.programmer.logic.parser;


import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.programmer.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.programmer.logic.commands.DeleteCommand;
import seedu.programmer.logic.commands.DeleteLabCommand;


public class DeleteLabCommandParserTest {

    private DeleteLabCommandParser parser = new DeleteLabCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, 1, new DeleteLabCommand(LAB_NUM));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteLabCommand.MESSAGE_USAGE));
    }
}
