package seedu.address.logic.parser.vendor;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.COST_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COST_DESC_NOT_DOUBLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COST_DESC_NOT_POSITIVE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OPERATING_HOURS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SERVICE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.OPERATING_HOURS_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.SERVICE_NAME_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CHEAP;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HIGH_RATINGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPERATING_HOURS_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERVICE_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CHEAP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_RATINGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENDOR_ID_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VENDOR_ID_DESC_DANIEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.guest.EditGuestCommand;
import seedu.address.logic.commands.vendor.EditVendorCommand;
import seedu.address.logic.commands.vendor.EditVendorCommand.EditVendorDescriptor;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.OperatingHours;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.VendorId;
import seedu.address.testutil.vendor.EditVendorDescriptorBuilder;

public class EditVendorCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditVendorCommand.MESSAGE_USAGE);

    private EditVendorCommandParser parser = new EditVendorCommandParser();

    @Test
    public void parse_validVendorIdArgs_returnsEditCommand() {
        EditVendorDescriptor editVendorDescriptor = new EditVendorDescriptor();
        editVendorDescriptor.setVendorId(VENDOR_ID_FIRST_PERSON);
        editVendorDescriptor.setCost(new Cost(Double.valueOf(VALID_COST_DANIEL)));
        assertParseSuccess(parser,
                EditGuestCommand.COMMAND_WORD
                        + VENDOR_ID_DESC_DANIEL
                        + COST_DESC_DANIEL,
                new EditVendorCommand(VENDOR_ID_FIRST_PERSON, editVendorDescriptor));
    }

    @Test
    public void parse_invalidValueForVendor_failure() {
        String partialUserInput = EditVendorCommand.COMMAND_WORD
                + " "
                + PREFIX_VENDOR_ID
                + VENDOR_ID_FIRST_PERSON;

        assertParseFailure(parser, "edit vid/", VendorId.MESSAGE_CONSTRAINTS); // invalid vendor id
        assertParseFailure(parser, partialUserInput + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, partialUserInput + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, partialUserInput + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, partialUserInput + INVALID_ADDRESS_DESC,
                Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, partialUserInput + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, partialUserInput + INVALID_COST_DESC_NOT_DOUBLE,
                Cost.INVALID_DOUBLE); // invalid cost (cost provided is not a double)
        assertParseFailure(parser, partialUserInput + INVALID_COST_DESC_NOT_POSITIVE,
                Cost.MESSAGE_CONSTRAINTS); // invalid cost (cost provided is not a double greater than 0.0)
        assertParseFailure(parser, partialUserInput + INVALID_SERVICE_NAME_DESC,
                ServiceName.MESSAGE_CONSTRAINTS); // invalid service name
        assertParseFailure(parser, partialUserInput + INVALID_OPERATING_HOURS_DESC,
                OperatingHours.MESSAGE_CONSTRAINTS); // invalid operating hours

        // invalid phone followed by valid email
        assertParseFailure(parser, partialUserInput + INVALID_PHONE_DESC + EMAIL_DESC_ALICE, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, partialUserInput + PHONE_DESC_DANIEL + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, partialUserInput + TAG_DESC_HIGH_RATINGS + TAG_DESC_CHEAP + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, partialUserInput + TAG_DESC_HIGH_RATINGS + TAG_EMPTY + TAG_DESC_CHEAP,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, partialUserInput + TAG_EMPTY + TAG_DESC_HIGH_RATINGS + TAG_DESC_CHEAP,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                partialUserInput + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_DANIEL + VALID_PHONE_DANIEL,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecifiedForVendor_success() {
        VendorId targetVendorId = new VendorId(VALID_VENDOR_ID_DANIEL);
        String userInput = EditVendorCommand.COMMAND_WORD
                + VENDOR_ID_DESC_DANIEL
                + PHONE_DESC_DANIEL
                + TAG_DESC_CHEAP
                + EMAIL_DESC_DANIEL
                + ADDRESS_DESC_DANIEL
                + NAME_DESC_DANIEL
                + TAG_DESC_HIGH_RATINGS
                + COST_DESC_DANIEL
                + OPERATING_HOURS_DESC_DANIEL
                + SERVICE_NAME_DESC_DANIEL;


        EditVendorDescriptor descriptor = new EditVendorDescriptorBuilder()
                .withVendorId(VALID_VENDOR_ID_DANIEL)
                .withName(VALID_NAME_DANIEL)
                .withPhone(VALID_PHONE_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .withAddress(VALID_ADDRESS_DANIEL)
                .withTags(VALID_TAG_CHEAP, VALID_TAG_HIGH_RATINGS)
                .withServiceName(VALID_SERVICE_NAME_DANIEL)
                .withCost(VALID_COST_DANIEL)
                .withOperatingHours(VALID_OPERATING_HOURS_DANIEL).build();
        EditVendorCommand expectedCommand = new EditVendorCommand(targetVendorId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecifiedForVendor_success() {
        VendorId targetVendorId = new VendorId(VALID_VENDOR_ID_DANIEL);
        String userInput = EditVendorCommand.COMMAND_WORD
                + VENDOR_ID_DESC_DANIEL
                + ADDRESS_DESC_DANIEL;

        EditVendorDescriptor descriptor = new EditVendorDescriptorBuilder()
                .withVendorId(VALID_VENDOR_ID_DANIEL)
                .withAddress(VALID_ADDRESS_DANIEL)
                .build();
        EditVendorCommand expectedCommand = new EditVendorCommand(targetVendorId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFieldsForVendor_acceptsLast() {
        VendorId targetVendorId = new VendorId(VALID_VENDOR_ID_DANIEL);
        String userInput = EditVendorCommand.COMMAND_WORD
                + VENDOR_ID_DESC_DANIEL + PHONE_DESC_DANIEL + ADDRESS_DESC_DANIEL + EMAIL_DESC_DANIEL
                + TAG_DESC_HIGH_RATINGS + PHONE_DESC_ELLE + ADDRESS_DESC_ELLE + EMAIL_DESC_ELLE + TAG_DESC_HIGH_RATINGS
                + PHONE_DESC_DANIEL + ADDRESS_DESC_DANIEL + EMAIL_DESC_DANIEL + TAG_DESC_CHEAP;

        EditVendorCommand.EditVendorDescriptor descriptor =
                new EditVendorDescriptorBuilder().withVendorId(VALID_VENDOR_ID_DANIEL)
                        .withPhone(VALID_PHONE_DANIEL).withEmail(VALID_EMAIL_DANIEL).withAddress(VALID_ADDRESS_DANIEL)
                        .withTags(VALID_TAG_HIGH_RATINGS, VALID_TAG_CHEAP).build();
        EditVendorCommand expectedCommand = new EditVendorCommand(targetVendorId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValueForVendor_success() {
        // no other valid values specified
        VendorId targetVendorId = new VendorId(VALID_VENDOR_ID_DANIEL);
        String userInput =
                EditVendorCommand.COMMAND_WORD + VENDOR_ID_DESC_DANIEL + INVALID_PHONE_DESC + PHONE_DESC_DANIEL;
        EditVendorDescriptor descriptor = new EditVendorDescriptorBuilder()
                .withVendorId(VALID_VENDOR_ID_DANIEL)
                .withPhone(VALID_PHONE_DANIEL)
                .build();
        EditVendorCommand expectedCommand = new EditVendorCommand(targetVendorId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = EditVendorCommand.COMMAND_WORD + VENDOR_ID_DESC_DANIEL + EMAIL_DESC_DANIEL + INVALID_PHONE_DESC
                + ADDRESS_DESC_DANIEL + PHONE_DESC_DANIEL;
        descriptor = new EditVendorDescriptorBuilder()
                .withVendorId(VALID_VENDOR_ID_DANIEL)
                .withPhone(VALID_PHONE_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .withAddress(VALID_ADDRESS_DANIEL)
                .build();
        expectedCommand = new EditVendorCommand(targetVendorId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTagsForVendor_success() {
        VendorId targetVendorId = new VendorId(VALID_VENDOR_ID_DANIEL);
        String userInput = EditVendorCommand.COMMAND_WORD + VENDOR_ID_DESC_DANIEL + TAG_EMPTY;

        EditVendorDescriptor descriptor =
                new EditVendorDescriptorBuilder().withVendorId(VALID_VENDOR_ID_DANIEL).withTags().build();
        EditVendorCommand expectedCommand = new EditVendorCommand(targetVendorId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
