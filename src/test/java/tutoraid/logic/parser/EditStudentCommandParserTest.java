package tutoraid.logic.parser;

import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_PARENT_NAME_DESC;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_PARENT_PHONE_DESC;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_STUDENT_NAME_DESC;
import static tutoraid.logic.commands.CommandTestUtil.INVALID_STUDENT_PHONE_DESC;
import static tutoraid.logic.commands.CommandTestUtil.PARENT_NAME_DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.PARENT_NAME_DESC_BOB;
import static tutoraid.logic.commands.CommandTestUtil.PARENT_PHONE_DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.PARENT_PHONE_DESC_BOB;
import static tutoraid.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.STUDENT_PHONE_DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.STUDENT_PHONE_DESC_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_NAME_AMY;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_NAME_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_AMY;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_AMY;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_PHONE_AMY;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_PHONE_BOB;
import static tutoraid.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tutoraid.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static tutoraid.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static tutoraid.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.EditStudentCommand;
import tutoraid.logic.commands.EditStudentCommand.EditStudentDescriptor;
import tutoraid.model.student.Name;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.Phone;
import tutoraid.model.student.StudentName;
import tutoraid.testutil.EditStudentDescriptorBuilder;

public class EditStudentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStudentCommand.MESSAGE_USAGE);

    private EditStudentCommandParser parser = new EditStudentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_STUDENT_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditStudentCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + STUDENT_NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + STUDENT_NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_STUDENT_NAME_DESC, StudentName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_STUDENT_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_PARENT_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_PARENT_NAME_DESC, ParentName.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid parent phone
        assertParseFailure(parser, "1" + INVALID_STUDENT_PHONE_DESC + PARENT_PHONE_DESC_AMY,
                Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + STUDENT_PHONE_DESC_BOB + INVALID_STUDENT_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1"
                + INVALID_STUDENT_NAME_DESC
                + INVALID_PARENT_PHONE_DESC
                + VALID_PARENT_NAME_AMY + VALID_STUDENT_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased()
                + STUDENT_PHONE_DESC_BOB
                + PARENT_PHONE_DESC_AMY
                + PARENT_NAME_DESC_AMY
                + STUDENT_NAME_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_AMY)
                .withStudentPhone(VALID_STUDENT_PHONE_BOB)
                .withParentPhone(VALID_PARENT_PHONE_AMY)
                .withParentName(VALID_PARENT_NAME_AMY)
                .build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + STUDENT_PHONE_DESC_BOB + PARENT_PHONE_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentPhone(VALID_STUDENT_PHONE_BOB)
                .withParentPhone(VALID_PARENT_PHONE_AMY)
                .build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + STUDENT_NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_AMY)
                .build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + STUDENT_PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder()
                .withStudentPhone(VALID_STUDENT_PHONE_AMY)
                .build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // parent phone
        userInput = targetIndex.getOneBased() + PARENT_PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder()
                .withParentPhone(VALID_PARENT_PHONE_AMY)
                .build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // parent name
        userInput = targetIndex.getOneBased() + PARENT_NAME_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder()
                .withParentName(VALID_PARENT_NAME_AMY)
                .build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased()
                + STUDENT_PHONE_DESC_AMY + PARENT_NAME_DESC_AMY + PARENT_PHONE_DESC_AMY
                + STUDENT_PHONE_DESC_AMY + PARENT_NAME_DESC_AMY + PARENT_PHONE_DESC_AMY
                + STUDENT_PHONE_DESC_BOB + PARENT_NAME_DESC_BOB + PARENT_PHONE_DESC_BOB;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentPhone(VALID_STUDENT_PHONE_BOB)
                .withParentPhone(VALID_PARENT_PHONE_BOB)
                .withParentName(VALID_PARENT_NAME_BOB)
                .build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_STUDENT_PHONE_DESC + STUDENT_PHONE_DESC_BOB;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentPhone(VALID_STUDENT_PHONE_BOB)
                .build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased()
                + PARENT_PHONE_DESC_BOB
                + INVALID_STUDENT_PHONE_DESC
                + PARENT_NAME_DESC_BOB
                + STUDENT_PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder()
                .withStudentPhone(VALID_STUDENT_PHONE_BOB)
                .withParentPhone(VALID_PARENT_PHONE_BOB)
                .withParentName(VALID_PARENT_NAME_BOB)
                .build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
