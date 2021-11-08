
package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.commons.core.Messages.MESSAGE_TEMPLATE;
import static seedu.programmer.logic.commands.CommandTestUtil.CLASS_ID_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.CLASS_ID_DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.INVALID_CLASS_ID_DESC;
import static seedu.programmer.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.programmer.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.programmer.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.programmer.logic.commands.CommandTestUtil.LAB_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_ACTUAL_SCORE;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_CLASS_ID_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_CLASS_ID_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_LAB_NO;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.programmer.logic.parser.ParserUtil.MESSAGE_EMPTY_INDEX;
import static seedu.programmer.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.programmer.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.programmer.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.programmer.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.EditCommand;
import seedu.programmer.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.StudentId;
import seedu.programmer.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY,
                String.format(MESSAGE_TEMPLATE, MESSAGE_INVALID_INDEX, EditCommand.MESSAGE_USAGE));

        // no field specified
        assertParseFailure(parser, "1",
                String.format(MESSAGE_TEMPLATE, EditCommand.MESSAGE_NOT_EDITED, EditCommand.MESSAGE_USAGE));

        // no index and no field specified
        assertParseFailure(parser, "",
                String.format(MESSAGE_TEMPLATE, MESSAGE_EMPTY_INDEX, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY,
                String.format(MESSAGE_TEMPLATE, MESSAGE_INVALID_INDEX, EditCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY,
                String.format(MESSAGE_TEMPLATE, MESSAGE_INVALID_INDEX, EditCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string",
                String.format(MESSAGE_TEMPLATE, MESSAGE_INVALID_INDEX, EditCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string",
                String.format(MESSAGE_TEMPLATE, MESSAGE_INVALID_INDEX, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC,
                String.format(MESSAGE_TEMPLATE, Name.MESSAGE_CONSTRAINTS, EditCommand.MESSAGE_USAGE));
        // invalid student_id
        assertParseFailure(parser, "1" + INVALID_STUDENT_ID_DESC,
                String.format(MESSAGE_TEMPLATE, StudentId.MESSAGE_CONSTRAINTS, EditCommand.MESSAGE_USAGE));
        // invalid class_id
        assertParseFailure(parser, "1" + INVALID_CLASS_ID_DESC,
                String.format(MESSAGE_TEMPLATE, ClassId.MESSAGE_CONSTRAINTS, EditCommand.MESSAGE_USAGE));
        // invalid email
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC,
                String.format(MESSAGE_TEMPLATE, Email.MESSAGE_CONSTRAINTS, EditCommand.MESSAGE_USAGE));

        // invalid studentId followed by valid classid
        assertParseFailure(parser, "1" + INVALID_STUDENT_ID_DESC + CLASS_ID_DESC_AMY,
                String.format(MESSAGE_TEMPLATE, StudentId.MESSAGE_CONSTRAINTS, EditCommand.MESSAGE_USAGE));

        // valid studentId followed by invalid studentId. The test case for invalid studentId followed by valid
        // studentId is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + STUDENT_ID_DESC_BOB + INVALID_STUDENT_ID_DESC,
                String.format(MESSAGE_TEMPLATE, StudentId.MESSAGE_CONSTRAINTS, EditCommand.MESSAGE_USAGE));

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_STUDENT_ID_DESC
                        + VALID_CLASS_ID_AMY + VALID_STUDENT_ID_AMY,
                String.format(MESSAGE_TEMPLATE, Name.MESSAGE_CONSTRAINTS, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + STUDENT_ID_DESC_BOB
                + CLASS_ID_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentId(VALID_STUDENT_ID_BOB).withClassId(VALID_CLASS_ID_AMY).withemail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + STUDENT_ID_DESC_BOB + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_BOB)
                .withemail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // student ID
        userInput = targetIndex.getOneBased() + STUDENT_ID_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withemail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // class ID
        userInput = targetIndex.getOneBased() + CLASS_ID_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withClassId(VALID_CLASS_ID_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // labNum and labResult
        userInput = targetIndex.getOneBased() + LAB_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withLab(VALID_LAB_NO, VALID_ACTUAL_SCORE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + STUDENT_ID_DESC_AMY + CLASS_ID_DESC_AMY + EMAIL_DESC_AMY
                + STUDENT_ID_DESC_AMY + EMAIL_DESC_AMY + CLASS_ID_DESC_AMY
                + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + CLASS_ID_DESC_BOB;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_BOB)
                .withClassId(VALID_CLASS_ID_BOB).withemail(VALID_EMAIL_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }



    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_STUDENT_ID_DESC + STUDENT_ID_DESC_BOB;
        //System.out.println(userInput);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentId(VALID_STUDENT_ID_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + CLASS_ID_DESC_BOB + INVALID_STUDENT_ID_DESC + EMAIL_DESC_BOB
                + STUDENT_ID_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_BOB)
                .withClassId(VALID_CLASS_ID_BOB)
                .withemail(VALID_EMAIL_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


}
