package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.Name;
import seedu.address.testutil.ItemDescriptorBuilder;

public class RemoveCommandParserTest {
    private RemoveCommandParser parser = new RemoveCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ItemDescriptor expectedDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).withCount(VALID_COUNT_BAGEL).
                        withCostPrice(VALID_COSTPRICE_BAGEL).withSalesPrice(VALID_SALESPRICE_BAGEL).build();

        // All fields
        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + COUNT_DESC_BAGEL
                        + COSTPRICE_DESC_BAGEL + SALESPRICE_DESC_BAGEL,
                new RemoveCommand(expectedDescriptor));

        // Extraneous flags
        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + COUNT_DESC_BAGEL
                        + COSTPRICE_DESC_BAGEL + SALESPRICE_DESC_BAGEL,
                new RemoveCommand(expectedDescriptor));

        // multiple id - last id accepted
        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_DONUT + ID_DESC_BAGEL + COUNT_DESC_BAGEL
                        + COSTPRICE_DESC_BAGEL + SALESPRICE_DESC_BAGEL,
                new RemoveCommand(expectedDescriptor));

        // multiple count - last count accepted
        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + COUNT_DESC_DONUT
                + COUNT_DESC_BAGEL + COSTPRICE_DESC_BAGEL + SALESPRICE_DESC_BAGEL,
                new RemoveCommand(expectedDescriptor));
    }

    @Test
    public void parse_nameOnlyNoId_success() {
        ItemDescriptor expectedDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withCount(VALID_COUNT_BAGEL)
                .build();

        assertParseSuccess(parser, VALID_NAME_BAGEL + COUNT_DESC_BAGEL,
                new RemoveCommand(expectedDescriptor));
    }

    @Test
    public void parse_idOnlyNoName_success() {
        ItemDescriptor expectedDescriptor = new ItemDescriptorBuilder()
                .withId(VALID_ID_BAGEL)
                .withCount(VALID_COUNT_BAGEL)
                .build();

        assertParseSuccess(parser, ID_DESC_BAGEL + COUNT_DESC_BAGEL,
                new RemoveCommand(expectedDescriptor));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {

        // no count (count should be defaulted to 1)
        ItemDescriptor expectedDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_BAGEL)
                .withCount(1)
                .build();
        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL,
                new RemoveCommand(expectedDescriptor));

    }

    @Test
    public void parse_noNameOrId_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE);

        // both name and id prefix missing
        assertParseFailure(parser, COUNT_DESC_BAGEL, expectedMessage);
    }

    @Test
    public void parse_countZero_failure() {
        assertParseFailure(parser, VALID_NAME_BAGEL + " " + ID_DESC_BAGEL + " " + INVALID_COUNT_ZERO,
                Messages.MESSAGE_INVALID_COUNT_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_SPECIAL_CHAR + ID_DESC_BAGEL + COUNT_DESC_BAGEL,
                Name.MESSAGE_CONSTRAINTS);

        // invalid id with negative number
        assertParseFailure(parser, VALID_NAME_BAGEL + INVALID_ID_NEGATIVE_NUMBER + COUNT_DESC_BAGEL,
                Messages.MESSAGE_INVALID_ID_LENGTH_AND_SIGN);

        // invalid count format
        assertParseFailure(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + INVALID_COUNT_LETTER,
                Messages.MESSAGE_INVALID_COUNT_FORMAT);

        // invalid count value
        assertParseFailure(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + INVALID_COUNT_NEGATIVE_VALUE,
                Messages.MESSAGE_INVALID_COUNT_FORMAT);
    }
}
