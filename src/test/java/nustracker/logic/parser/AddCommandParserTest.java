package nustracker.logic.parser;

import static nustracker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustracker.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.AddCommand;
import nustracker.logic.commands.CommandTestUtil;
import nustracker.model.student.Email;
import nustracker.model.student.Major;
import nustracker.model.student.Name;
import nustracker.model.student.Phone;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.model.student.Year;
import nustracker.testutil.StudentBuilder;
import nustracker.testutil.TypicalStudents;


public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(TypicalStudents.BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_AMY + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, new AddCommand(expectedStudent));

        // multiple year of study given - last year accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_AMY
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, new AddCommand(expectedStudent));

        // multiple majors - last major accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_AMY
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, new AddCommand(expectedStudent));

        // multiple student ID - last student ID accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_AMY
                + CommandTestUtil.STUDENTID_DESC_BOB, new AddCommand(expectedStudent));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Student expectedStudent = new StudentBuilder(TypicalStudents.AMY).build();
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY
                + CommandTestUtil.PHONE_DESC_AMY + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.YEAR_DESC_AMY
                + CommandTestUtil.MAJOR_DESC_AMY
                + CommandTestUtil.STUDENTID_DESC_AMY, new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.VALID_PHONE_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.VALID_EMAIL_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, expectedMessage);

        // missing year prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.VALID_YEAR_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, expectedMessage);

        // missing major prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.VALID_EMAIL_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.VALID_MAJOR_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, expectedMessage);

        // missing studentId prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.VALID_EMAIL_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.VALID_STUDENTID_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB
                        + CommandTestUtil.VALID_PHONE_BOB + CommandTestUtil.VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_EMAIL_DESC
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, Email.MESSAGE_CONSTRAINTS);


        // invalid year
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_EMAIL_DESC
                + CommandTestUtil.INVALID_YEAR_DESC
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, Year.MESSAGE_CONSTRAINTS);

        // invalid major
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_EMAIL_DESC
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.INVALID_MAJOR_DESC
                + CommandTestUtil.STUDENTID_DESC_BOB, Major.MESSAGE_CONSTRAINTS);


        // invalid student ID
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_EMAIL_DESC
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.INVALID_STUDENTID_DESC, StudentId.MESSAGE_CONSTRAINTS);


        // non-empty preamble
        assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.YEAR_DESC_BOB
                + CommandTestUtil.MAJOR_DESC_BOB
                + CommandTestUtil.STUDENTID_DESC_BOB, String.format(
                Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
