package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.UnmarkCommand;

public class MarkingCommandParserTest {

    private String markCommandWord = "mark";
    private String unmarkCommandWord = "unmark";

    private MarkingCommandParser parserForMark = new MarkingCommandParser(markCommandWord);
    private MarkingCommandParser parserForUnmark = new MarkingCommandParser(unmarkCommandWord);

    @Test
    public void parse_validArgs_returnsMarkingCommand() {
        // One index
        assertParseSuccess(parserForMark, "1", new MarkCommand(new Index[]{INDEX_FIRST_PERSON}));
        assertParseSuccess(parserForUnmark, "1", new UnmarkCommand(new Index[]{INDEX_FIRST_PERSON}));

        // Multiple index
        assertParseSuccess(parserForMark, "1 2",
                new MarkCommand(new Index[]{INDEX_SECOND_PERSON, INDEX_FIRST_PERSON}));
        assertParseSuccess(parserForUnmark, "1 2",
                new UnmarkCommand(new Index[]{INDEX_SECOND_PERSON, INDEX_FIRST_PERSON}));

        // Multiple index with varying white space
        assertParseSuccess(parserForMark, "3            1     2",
                new MarkCommand(new Index[]{INDEX_THIRD_PERSON, INDEX_SECOND_PERSON, INDEX_FIRST_PERSON}));
        assertParseSuccess(parserForUnmark, "3            1     2",
                new UnmarkCommand(new Index[]{INDEX_THIRD_PERSON, INDEX_SECOND_PERSON, INDEX_FIRST_PERSON}));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Not a number
        assertParseFailure(parserForMark, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));

        // Zero index
        assertParseFailure(parserForMark, "0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));

        // Negative index
        assertParseFailure(parserForMark, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));

        // Multiple inputs, one invalid
        assertParseFailure(parserForMark, "0 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "0 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));

        // Multiple inputs, all invalid
        assertParseFailure(parserForMark, "0 -1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "0 -1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
    }
}
