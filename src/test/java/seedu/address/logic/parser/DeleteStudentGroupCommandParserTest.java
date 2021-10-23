package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_WRONG_NUMBER_OF_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GROUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteStudentGroupCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteStudentCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteStudentGroupCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteStudentGroupCommandParserTest {

    private DeleteStudentGroupCommandParser parser = new DeleteStudentGroupCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 1", new DeleteStudentGroupCommand(INDEX_FIRST_GROUP, INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsInvalidNumber_throwsParseException() {
        assertParseFailure(parser, "1 1 1",
                String.format(MESSAGE_WRONG_NUMBER_OF_INDEX, 2, 3));
    }
}
