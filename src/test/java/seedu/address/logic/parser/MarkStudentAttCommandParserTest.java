package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEEK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkStudentAttCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the MarkStudentAttCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the MarkStudentAttCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class MarkStudentAttCommandParserTest {

    private MarkStudentAttCommandParser parser = new MarkStudentAttCommandParser();

    @Test
    public void parse_validArgs_returnsMarkStudentAttCommand() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + VALID_WEEK;

        assertParseSuccess(parser, userInput,
                new MarkStudentAttCommand(INDEX_FIRST_STUDENT, 1));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkStudentAttCommand.MESSAGE_USAGE));
    }
}
