package seedu.times.logic.parser.classcommands;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.times.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.times.logic.commands.classcommands.DeleteClassCommand;
import seedu.times.logic.parser.classcommandparsers.DeleteClassCommandParser;

public class DeleteClassCommandParserTest {
    private DeleteClassCommandParser parser = new DeleteClassCommandParser();

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, "1", new DeleteClassCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_failure() {
        //no args
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE));

        //empty args
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE));

        //negative args
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE));

        //zero
        assertParseFailure(parser, "0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE));

        //float
        assertParseFailure(parser, "1.2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE));

        //char
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE));
    }
}
