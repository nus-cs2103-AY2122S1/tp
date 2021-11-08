package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ORDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ORDER_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.ORDER_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_NUMBER_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClientCommand;
import seedu.address.logic.commands.EditClientCommand.EditClientDescriptor;
import seedu.address.model.ModelManager;
import seedu.address.model.client.Address;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.ID;
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Product;
import seedu.address.testutil.EditClientDescriptorBuilder;
import seedu.address.testutil.TypicalProducts;

public class EditClientCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClientCommand.MESSAGE_USAGE);

    private final EditClientCommandParser parser = new EditClientCommandParser(new ModelStub());

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, Index.MESSAGE_CONSTRAINTS);

        // no field specified
        assertParseFailure(parser, "1", EditClientCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, Index.MESSAGE_CONSTRAINTS);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, Index.MESSAGE_CONSTRAINTS);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", Index.MESSAGE_CONSTRAINTS);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 -id 2", Index.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name, phone number, email, address, orders
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_PHONE_NUMBER_DESC, PhoneNumber.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_ORDER_DESC, Order.MESSAGE_CONSTRAINTS);

        // invalid phone number followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_NUMBER_DESC + EMAIL_DESC_AMY,
                PhoneNumber.MESSAGE_CONSTRAINTS);

        // valid phone number followed by invalid phone number. The test case for invalid phone number followed by valid
        // phone number is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_NUMBER_DESC_BOB + INVALID_PHONE_NUMBER_DESC,
                PhoneNumber.MESSAGE_CONSTRAINTS);

        // many valid orders with a single invalid order will result in error
        assertParseFailure(parser, "1" + ORDER_DESC_ONE + ORDER_DESC_TWO + INVALID_ORDER_DESC,
                Order.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + ORDER_DESC_ONE + INVALID_ORDER_DESC + ORDER_DESC_TWO,
                Order.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_ORDER_DESC + ORDER_DESC_ONE + ORDER_DESC_TWO,
                Order.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_NUMBER_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_CLIENT;
        String userInput = targetIndex.getOneBased() + ORDER_DESC_TWO + PHONE_NUMBER_DESC_BOB + EMAIL_DESC_AMY
                                   + ADDRESS_DESC_AMY + NAME_DESC_AMY + ORDER_DESC_ONE;

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withPhoneNumber(VALID_PHONE_NUMBER_BOB)
                .withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withOrders(VALID_ORDER_ONE, VALID_ORDER_TWO)
                .build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + PHONE_NUMBER_DESC_BOB + EMAIL_DESC_AMY;

        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withPhoneNumber(VALID_PHONE_NUMBER_BOB)
                .withEmail(VALID_EMAIL_AMY)
                .build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_CLIENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone number
        userInput = targetIndex.getOneBased() + PHONE_NUMBER_DESC_AMY;
        descriptor = new EditClientDescriptorBuilder().withPhoneNumber(VALID_PHONE_NUMBER_AMY).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditClientDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditClientDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // order
        userInput = targetIndex.getOneBased() + ORDER_DESC_ONE;
        descriptor = new EditClientDescriptorBuilder().withOrders(VALID_ORDER_ONE).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased()
                                   + NAME_DESC_AMY + PHONE_NUMBER_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                                   + ORDER_DESC_ONE + NAME_DESC_AMY + PHONE_NUMBER_DESC_AMY + EMAIL_DESC_AMY
                                   + ADDRESS_DESC_AMY + ORDER_DESC_ONE + NAME_DESC_BOB + PHONE_NUMBER_DESC_BOB
                                   + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + ORDER_DESC_TWO;

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhoneNumber(VALID_PHONE_NUMBER_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withOrders(VALID_ORDER_ONE, VALID_ORDER_TWO)
                .build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_NUMBER_DESC + PHONE_NUMBER_DESC_BOB;
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withPhoneNumber(VALID_PHONE_NUMBER_BOB)
                .build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_PHONE_NUMBER_DESC + ADDRESS_DESC_BOB + PHONE_NUMBER_DESC_BOB
                            + EMAIL_DESC_BOB + NAME_DESC_BOB;
        descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhoneNumber(VALID_PHONE_NUMBER_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


    public static class ModelStub extends ModelManager {
        private final ID idCannon = TypicalProducts.CANNON.getId();
        private final ID idDaisy = TypicalProducts.DAISY.getId();

        @Override
        public boolean hasProduct(ID id) {
            return id.equals(idCannon) || id.equals(idDaisy);
        }

        @Override
        public Product getProductById(ID id) {
            if (id.equals(idCannon)) {
                return TypicalProducts.CANNON;
            }

            if (id.equals(idDaisy)) {
                return TypicalProducts.DAISY;
            }

            return null;
        }
    }
}
