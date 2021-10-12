package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SupplierCommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.DELIVERY_DETAILS_DESC_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.DELIVERY_DETAILS_DESC_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.SupplierCommandTestUtil.INVALID_DELIVERY_DETAILS_DESC;
import static seedu.address.logic.commands.SupplierCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.SupplierCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.SupplierCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.SupplierCommandTestUtil.INVALID_SUPPLY_TYPE_DESC;
import static seedu.address.logic.commands.SupplierCommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.SupplierCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.SupplierCommandTestUtil.SUPPLY_TYPE_DESC_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.SUPPLY_TYPE_DESC_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.SupplierCommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_DELIVERY_DETAIL_MONTHLY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_SUPPLY_TYPE_BEEF;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalSuppliers.AMY;
import static seedu.address.testutil.TypicalSuppliers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.supplier.DeliveryDetails;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.person.supplier.SupplyType;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.SupplierBuilder;

public class AddSupplierCommandParserTest {
    private AddSupplierCommandParser parser = new AddSupplierCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Supplier expectedSupplier = new SupplierBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                new AddSupplierCommand(expectedSupplier));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                new AddSupplierCommand(expectedSupplier));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                new AddSupplierCommand(expectedSupplier));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                new AddSupplierCommand(expectedSupplier));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                new AddSupplierCommand(expectedSupplier));

        // multiple tags - all accepted
        Supplier expectedSupplierMultipleTags = new SupplierBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                new AddSupplierCommand(expectedSupplierMultipleTags));

        // multiple supplyTypes - last supplyType accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_AMY + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                new AddSupplierCommand(expectedSupplier));

        // multiple deliveryDetails - last deliveryDetail accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_AMY
                        + DELIVERY_DETAILS_DESC_BOB,
                new AddSupplierCommand(expectedSupplier));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Supplier expectedSupplier = new SupplierBuilder(AMY).withTags().build();
        assertParseSuccess(parser,
                NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + SUPPLY_TYPE_DESC_AMY + DELIVERY_DETAILS_DESC_AMY,
                new AddSupplierCommand(expectedSupplier));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSupplierCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                        + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                        + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                expectedMessage);

        // missing supplyType prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_SUPPLY_TYPE_BEEF + DELIVERY_DETAILS_DESC_BOB,
                expectedMessage);

        // missing deliveryDetails prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + SUPPLY_TYPE_DESC_BOB + VALID_DELIVERY_DETAIL_MONTHLY,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                        + VALID_SUPPLY_TYPE_BEEF + VALID_DELIVERY_DETAIL_MONTHLY,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid supplyType
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + INVALID_SUPPLY_TYPE_DESC + DELIVERY_DETAILS_DESC_BOB,
                SupplyType.MESSAGE_CONSTRAINTS);

        // invalid deliveryDetails
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SUPPLY_TYPE_DESC_BOB + INVALID_DELIVERY_DETAILS_DESC,
                DeliveryDetails.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                        + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSupplierCommand.MESSAGE_USAGE));
    }
}
