package seedu.times.logic.parser.classcommands;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX;
import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.classcommands.SelectClassCommand;
import seedu.times.logic.parser.classcommandparsers.SelectClassCommandParser;


public class SelectClassCommandParserTest {
    private SelectClassCommandParser parser = new SelectClassCommandParser();

    @Test
    public void parse_emptyArgs_failure() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SelectClassCommand.MESSAGE_USAGE)); //nothing
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SelectClassCommand.MESSAGE_USAGE)); //whitespace
    }

    @Test
    public void parse_validIndex_returnsSelectClassCommand() {
        SelectClassCommand expectedSelectClassCommand =
                new SelectClassCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, "1", expectedSelectClassCommand);
        expectedSelectClassCommand =
                new SelectClassCommand(Index.fromOneBased(10));
        assertParseSuccess(parser, "10", expectedSelectClassCommand);
    }

    @Test
    public void parse_invalidClassIndex_failure() {
        //non-digit
        assertParseFailure(parser, "ds", MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        //non-digit with student index
        assertParseFailure(parser, "oiajsd 1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SelectClassCommand.MESSAGE_USAGE));
        //negative
        assertParseFailure(parser, "-4", MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        //zero
        assertParseFailure(parser, "0", MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
    }
}
