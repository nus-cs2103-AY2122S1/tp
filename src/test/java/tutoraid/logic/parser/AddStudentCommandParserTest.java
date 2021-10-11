package tutoraid.logic.parser;

import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_PARENT_NAME_DESC;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_PARENT_PHONE_DESC;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_STUDENT_NAME_DESC;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_STUDENT_PHONE_DESC;
import static tutoraid.logic.commands.CommandTestUtil.PARENT_NAME_DESC_BOB;
import static tutoraid.logic.commands.CommandTestUtil.PARENT_PHONE_DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.PARENT_PHONE_DESC_BOB;
import static tutoraid.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static tutoraid.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static tutoraid.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_BOB;
import static tutoraid.logic.commands.CommandTestUtil.STUDENT_PHONE_DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.STUDENT_PHONE_DESC_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static tutoraid.testutil.TypicalStudents.AMY;
import static tutoraid.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.AddStudentCommand;
import tutoraid.model.student.Name;
import tutoraid.model.student.Phone;
import tutoraid.model.student.Student;
import tutoraid.testutil.StudentBuilder;

public class AddStudentCommandParserTest {
    private final AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser, PREAMBLE_WHITESPACE + STUDENT_NAME_DESC_BOB
                + STUDENT_PHONE_DESC_BOB + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB,
            new AddStudentCommand(expectedStudent));

        // multiple names - last name accepted
        CommandParserTestUtil.assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + STUDENT_NAME_DESC_BOB
                + STUDENT_PHONE_DESC_BOB + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB,
            new AddStudentCommand(expectedStudent));

        // multiple phones - last phone accepted
        CommandParserTestUtil.assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + STUDENT_PHONE_DESC_AMY
                + STUDENT_PHONE_DESC_BOB + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB,
            new AddStudentCommand(expectedStudent));

        // TODO: add more tests for multiple parent name, parent phone
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // TODO: add tests with missing parent phone etc.
        Student expectedStudent = new StudentBuilder(AMY).withParentName("").build();
        CommandParserTestUtil.assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + STUDENT_PHONE_DESC_AMY
                + PARENT_PHONE_DESC_AMY,
            new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing student name prefix
        CommandParserTestUtil.assertParseFailure(parser, VALID_STUDENT_NAME_BOB + STUDENT_PHONE_DESC_BOB
            + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid student name
        CommandParserTestUtil.assertParseFailure(parser, INVALID_STUDENT_NAME_DESC + STUDENT_PHONE_DESC_BOB
            + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid student phone
        CommandParserTestUtil.assertParseFailure(parser, STUDENT_NAME_DESC_BOB + INVALID_STUDENT_PHONE_DESC
            + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid parent name
        CommandParserTestUtil.assertParseFailure(parser, STUDENT_NAME_DESC_BOB + STUDENT_PHONE_DESC_BOB
            + INVALID_PARENT_NAME_DESC + PARENT_PHONE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid parent phone
        CommandParserTestUtil.assertParseFailure(parser, STUDENT_NAME_DESC_BOB + STUDENT_PHONE_DESC_BOB
            + PARENT_NAME_DESC_BOB
            + INVALID_PARENT_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser, INVALID_STUDENT_NAME_DESC + STUDENT_PHONE_DESC_BOB
            + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser, PREAMBLE_NON_EMPTY + STUDENT_NAME_DESC_BOB
                + STUDENT_PHONE_DESC_BOB + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
