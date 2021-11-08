package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.commons.core.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.programmer.logic.commands.CommandTestUtil.CLASS_ID_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.CLASS_ID_DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.programmer.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_CLASS_ID_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.programmer.testutil.TypicalStudents.AMY;
import static seedu.programmer.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.programmer.logic.commands.AddCommand;
import seedu.programmer.model.student.Student;
import seedu.programmer.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        //System.out.println(NAME_DESC_BOB);
        Student expectedStudent = new StudentBuilder(BOB).buildNoLab();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + STUDENT_ID_DESC_BOB + CLASS_ID_DESC_BOB
                + EMAIL_DESC_BOB, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + STUDENT_ID_DESC_BOB + CLASS_ID_DESC_BOB
                + EMAIL_DESC_BOB, new AddCommand(expectedStudent));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).buildNoLab();
        assertParseSuccess(parser, NAME_DESC_AMY + STUDENT_ID_DESC_AMY + CLASS_ID_DESC_AMY + EMAIL_DESC_AMY,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedInvalidMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
        String expectedMissingArgMessage = String.format(MESSAGE_MISSING_ARGUMENT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + STUDENT_ID_DESC_BOB + CLASS_ID_DESC_BOB + EMAIL_DESC_BOB,
                expectedInvalidMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_STUDENT_ID_BOB + CLASS_ID_DESC_BOB + EMAIL_DESC_BOB,
                expectedMissingArgMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + VALID_CLASS_ID_BOB + EMAIL_DESC_BOB,
                expectedMissingArgMessage);

        // missing programmer prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + CLASS_ID_DESC_BOB + VALID_EMAIL_BOB,
                expectedMissingArgMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_STUDENT_ID_BOB + VALID_CLASS_ID_BOB + VALID_EMAIL_BOB,
                expectedInvalidMessage);
    }
}
