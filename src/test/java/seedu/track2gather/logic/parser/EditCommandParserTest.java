package seedu.track2gather.logic.parser;

import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.track2gather.logic.commands.CommandTestUtil.CASE_NUMBER_DESC_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.CASE_NUMBER_DESC_BOB;
import static seedu.track2gather.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.track2gather.logic.commands.CommandTestUtil.HOME_ADDRESS_DESC_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.HOME_ADDRESS_DESC_BOB;
import static seedu.track2gather.logic.commands.CommandTestUtil.INVALID_CASE_NUMBER_DESC;
import static seedu.track2gather.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.track2gather.logic.commands.CommandTestUtil.INVALID_HOME_ADDRESS_DESC;
import static seedu.track2gather.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.track2gather.logic.commands.CommandTestUtil.INVALID_NEXT_OF_KIN_ADDRESS_DESC;
import static seedu.track2gather.logic.commands.CommandTestUtil.INVALID_NEXT_OF_KIN_NAME_DESC;
import static seedu.track2gather.logic.commands.CommandTestUtil.INVALID_NEXT_OF_KIN_PHONE_DESC;
import static seedu.track2gather.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.track2gather.logic.commands.CommandTestUtil.INVALID_QUARANTINE_ADDRESS_DESC;
import static seedu.track2gather.logic.commands.CommandTestUtil.INVALID_SHN_PERIOD_DESC;
import static seedu.track2gather.logic.commands.CommandTestUtil.INVALID_WORK_ADDRESS_DESC;
import static seedu.track2gather.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.NEXT_OF_KIN_ADDRESS_DESC_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.NEXT_OF_KIN_NAME_DESC_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.NEXT_OF_KIN_PHONE_DESC_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.track2gather.logic.commands.CommandTestUtil.QUARANTINE_ADDRESS_DESC_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.SHN_PERIOD_DESC_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_CASE_NUMBER_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_CASE_NUMBER_BOB;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_HOME_ADDRESS_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_HOME_ADDRESS_BOB;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_ADDRESS_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_NAME_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_PHONE_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_QUARANTINE_ADDRESS_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_SHN_PERIOD_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.VALID_WORK_ADDRESS_AMY;
import static seedu.track2gather.logic.commands.CommandTestUtil.WORK_ADDRESS_DESC_AMY;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.track2gather.commons.core.index.Index;
import seedu.track2gather.logic.commands.EditCommand;
import seedu.track2gather.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.track2gather.model.person.attributes.Address;
import seedu.track2gather.model.person.attributes.CaseNumber;
import seedu.track2gather.model.person.attributes.Email;
import seedu.track2gather.model.person.attributes.Name;
import seedu.track2gather.model.person.attributes.Period;
import seedu.track2gather.model.person.attributes.Phone;
import seedu.track2gather.testutil.EditPersonDescriptorBuilder;

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
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        // invalid case number
        assertParseFailure(parser, "1" + INVALID_CASE_NUMBER_DESC, CaseNumber.MESSAGE_CONSTRAINTS);
        // invalid homeAddress
        assertParseFailure(parser, "1" + INVALID_HOME_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);
        // invalid workAddress
        assertParseFailure(parser, "1" + INVALID_WORK_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);
        // invalid quarantineAddress
        assertParseFailure(parser, "1" + INVALID_QUARANTINE_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);
        // invalid shnPeriod
        assertParseFailure(parser, "1" + INVALID_SHN_PERIOD_DESC, Period.MESSAGE_CONSTRAINTS);
        // invalid nextOfKinName
        assertParseFailure(parser, "1" + INVALID_NEXT_OF_KIN_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid nextOfKinPhone
        assertParseFailure(parser, "1" + INVALID_NEXT_OF_KIN_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);
        // invalid nextOfKinAddress
        assertParseFailure(parser, "1" + INVALID_NEXT_OF_KIN_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_HOME_ADDRESS_AMY
                + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + CASE_NUMBER_DESC_AMY + HOME_ADDRESS_DESC_AMY + WORK_ADDRESS_DESC_AMY + QUARANTINE_ADDRESS_DESC_AMY
                + SHN_PERIOD_DESC_AMY + NEXT_OF_KIN_NAME_DESC_AMY + NEXT_OF_KIN_PHONE_DESC_AMY
                + NEXT_OF_KIN_ADDRESS_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withCaseNumber(VALID_CASE_NUMBER_AMY)
                .withHomeAddress(VALID_HOME_ADDRESS_AMY).withWorkAddress(VALID_WORK_ADDRESS_AMY)
                .withQuarantineAddress(VALID_QUARANTINE_ADDRESS_AMY).withShnPeriod(VALID_SHN_PERIOD_AMY)
                .withNextOfKinName(VALID_NEXT_OF_KIN_NAME_AMY).withNextOfKinPhone(VALID_NEXT_OF_KIN_PHONE_AMY)
                .withNextOfKinAddress(VALID_NEXT_OF_KIN_ADDRESS_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // case number
        userInput = targetIndex.getOneBased() + CASE_NUMBER_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withCaseNumber(VALID_CASE_NUMBER_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // homeAddress
        userInput = targetIndex.getOneBased() + HOME_ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withHomeAddress(VALID_HOME_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // workAddress
        userInput = targetIndex.getOneBased() + WORK_ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withWorkAddress(VALID_WORK_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // quarantineAddress
        userInput = targetIndex.getOneBased() + QUARANTINE_ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withQuarantineAddress(VALID_QUARANTINE_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // shnPeriod
        userInput = targetIndex.getOneBased() + SHN_PERIOD_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withShnPeriod(VALID_SHN_PERIOD_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // nextOfKinName
        userInput = targetIndex.getOneBased() + NEXT_OF_KIN_NAME_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withNextOfKinName(VALID_NEXT_OF_KIN_NAME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // nextOfKinPhone
        userInput = targetIndex.getOneBased() + NEXT_OF_KIN_PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withNextOfKinPhone(VALID_NEXT_OF_KIN_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // nextOfKinAddress
        userInput = targetIndex.getOneBased() + NEXT_OF_KIN_ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withNextOfKinAddress(VALID_NEXT_OF_KIN_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + EMAIL_DESC_AMY + CASE_NUMBER_DESC_AMY
                + HOME_ADDRESS_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + CASE_NUMBER_DESC_AMY + HOME_ADDRESS_DESC_AMY
                + EMAIL_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + CASE_NUMBER_DESC_BOB + HOME_ADDRESS_DESC_BOB;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withCaseNumber(VALID_CASE_NUMBER_BOB)
                .withHomeAddress(VALID_HOME_ADDRESS_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + HOME_ADDRESS_DESC_BOB
                + CASE_NUMBER_DESC_BOB + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withCaseNumber(VALID_CASE_NUMBER_BOB).withHomeAddress(VALID_HOME_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
