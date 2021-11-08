package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.SupplierCommandTestUtil.DELIVERY_DETAILS_DESC_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.DELIVERY_DETAILS_DESC_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.INVALID_DELIVERY_DETAILS_DESC;
import static seedu.address.logic.commands.SupplierCommandTestUtil.INVALID_SUPPLY_TYPE_DESC;
import static seedu.address.logic.commands.SupplierCommandTestUtil.SUPPLY_TYPE_DESC_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.SUPPLY_TYPE_DESC_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_DELIVERY_DETAIL_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_DELIVERY_DETAIL_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_SUPPLY_TYPE_BEEF;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_SUPPLY_TYPE_CHICKEN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SUPPLIER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SUPPLIER;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_SUPPLIER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditSupplierCommand;
import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.supplier.DeliveryDetails;
import seedu.address.model.person.supplier.SupplyType;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditSupplierDescriptorBuilder;

public class EditSupplierCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditSupplierCommand.MESSAGE_USAGE);

    private EditSupplierCommandParser parser = new EditSupplierCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditSupplierCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        // invalid supplyType
        assertParseFailure(parser, "1" + INVALID_SUPPLY_TYPE_DESC, SupplyType.MESSAGE_CONSTRAINTS);
        // invalid deliveryDetails
        assertParseFailure(parser, "1" + INVALID_DELIVERY_DETAILS_DESC, DeliveryDetails.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Supplier} being edited,
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
        Index targetIndex = INDEX_SECOND_SUPPLIER;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND
                + DELIVERY_DETAILS_DESC_BOB + SUPPLY_TYPE_DESC_AMY;

        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withSupplyType(VALID_SUPPLY_TYPE_CHICKEN)
                .withDeliveryDetails(VALID_DELIVERY_DETAIL_BOB).build();
        EditSupplierCommand expectedCommand = new EditSupplierCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_SUPPLIER;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditSupplierCommand expectedCommand = new EditSupplierCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_SUPPLIER;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditSupplierCommand expectedCommand = new EditSupplierCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditSupplierDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditSupplierCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditSupplierDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditSupplierCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditSupplierDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditSupplierCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditSupplierDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditSupplierCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // supply type
        userInput = targetIndex.getOneBased() + SUPPLY_TYPE_DESC_AMY;
        descriptor = new EditSupplierDescriptorBuilder().withSupplyType(VALID_SUPPLY_TYPE_CHICKEN).build();
        expectedCommand = new EditSupplierCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // delivery details
        userInput = targetIndex.getOneBased() + DELIVERY_DETAILS_DESC_AMY;
        descriptor = new EditSupplierDescriptorBuilder().withDeliveryDetails(VALID_DELIVERY_DETAIL_AMY).build();
        expectedCommand = new EditSupplierCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_SUPPLIER;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + SUPPLY_TYPE_DESC_AMY + DELIVERY_DETAILS_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_AMY
                + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + TAG_DESC_HUSBAND + SUPPLY_TYPE_DESC_BOB + DELIVERY_DETAILS_DESC_BOB;

        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withSupplyType(VALID_SUPPLY_TYPE_BEEF).withDeliveryDetails(VALID_DELIVERY_DETAIL_BOB)
                .build();
        EditSupplierCommand expectedCommand = new EditSupplierCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_SUPPLIER;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditSupplierCommand expectedCommand = new EditSupplierCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditSupplierDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditSupplierCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_SUPPLIER;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder().withTags().build();
        EditSupplierCommand expectedCommand = new EditSupplierCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
