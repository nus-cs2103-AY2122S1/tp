package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CLASSID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CLASSID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLASSID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENTID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.model.student.ClassId;
import seedu.address.model.student.Grade;
import seedu.address.model.student.Name;
import seedu.address.model.student.StudentId;
import seedu.address.testutil.EditStudentDescriptorBuilder;

//TODO
public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid student_id
        assertParseFailure(parser, "1" + INVALID_STUDENTID_DESC, StudentId.MESSAGE_CONSTRAINTS);
        // invalid class_id
        assertParseFailure(parser, "1" + INVALID_CLASSID_DESC, ClassId.MESSAGE_CONSTRAINTS);
        // invalid grade
        assertParseFailure(parser, "1" + INVALID_GRADE_DESC, Grade.MESSAGE_CONSTRAINTS);

        // invalid studentId followed by valid classid
        assertParseFailure(parser, "1" + INVALID_STUDENTID_DESC + CLASSID_DESC_AMY,
                StudentId.MESSAGE_CONSTRAINTS);

        // valid studentId followed by invalid studentId. The test case for invalid studentId followed by valid
        // studentId is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + STUDENTID_DESC_BOB + INVALID_STUDENTID_DESC,
                StudentId.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_STUDENTID_DESC
                        + VALID_CLASSID_AMY + VALID_STUDENTID_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + STUDENTID_DESC_BOB
                + CLASSID_DESC_AMY + GRADE_DESC_AMY + NAME_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentId(VALID_STUDENTID_BOB).withClassId(VALID_CLASSID_AMY).withGrade(VALID_GRADE_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + STUDENTID_DESC_BOB + GRADE_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENTID_BOB)
                .withGrade(VALID_GRADE_AMY).build();
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
        userInput = targetIndex.getOneBased() + STUDENTID_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENTID_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // grade
        userInput = targetIndex.getOneBased() + GRADE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withGrade(VALID_GRADE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //TODO

        // class ID
        userInput = targetIndex.getOneBased() + CLASSID_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withClassId(VALID_CLASSID_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);


    }


    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + STUDENTID_DESC_AMY + CLASSID_DESC_AMY + GRADE_DESC_AMY
                + STUDENTID_DESC_AMY + GRADE_DESC_AMY + CLASSID_DESC_AMY
                + STUDENTID_DESC_BOB + GRADE_DESC_BOB + CLASSID_DESC_BOB;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENTID_BOB)
                .withClassId(VALID_CLASSID_BOB).withGrade(VALID_GRADE_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }



    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_STUDENTID_DESC + STUDENTID_DESC_BOB;
        //System.out.println(userInput);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentId(VALID_STUDENTID_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + CLASSID_DESC_BOB + INVALID_STUDENTID_DESC + GRADE_DESC_BOB
                + STUDENTID_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENTID_BOB)
                .withClassId(VALID_CLASSID_BOB)
                .withGrade(VALID_GRADE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


}
