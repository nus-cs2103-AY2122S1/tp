package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_DUPLICATE_INDEX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
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
                new MarkCommand(new Index[]{INDEX_FIRST_PERSON, INDEX_SECOND_PERSON}));
        assertParseSuccess(parserForUnmark, "1 2",
                new UnmarkCommand(new Index[]{INDEX_FIRST_PERSON, INDEX_SECOND_PERSON}));

        // Multiple index with varying white space
        assertParseSuccess(parserForMark, "3            1     2",
                new MarkCommand(new Index[]{INDEX_THIRD_PERSON, INDEX_FIRST_PERSON, INDEX_SECOND_PERSON}));
        assertParseSuccess(parserForUnmark, "3            1     2",
                new UnmarkCommand(new Index[]{INDEX_THIRD_PERSON, INDEX_FIRST_PERSON, INDEX_SECOND_PERSON}));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String errorDueToInvalidIndex = MESSAGE_INVALID_COMMAND_FORMAT + "\n" + MESSAGE_INVALID_INDEX;
        String errorDueToDuplicateIndex = MESSAGE_INVALID_COMMAND_FORMAT + "\n" + MESSAGE_DUPLICATE_INDEX;

        // Not a number
        assertParseFailure(parserForMark, "a",
                String.format(errorDueToInvalidIndex, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "a",
                String.format(errorDueToInvalidIndex, UnmarkCommand.MESSAGE_USAGE));

        // Zero index
        assertParseFailure(parserForMark, "0",
                String.format(errorDueToInvalidIndex, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "0",
                String.format(errorDueToInvalidIndex, UnmarkCommand.MESSAGE_USAGE));

        // Negative index
        assertParseFailure(parserForMark, "-1",
                String.format(errorDueToInvalidIndex, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "-1",
                String.format(errorDueToInvalidIndex, UnmarkCommand.MESSAGE_USAGE));

        // Multiple inputs, one invalid
        assertParseFailure(parserForMark, "0 1",
                String.format(errorDueToInvalidIndex, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "0 1",
                String.format(errorDueToInvalidIndex, UnmarkCommand.MESSAGE_USAGE));

        // Multiple inputs, all invalid
        assertParseFailure(parserForMark, "0 -1",
                String.format(errorDueToInvalidIndex, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "0 -1",
                String.format(errorDueToInvalidIndex, UnmarkCommand.MESSAGE_USAGE));

        // 2 duplicate inputs
        assertParseFailure(parserForMark, "1 1", 
                String.format(errorDueToDuplicateIndex, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "1 1",
                String.format(errorDueToDuplicateIndex, UnmarkCommand.MESSAGE_USAGE));
        
        // Many duplicates interspersed (Note that MarkingCommandParser does not detect out of range indexes)
        assertParseFailure(parserForMark, "70 2 3 2 2 1 70", 
                String.format(errorDueToDuplicateIndex, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "70 2 3 2 2 1 70",
                String.format(errorDueToDuplicateIndex, UnmarkCommand.MESSAGE_USAGE)); 
        
         // Duplicates as last few inputs
        assertParseFailure(parserForMark, "2 3 4 4 4",
                String.format(errorDueToDuplicateIndex, MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parserForUnmark, "2 3 4 4 4",
                String.format(errorDueToDuplicateIndex, UnmarkCommand.MESSAGE_USAGE));
    }
}
