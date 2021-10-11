package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PARENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PARENT_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PARENT_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PARENT_NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PARENT_PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PARENT_PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.Phone;
import seedu.address.testutil.PersonBuilder;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STUDENT_NAME_DESC_BOB + STUDENT_PHONE_DESC_BOB
                + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB, new AddStudentCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + STUDENT_NAME_DESC_BOB + STUDENT_PHONE_DESC_BOB
                + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB, new AddStudentCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + STUDENT_PHONE_DESC_AMY + STUDENT_PHONE_DESC_BOB
                + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB, new AddStudentCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + STUDENT_PHONE_DESC_BOB + PARENT_NAME_DESC_AMY
                + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB, new AddStudentCommand(expectedStudent));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + STUDENT_PHONE_DESC_BOB + PARENT_NAME_DESC_BOB
                + PARENT_PHONE_DESC_AMY + PARENT_PHONE_DESC_BOB, new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new PersonBuilder(AMY).withParentName("").build();
        assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + STUDENT_PHONE_DESC_AMY + PARENT_PHONE_DESC_AMY,
                new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing student name prefix
        assertParseFailure(parser, VALID_STUDENT_NAME_BOB + STUDENT_PHONE_DESC_BOB + PARENT_NAME_DESC_BOB
                        + PARENT_PHONE_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid student name
        assertParseFailure(parser, INVALID_STUDENT_NAME_DESC + STUDENT_PHONE_DESC_BOB + PARENT_NAME_DESC_BOB
                + PARENT_PHONE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid student phone
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + INVALID_STUDENT_PHONE_DESC + PARENT_NAME_DESC_BOB
                + PARENT_PHONE_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid parent name
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + STUDENT_PHONE_DESC_BOB + INVALID_PARENT_NAME_DESC
                + PARENT_PHONE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid parent phone
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + STUDENT_PHONE_DESC_BOB + PARENT_NAME_DESC_BOB
                + INVALID_PARENT_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_STUDENT_NAME_DESC + STUDENT_PHONE_DESC_BOB + PARENT_NAME_DESC_BOB
                        + PARENT_PHONE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + STUDENT_NAME_DESC_BOB + STUDENT_PHONE_DESC_BOB
                        + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
