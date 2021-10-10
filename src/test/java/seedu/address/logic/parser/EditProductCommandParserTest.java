package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_UNIT_PRICE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.UNIT_PRICE_DESC_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.UNIT_PRICE_DESC_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_DAISY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PRODUCT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditProductCommand;
import seedu.address.logic.commands.EditProductCommand.EditProductDescriptor;
import seedu.address.model.product.Name;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.UnitPrice;
import seedu.address.testutil.EditProductDescriptorBuilder;

public class EditProductCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProductCommand.MESSAGE_USAGE);

    private EditProductCommandParser parser = new EditProductCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_CANNON, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditProductCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_CANNON, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_CANNON, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 -i 2", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name, unit price, quantity, address
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_UNIT_PRICE_DESC, UnitPrice.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_QUANTITY_DESC, Quantity.MESSAGE_CONSTRAINTS);

        // invalid unit price followed by valid quantity
        assertParseFailure(parser, "1" + INVALID_UNIT_PRICE_DESC + QUANTITY_DESC_CANNON,
                UnitPrice.MESSAGE_CONSTRAINTS);

        // valid unit price followed by invalid unit price. The test case for invalid unit price followed by valid
        // unit price is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + UNIT_PRICE_DESC_DAISY + INVALID_UNIT_PRICE_DESC,
                UnitPrice.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + INVALID_NAME_DESC + INVALID_QUANTITY_DESC + VALID_UNIT_PRICE_CANNON,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PRODUCT;
        String userInput = targetIndex.getOneBased() + UNIT_PRICE_DESC_DAISY + QUANTITY_DESC_CANNON + NAME_DESC_CANNON;

        EditProductDescriptor descriptor = new EditProductDescriptorBuilder()
                .withName(VALID_NAME_CANNON)
                .withUnitPrice(VALID_UNIT_PRICE_DAISY)
                .withQuantity(VALID_QUANTITY_CANNON)
                .build();
        EditProductCommand expectedCommand = new EditProductCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PRODUCT;
        String userInput = targetIndex.getOneBased() + QUANTITY_DESC_CANNON + UNIT_PRICE_DESC_DAISY;

        EditProductDescriptor descriptor = new EditProductDescriptorBuilder()
                .withUnitPrice(VALID_UNIT_PRICE_DAISY)
                .withQuantity(VALID_QUANTITY_CANNON)
                .build();
        EditProductCommand expectedCommand = new EditProductCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PRODUCT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_CANNON;
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder().withName(VALID_NAME_CANNON).build();
        EditProductCommand expectedCommand = new EditProductCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // unit price
        userInput = targetIndex.getOneBased() + UNIT_PRICE_DESC_CANNON;
        descriptor = new EditProductDescriptorBuilder().withUnitPrice(VALID_UNIT_PRICE_CANNON).build();
        expectedCommand = new EditProductCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        userInput = targetIndex.getOneBased() + QUANTITY_DESC_CANNON;
        descriptor = new EditProductDescriptorBuilder().withQuantity(VALID_QUANTITY_CANNON).build();
        expectedCommand = new EditProductCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PRODUCT;
        String userInput = targetIndex.getOneBased()
                                   + NAME_DESC_CANNON + UNIT_PRICE_DESC_CANNON + QUANTITY_DESC_CANNON
                                   + NAME_DESC_CANNON + UNIT_PRICE_DESC_CANNON + QUANTITY_DESC_CANNON
                                   + NAME_DESC_DAISY + UNIT_PRICE_DESC_DAISY + QUANTITY_DESC_DAISY;

        EditProductDescriptor descriptor = new EditProductDescriptorBuilder()
                .withName(VALID_NAME_DAISY)
                .withUnitPrice(VALID_UNIT_PRICE_DAISY)
                .withQuantity(VALID_QUANTITY_DAISY)
                .build();
        EditProductCommand expectedCommand = new EditProductCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PRODUCT;
        String userInput = targetIndex.getOneBased() + INVALID_UNIT_PRICE_DESC + UNIT_PRICE_DESC_DAISY;
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder()
                .withUnitPrice(VALID_UNIT_PRICE_DAISY)
                .build();
        EditProductCommand expectedCommand = new EditProductCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_UNIT_PRICE_DESC + NAME_DESC_DAISY + UNIT_PRICE_DESC_DAISY
                            + QUANTITY_DESC_DAISY;
        descriptor = new EditProductDescriptorBuilder()
                .withName(VALID_NAME_DAISY)
                .withUnitPrice(VALID_UNIT_PRICE_DAISY)
                .withQuantity(VALID_QUANTITY_DAISY)
                .build();
        expectedCommand = new EditProductCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
