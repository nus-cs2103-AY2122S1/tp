package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_WEEK;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DUPLICATE_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEEK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkStudentPartCommand;
import seedu.address.model.student.Participation;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the MarkStudentPartCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the MarkStudentPartCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class MarkStudentPartCommandParserTest {

    private final MarkStudentPartCommandParser parser = new MarkStudentPartCommandParser();

    @Test
    public void parse_validArgs_returnsMarkStudentPartCommand() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + VALID_WEEK;

        assertParseSuccess(parser, userInput,
                new MarkStudentPartCommand(Collections.singletonList(INDEX_FIRST_STUDENT), 1));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // EP: Student index not a number
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkStudentPartCommand.MESSAGE_USAGE));

        // EP: Student index out of range
        assertParseFailure(parser, String.valueOf(Participation.LAST_WEEK_OF_SEM + 1),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkStudentPartCommand.MESSAGE_USAGE));

        // EP: No week prefix found
        assertParseFailure(parser, "1 2 12",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkStudentPartCommand.MESSAGE_USAGE));

        // EP: Empty parameter succeeding command word
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkStudentPartCommand.MESSAGE_USAGE));

        // EP: String as week parameter
        assertParseFailure(parser, "1 w/hello", String.format(Participation.MESSAGE_CONSTRAINTS,
                Participation.FIRST_WEEK_OF_SEM, Participation.LAST_WEEK_OF_SEM));

        // EP: Duplicate index found
        assertParseFailure(parser, "1 1 1 w/1", MESSAGE_INVALID_DUPLICATE_INDEX);

        // EP: Empty week parameter
        assertParseFailure(parser, "1 w/", MESSAGE_EMPTY_WEEK);
    }
}
