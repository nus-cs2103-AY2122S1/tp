package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INDEX_ERROR;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.DeleteCommand;

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
    public void parse_validQuestionArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " question 1", new DeleteCommand("question", INDEX_FIRST_QUESTION));
    }

    @Test
    public void parse_validNoteArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " note 1", new DeleteCommand("note", INDEX_FIRST_QUESTION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_incompleteArgs_throwsParseException() {
        assertParseFailure(parser, " note", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " question",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeIndex_throwsParseException() {
        assertParseFailure(parser, " note -1", MESSAGE_INDEX_ERROR);
        assertParseFailure(parser, " question -1", MESSAGE_INDEX_ERROR);
    }

    @Test
    public void parse_valueLargerThanMaxInteger_throwsParseException() {
        assertParseFailure(parser, " note 1234567812345", MESSAGE_INDEX_ERROR);
        assertParseFailure(parser, " question 1234567812345", MESSAGE_INDEX_ERROR);
    }
}
