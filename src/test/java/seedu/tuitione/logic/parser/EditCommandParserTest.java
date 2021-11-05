package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.HEADER_ALERT;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_INDEX;
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
import static seedu.tuitione.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.REMARK_DESC_FINANCIAL_AID;
import static seedu.tuitione.logic.commands.CommandTestUtil.REMARK_DESC_FRIEND;
import static seedu.tuitione.logic.commands.CommandTestUtil.REMARK_DESC_HUSBAND;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_GRADE_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_REMARK_FINANCIAL_AID;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_REMARK_FRIEND;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.EditCommand;
import seedu.tuitione.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.tuitione.model.remark.Remark;
import seedu.tuitione.model.student.Address;
import seedu.tuitione.model.student.Email;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.ParentContact;
import seedu.tuitione.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String REMARK_EMPTY = " " + PREFIX_REMARK;

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
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC,
                HEADER_ALERT + Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC,
                HEADER_ALERT + ParentContact.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC,
                HEADER_ALERT + Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC,
                HEADER_ALERT + Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_GRADE_DESC,
                HEADER_ALERT + Grade.GRADE_MESSAGE_CONSTRAINTS); // invalid grade
        assertParseFailure(parser, "1" + INVALID_REMARK_DESC,
                HEADER_ALERT + Remark.MESSAGE_CONSTRAINTS); // invalid remark

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY,
                HEADER_ALERT + ParentContact.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC,
                HEADER_ALERT + ParentContact.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_REMARK} alone will reset the remarks of the {@code Student} being edited,
        // parsing it together with a valid remark results in error
        assertParseFailure(parser, "1" + REMARK_DESC_FRIEND + REMARK_DESC_HUSBAND + REMARK_EMPTY,
                HEADER_ALERT + Remark.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + REMARK_DESC_FRIEND + REMARK_EMPTY + REMARK_DESC_HUSBAND,
                HEADER_ALERT + Remark.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + REMARK_EMPTY + REMARK_DESC_FRIEND + REMARK_DESC_HUSBAND,
                HEADER_ALERT + Remark.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY
                        + VALID_PHONE_AMY,
                HEADER_ALERT + Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + REMARK_DESC_FINANCIAL_AID
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY
                + GRADE_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withRemarks(VALID_REMARK_FINANCIAL_AID).withGrade(VALID_GRADE_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
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

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // grade
        userInput = targetIndex.getOneBased() + GRADE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withGrade(VALID_GRADE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // remarks
        userInput = targetIndex.getOneBased() + REMARK_DESC_FRIEND;
        descriptor = new EditStudentDescriptorBuilder().withRemarks(VALID_REMARK_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + GRADE_DESC_AMY + REMARK_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + GRADE_DESC_AMY + REMARK_DESC_FRIEND + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + REMARK_DESC_FINANCIAL_AID;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withGrade(VALID_GRADE_BOB)
                .withRemarks(VALID_REMARK_FRIEND, VALID_REMARK_FINANCIAL_AID)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
