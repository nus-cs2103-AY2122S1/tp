package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.HEADER_ALERT;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.GRADE_DESC_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.GRADE_DESC_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.INVALID_REMARK_DESC;
import static seedu.tuitione.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.tuitione.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.tuitione.logic.commands.CommandTestUtil.REMARK_DESC_FRIEND;
import static seedu.tuitione.logic.commands.CommandTestUtil.REMARK_DESC_HUSBAND;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_REMARK_FRIEND;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_REMARK_HUSBAND;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tuitione.testutil.TypicalStudents.AMY;
import static seedu.tuitione.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.tuitione.logic.commands.AddCommand;
import seedu.tuitione.model.remark.Remark;
import seedu.tuitione.model.student.Address;
import seedu.tuitione.model.student.Email;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.ParentContact;
import seedu.tuitione.model.student.Student;
import seedu.tuitione.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withRemarks(VALID_REMARK_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GRADE_DESC_BOB + REMARK_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GRADE_DESC_BOB + REMARK_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GRADE_DESC_BOB + REMARK_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GRADE_DESC_BOB + REMARK_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple addresses - last tuitione accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + GRADE_DESC_BOB + REMARK_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple grades - last grade accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GRADE_DESC_AMY + GRADE_DESC_BOB + REMARK_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple remarks - all accepted
        Student expectedStudentMultipleRemarks = new StudentBuilder(BOB).withRemarks(VALID_REMARK_FRIEND,
                VALID_REMARK_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GRADE_DESC_BOB + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND,
                new AddCommand(expectedStudentMultipleRemarks));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero remarks
        Student expectedStudent = new StudentBuilder(AMY).withRemarks().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + GRADE_DESC_AMY, new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GRADE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GRADE_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + GRADE_DESC_BOB, expectedMessage);

        // missing tuitione prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + GRADE_DESC_BOB, expectedMessage);

        // missing grade prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + VALID_GRADE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + VALID_GRADE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GRADE_DESC_BOB + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND,
                HEADER_ALERT + Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GRADE_DESC_BOB + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND,
                HEADER_ALERT + ParentContact.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + GRADE_DESC_BOB + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND,
                HEADER_ALERT + Email.MESSAGE_CONSTRAINTS);

        // invalid tuitione
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + GRADE_DESC_BOB + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND,
                HEADER_ALERT + Address.MESSAGE_CONSTRAINTS);

        // invalid grade
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + INVALID_GRADE_DESC + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND,
                HEADER_ALERT + Grade.GRADE_MESSAGE_CONSTRAINTS);

        // invalid remark
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GRADE_DESC_BOB + INVALID_REMARK_DESC + VALID_REMARK_FRIEND,
                HEADER_ALERT + Remark.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + GRADE_DESC_BOB,
                HEADER_ALERT + Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GRADE_DESC_BOB + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
