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
import seedu.address.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        // One index
        assertParseSuccess(parser, "1", new DeleteCommand(new Index[]{INDEX_FIRST_PERSON}));

        // Multiple index
        assertParseSuccess(parser, "1 2", new DeleteCommand(new Index[]{INDEX_SECOND_PERSON, INDEX_FIRST_PERSON}));

        // Multiple index with varying white space
        assertParseSuccess(parser, "3            1     2",
                new DeleteCommand(new Index[]{INDEX_THIRD_PERSON, INDEX_SECOND_PERSON, INDEX_FIRST_PERSON}));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String errorDueToInvalidIndex = MESSAGE_INVALID_COMMAND_FORMAT + "\n" + MESSAGE_INVALID_INDEX;
        String errorDueToDuplicateIndex = MESSAGE_INVALID_COMMAND_FORMAT + "\n" + MESSAGE_DUPLICATE_INDEX;

        // Not a number
        assertParseFailure(parser, "a", String.format(errorDueToInvalidIndex, DeleteCommand.MESSAGE_USAGE));

        // Zero index
        assertParseFailure(parser, "0", String.format(errorDueToInvalidIndex, DeleteCommand.MESSAGE_USAGE));

        // Negative index
        assertParseFailure(parser, "-1", String.format(errorDueToInvalidIndex, DeleteCommand.MESSAGE_USAGE));

        // Multiple inputs, one invalid
        assertParseFailure(parser, "0 1", String.format(errorDueToInvalidIndex, DeleteCommand.MESSAGE_USAGE));

        // Multiple inputs, all invalid
        assertParseFailure(parser, "0 -1", String.format(errorDueToInvalidIndex, DeleteCommand.MESSAGE_USAGE));

        // 2 duplicate inputs
        assertParseFailure(parser, "1 1",
                String.format(errorDueToDuplicateIndex, DeleteCommand.MESSAGE_USAGE));

         // Many duplicates interspersed (Note that DeleteCommandParser does not detect out of range indexes)
        assertParseFailure(parser, "70 2 3 2 2 1 70",
                String.format(errorDueToDuplicateIndex, DeleteCommand.MESSAGE_USAGE));

        // Duplicates as last few inputs
        assertParseFailure(parser, "2 3 4 4 4",
                String.format(errorDueToDuplicateIndex, DeleteCommand.MESSAGE_USAGE)); 
    }
}
