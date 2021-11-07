package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MARK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MARK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MARK_2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddLastMarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StudentMark;

public class AddLastMarkCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + MESSAGE_INVALID_COMMAND_FORMAT,
                    AddLastMarkCommand.MESSAGE_USAGE);

    private AddLastMarkCommandParser parser = new AddLastMarkCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "m/low", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLastMarkCommand.MESSAGE_NOT_EDITED));

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 m/" + VALID_MARK, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 m/" + VALID_MARK, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_MARK, StudentMark.MESSAGE_CONSTRAINTS); // invalid mark
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + VALID_MARK;
        AddLastMarkCommand expectedCommand = null;
        try {
            expectedCommand = parser.parse(userInput);
        } catch (ParseException e) {
            assertParseFailure(parser, userInput, StudentMark.MESSAGE_CONSTRAINTS);
        }

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + VALID_MARK + VALID_MARK_2;

        AddLastMarkCommand expectedCommand = null;
        try {
            expectedCommand = parser.parse(userInput);
        } catch (ParseException e) {
            assertParseFailure(parser, userInput, StudentMark.MESSAGE_CONSTRAINTS);
        }

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
