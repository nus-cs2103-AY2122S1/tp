package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELE_HANDLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TELE_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELE_HANDLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Email;
import seedu.address.model.module.student.Name;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.TeleHandle;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();
    private ModuleName moduleName = new ModuleName(MODULE_NAME_0);
    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = BOB;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB
                + NAME_DESC_BOB + TELE_HANDLE_DESC_BOB + EMAIL_DESC_BOB,
                new AddStudentCommand(expectedStudent, moduleName));

        // multiple names - last name accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + NAME_DESC_AMY + NAME_DESC_BOB
                + TELE_HANDLE_DESC_BOB + EMAIL_DESC_BOB, new AddStudentCommand(expectedStudent, moduleName));

        // multiple student ids - last student id accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + STUDENT_ID_DESC_BOB
                + NAME_DESC_BOB + TELE_HANDLE_DESC_BOB + EMAIL_DESC_BOB,
                new AddStudentCommand(expectedStudent, moduleName));

        // multiple emails - last email accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + NAME_DESC_BOB
                + TELE_HANDLE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB,
                new AddStudentCommand(expectedStudent, moduleName));

        // multiple tele handles - last tele handle accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + NAME_DESC_BOB
                + TELE_HANDLE_DESC_AMY + TELE_HANDLE_DESC_BOB + EMAIL_DESC_BOB,
                new AddStudentCommand(expectedStudent, moduleName));

        // multiple module names - last module name accepted
        assertParseSuccess(parser, MODULE_NAME_DESC_0 + MODULE_NAME_DESC_1 + STUDENT_ID_DESC_BOB
                + NAME_DESC_BOB + TELE_HANDLE_DESC_AMY + TELE_HANDLE_DESC_BOB + EMAIL_DESC_BOB,
                new AddStudentCommand(expectedStudent, moduleName));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + VALID_NAME_BOB
                + TELE_HANDLE_DESC_BOB + EMAIL_DESC_BOB, expectedMessage);

        // missing student id prefix
        assertParseFailure(parser, MODULE_NAME_DESC_0 + VALID_STUDENT_ID_BOB + NAME_DESC_BOB
                + TELE_HANDLE_DESC_BOB + EMAIL_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + NAME_DESC_BOB
                + TELE_HANDLE_DESC_BOB + VALID_EMAIL_BOB, expectedMessage);

        // missing tele handle prefix
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + NAME_DESC_BOB
                + VALID_TELE_HANDLE_BOB + EMAIL_DESC_BOB, expectedMessage);

        // missing module name prefix
        assertParseFailure(parser, MODULE_NAME_0 + STUDENT_ID_DESC_BOB + NAME_DESC_BOB
                + VALID_TELE_HANDLE_BOB + EMAIL_DESC_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, MODULE_NAME_0 + VALID_STUDENT_ID_BOB + VALID_NAME_BOB
                + VALID_TELE_HANDLE_BOB + VALID_EMAIL_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + INVALID_NAME_DESC
                + TELE_HANDLE_DESC_BOB + EMAIL_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid student id
        assertParseFailure(parser, MODULE_NAME_DESC_0 + INVALID_STUDENT_ID_DESC + NAME_DESC_BOB
                + TELE_HANDLE_DESC_BOB + EMAIL_DESC_BOB, StudentId.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + NAME_DESC_BOB
                + TELE_HANDLE_DESC_BOB + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        // invalid tele handle
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + NAME_DESC_BOB
                + INVALID_TELE_HANDLE_DESC + EMAIL_DESC_BOB, TeleHandle.MESSAGE_CONSTRAINTS);

        // invalid module name
        assertParseFailure(parser, INVALID_MODULE_NAME_DESC + STUDENT_ID_DESC_BOB + NAME_DESC_BOB
                + INVALID_TELE_HANDLE_DESC + EMAIL_DESC_BOB, TeleHandle.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + INVALID_NAME_DESC
                + INVALID_TELE_HANDLE_DESC + EMAIL_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB
                + NAME_DESC_BOB + TELE_HANDLE_DESC_BOB + EMAIL_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
