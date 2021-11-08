package seedu.plannermd.logic.parser.editcommandparser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_DOCTOR_RISK_FIELD;
import static seedu.plannermd.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.BIRTH_DATE_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.BIRTH_DATE_DESC_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_BIRTH_DATE_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.RISK_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.plannermd.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.editcommand.EditDoctorCommand;
import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.BirthDate;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.tag.Tag;
import seedu.plannermd.testutil.doctor.EditDoctorDescriptorBuilder;

public class EditDoctorCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDoctorCommand.MESSAGE_USAGE);

    private final EditDoctorCommandParser parser = new EditDoctorCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditDoctorCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_BIRTH_DATE_DESC, BirthDate.MESSAGE_CONSTRAINTS); //invalid birth date
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + BIRTH_DATE_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditDoctorCommand.EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withBirthDate(VALID_BIRTH_DATE_AMY).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditDoctorCommand expectedCommand = new EditDoctorCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditDoctorCommand.EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditDoctorCommand expectedCommand = new EditDoctorCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditDoctorCommand.EditDoctorDescriptor descriptor =
                new EditDoctorDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditDoctorCommand expectedCommand = new EditDoctorCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditDoctorDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditDoctorCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditDoctorDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditDoctorCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditDoctorDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditDoctorCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // birthdate
        userInput = targetIndex.getOneBased() + BIRTH_DATE_DESC_AMY;
        descriptor = new EditDoctorDescriptorBuilder().withBirthDate(VALID_BIRTH_DATE_AMY).build();
        expectedCommand = new EditDoctorCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditDoctorDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditDoctorCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + BIRTH_DATE_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + BIRTH_DATE_DESC_BOB
                + TAG_DESC_HUSBAND;

        EditDoctorCommand.EditDoctorDescriptor descriptor =
                new EditDoctorDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                        .withAddress(VALID_ADDRESS_BOB).withBirthDate(VALID_BIRTH_DATE_BOB)
                        .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        EditDoctorCommand expectedCommand = new EditDoctorCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditDoctorCommand.EditDoctorDescriptor descriptor =
                new EditDoctorDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditDoctorCommand expectedCommand = new EditDoctorCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditDoctorDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditDoctorCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditDoctorCommand.EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withTags().build();
        EditDoctorCommand expectedCommand = new EditDoctorCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_riskField_failure() {
        assertParseFailure(parser, RISK_DESC_AMY,
                String.format(MESSAGE_INVALID_DOCTOR_RISK_FIELD, EditDoctorCommand.MESSAGE_USAGE));
    }
}
