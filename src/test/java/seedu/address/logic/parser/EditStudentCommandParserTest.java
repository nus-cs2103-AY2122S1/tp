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
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TELE_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELE_HANDLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Email;
import seedu.address.model.module.student.Name;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.TeleHandle;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditStudentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStudentCommand.MESSAGE_USAGE);

    private EditStudentCommandParser parser = new EditStudentCommandParser();

    private ModuleName moduleName = new ModuleName(MODULE_NAME_0);

    @Test
    public void parse_missingParts_failure() {
        // no student id specified
        assertParseFailure(parser, MODULE_NAME_DESC_0, MESSAGE_INVALID_FORMAT);

        // no module name specified
        assertParseFailure(parser, VALID_STUDENT_ID_AMY, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_MODULE_NAME_DESC + STUDENT_ID_DESC_AMY,
                ModuleName.MESSAGE_CONSTRAINTS); // invalid module name
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, MODULE_NAME_DESC_0 + INVALID_STUDENT_ID_DESC,
                StudentId.MESSAGE_CONSTRAINTS); // invalid student id
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + INVALID_EMAIL_DESC,
                Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + INVALID_TELE_HANDLE_DESC,
                TeleHandle.MESSAGE_CONSTRAINTS); // invalid tele handle

        // invalid name followed by valid email
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + INVALID_NAME_DESC
                + EMAIL_DESC_AMY, Name.MESSAGE_CONSTRAINTS);

        // valid name followed by invalid name. The test case for invalid name followed by valid name
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + NAME_DESC_AMY
                + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + INVALID_NAME_DESC
                        + INVALID_EMAIL_DESC + VALID_TELE_HANDLE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB
                + EMAIL_DESC_AMY + TELE_HANDLE_DESC_AMY + NAME_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentId(VALID_STUDENT_ID_BOB).withEmail(VALID_EMAIL_AMY).withTeleHandle(VALID_TELE_HANDLE_AMY)
                .build();
        EditStudentCommand expectedCommand = new EditStudentCommand(moduleName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + EMAIL_DESC_AMY + NAME_DESC_BOB;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_BOB)
                .withEmail(VALID_EMAIL_AMY).withName(VALID_NAME_BOB).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(moduleName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_AMY)
                .withName(VALID_NAME_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(moduleName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_AMY)
                .withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditStudentCommand(moduleName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tele handle
        userInput = MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + TELE_HANDLE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_AMY)
                .withTeleHandle(VALID_TELE_HANDLE_AMY).build();
        expectedCommand = new EditStudentCommand(moduleName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = MODULE_NAME_DESC_0 + STUDENT_ID_DESC_AMY + TELE_HANDLE_DESC_AMY + EMAIL_DESC_AMY
                + STUDENT_ID_DESC_AMY + TELE_HANDLE_DESC_AMY + EMAIL_DESC_AMY
                + STUDENT_ID_DESC_BOB + TELE_HANDLE_DESC_BOB + EMAIL_DESC_BOB;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_BOB)
                .withEmail(VALID_EMAIL_BOB).withTeleHandle(VALID_TELE_HANDLE_BOB)
                .build();
        EditStudentCommand expectedCommand = new EditStudentCommand(moduleName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + INVALID_NAME_DESC + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_BOB)
                .withName(VALID_NAME_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(moduleName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB + INVALID_TELE_HANDLE_DESC + TELE_HANDLE_DESC_BOB
                + NAME_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_BOB)
                .withTeleHandle(VALID_TELE_HANDLE_BOB).withName(VALID_NAME_BOB).build();
        expectedCommand = new EditStudentCommand(moduleName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
