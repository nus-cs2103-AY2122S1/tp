package seedu.address.logic.parser.vendor;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.COST_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.COST_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COST_DESC_NOT_POSITIVE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OPERATING_HOURS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SERVICE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VENDOR_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.OPERATING_HOURS_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.OPERATING_HOURS_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SERVICE_NAME_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.SERVICE_NAME_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CHEAP;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HIGH_RATINGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPERATING_HOURS_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERVICE_NAME_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CHEAP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_RATINGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENDOR_ID_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VENDOR_ID_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VENDOR_ID_DESC_ELLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.vendor.TypicalVendors.DANIEL_VENDOR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.vendor.AddVendorCommand;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.OperatingHours;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorId;
import seedu.address.testutil.vendor.VendorBuilder;

public class AddVendorCommandParserTest {

    private AddVendorCommandParser parser = new AddVendorCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        Vendor expectedVendor =
                new VendorBuilder(DANIEL_VENDOR).withTags(VALID_TAG_HIGH_RATINGS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL
                + ADDRESS_DESC_DANIEL + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_DANIEL + COST_DESC_DANIEL
                + OPERATING_HOURS_DESC_DANIEL + SERVICE_NAME_DESC_DANIEL, new AddVendorCommand(expectedVendor));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_ELLE + NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL
                        + ADDRESS_DESC_DANIEL + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_DANIEL + COST_DESC_DANIEL
                        + OPERATING_HOURS_DESC_DANIEL + SERVICE_NAME_DESC_DANIEL,
                new AddVendorCommand(expectedVendor));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_ELLE + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL
                        + ADDRESS_DESC_DANIEL + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_DANIEL + COST_DESC_DANIEL
                        + OPERATING_HOURS_DESC_DANIEL + SERVICE_NAME_DESC_DANIEL,
                new AddVendorCommand(expectedVendor));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_ELLE + EMAIL_DESC_DANIEL
                        + ADDRESS_DESC_DANIEL + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_DANIEL + COST_DESC_DANIEL
                        + OPERATING_HOURS_DESC_DANIEL + SERVICE_NAME_DESC_DANIEL,
                new AddVendorCommand(expectedVendor));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL + ADDRESS_DESC_ELLE
                        + ADDRESS_DESC_DANIEL + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_DANIEL + COST_DESC_DANIEL
                        + OPERATING_HOURS_DESC_DANIEL + SERVICE_NAME_DESC_DANIEL,
                new AddVendorCommand(expectedVendor));

        // multiple vendor id - last vendorId accepted
        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL
                        + ADDRESS_DESC_DANIEL + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_ELLE + VENDOR_ID_DESC_DANIEL
                        + COST_DESC_DANIEL + OPERATING_HOURS_DESC_DANIEL + SERVICE_NAME_DESC_DANIEL,
                new AddVendorCommand(expectedVendor));

        // multiple cost - last cost accepted
        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL
                        + ADDRESS_DESC_DANIEL + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_DANIEL + COST_DESC_ELLE
                        + COST_DESC_DANIEL + OPERATING_HOURS_DESC_DANIEL + SERVICE_NAME_DESC_DANIEL,
                new AddVendorCommand(expectedVendor));

        // multiple serviceName - last serviceName accepted
        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL
                        + ADDRESS_DESC_DANIEL + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_DANIEL + COST_DESC_DANIEL
                        + OPERATING_HOURS_DESC_DANIEL + SERVICE_NAME_DESC_ELLE + SERVICE_NAME_DESC_DANIEL,
                new AddVendorCommand(expectedVendor));

        // multiple operating hours - last operating hours accepted
        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL
                        + ADDRESS_DESC_DANIEL + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_DANIEL + COST_DESC_DANIEL
                        + OPERATING_HOURS_DESC_ELLE + OPERATING_HOURS_DESC_DANIEL + SERVICE_NAME_DESC_DANIEL,
                new AddVendorCommand(expectedVendor));


        // multiple tags - all accepted
        Vendor expectedVendorMultipleTags = new VendorBuilder(DANIEL_VENDOR).withTags(VALID_TAG_HIGH_RATINGS,
                VALID_TAG_DANIEL, VALID_TAG_CHEAP).build();

        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL + ADDRESS_DESC_DANIEL
                        + TAG_DESC_HIGH_RATINGS + TAG_DESC_CHEAP + TAG_DESC_DANIEL + VENDOR_ID_DESC_DANIEL
                        + COST_DESC_DANIEL + OPERATING_HOURS_DESC_DANIEL + SERVICE_NAME_DESC_DANIEL,
                new AddVendorCommand(expectedVendorMultipleTags));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Vendor expectedVendor = new VendorBuilder(DANIEL_VENDOR).withTags().build();
        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL
                        + ADDRESS_DESC_DANIEL + VENDOR_ID_DESC_DANIEL + COST_DESC_DANIEL
                        + OPERATING_HOURS_DESC_DANIEL + SERVICE_NAME_DESC_DANIEL,
                new AddVendorCommand(expectedVendor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVendorCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                        + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_ELLE + VALID_PHONE_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                        + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + VALID_EMAIL_ELLE + ADDRESS_DESC_ELLE
                        + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + VALID_ADDRESS_ELLE
                        + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE,
                expectedMessage);

        // missing vendor id prefix
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                        + VALID_VENDOR_ID_ELLE + COST_DESC_ELLE + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE,
                expectedMessage);

        // missing cost prefix
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                        + VENDOR_ID_DESC_ELLE + VALID_COST_ELLE + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE,
                expectedMessage);

        // missing service name prefix
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                        + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE + OPERATING_HOURS_DESC_ELLE + VALID_SERVICE_NAME_ELLE,
                expectedMessage);

        // missing operating hours prefix
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                        + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE + VALID_OPERATING_HOURS_ELLE + SERVICE_NAME_DESC_ELLE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                VALID_NAME_ELLE + VALID_PHONE_ELLE + VALID_EMAIL_ELLE + VALID_ADDRESS_ELLE + VENDOR_ID_DESC_ELLE
                        + VALID_COST_ELLE + VALID_OPERATING_HOURS_ELLE + VALID_SERVICE_NAME_ELLE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                + TAG_DESC_CHEAP + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE
                + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_ELLE + INVALID_PHONE_DESC + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                + TAG_DESC_CHEAP + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE
                + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + INVALID_EMAIL_DESC + ADDRESS_DESC_ELLE
                + TAG_DESC_CHEAP + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE
                + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + INVALID_ADDRESS_DESC
                + TAG_DESC_CHEAP + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE
                + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                + INVALID_TAG_DESC + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE
                + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE, Tag.MESSAGE_CONSTRAINTS);

        // invalid vendor id
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                + TAG_DESC_CHEAP + TAG_DESC_HIGH_RATINGS + INVALID_VENDOR_ID_DESC + COST_DESC_ELLE
                + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE, VendorId.MESSAGE_CONSTRAINTS);

        // invalid cost
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                + TAG_DESC_CHEAP + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_ELLE + INVALID_COST_DESC_NOT_POSITIVE
                + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE, Cost.MESSAGE_CONSTRAINTS);

        // invalid service name
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                + TAG_DESC_CHEAP + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE
                + OPERATING_HOURS_DESC_ELLE + INVALID_SERVICE_NAME_DESC, ServiceName.MESSAGE_CONSTRAINTS);

        // invalid operating hours
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                + TAG_DESC_CHEAP + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE
                + INVALID_OPERATING_HOURS_DESC + SERVICE_NAME_DESC_ELLE, OperatingHours.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + INVALID_ADDRESS_DESC
                        + VENDOR_ID_DESC_ELLE + COST_DESC_ELLE + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE
                        + ADDRESS_DESC_ELLE + TAG_DESC_CHEAP + TAG_DESC_HIGH_RATINGS + VENDOR_ID_DESC_ELLE
                        + COST_DESC_ELLE + OPERATING_HOURS_DESC_ELLE + SERVICE_NAME_DESC_ELLE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVendorCommand.MESSAGE_USAGE));
    }

}
