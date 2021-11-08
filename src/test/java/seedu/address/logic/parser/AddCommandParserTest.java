package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COSTPRICE_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.COUNT_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.COUNT_DESC_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COUNT_LETTER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COUNT_NEGATIVE_VALUE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COUNT_ZERO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_NEGATIVE_NUMBER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_SPECIAL_CHAR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SALESPRICE_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BAKED;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_POPULAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNT_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BAKED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POPULAR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItems.BAGEL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ItemDescriptorBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ItemDescriptor expectedDescriptor = new ItemDescriptorBuilder(BAGEL).withTags(VALID_TAG_BAKED).build();

        // All fields
        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + COUNT_DESC_BAGEL + COSTPRICE_DESC_BAGEL
                + SALESPRICE_DESC_BAGEL + TAG_DESC_BAKED, new AddCommand(expectedDescriptor));

        // multiple id - last id accepted
        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_DONUT + ID_DESC_BAGEL + COUNT_DESC_BAGEL
                + COSTPRICE_DESC_BAGEL + SALESPRICE_DESC_BAGEL + TAG_DESC_BAKED, new AddCommand(expectedDescriptor));

        // multiple count - last count accepted
        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + COUNT_DESC_DONUT + COUNT_DESC_BAGEL
                + COSTPRICE_DESC_BAGEL + SALESPRICE_DESC_BAGEL + TAG_DESC_BAKED, new AddCommand(expectedDescriptor));

        // multiple tags - all accepted
        ItemDescriptor multipleTagsDescriptor =
                new ItemDescriptorBuilder(BAGEL).withTags(VALID_TAG_POPULAR, VALID_TAG_BAKED).build();
        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + COUNT_DESC_BAGEL
                + COSTPRICE_DESC_BAGEL + SALESPRICE_DESC_BAGEL + TAG_DESC_POPULAR + TAG_DESC_BAKED,
                new AddCommand(multipleTagsDescriptor));
    }

    @Test
    public void parse_nameOnlyNoId_success() {
        ItemDescriptor expectedDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withCount(VALID_COUNT_BAGEL)
                .build();

        assertParseSuccess(parser, VALID_NAME_BAGEL + COUNT_DESC_BAGEL,
                new AddCommand(expectedDescriptor));
    }

    @Test
    public void parse_idOnlyNoName_success() {
        ItemDescriptor expectedDescriptor = new ItemDescriptorBuilder()
                .withId(VALID_ID_BAGEL)
                .withCount(VALID_COUNT_BAGEL)
                .build();

        assertParseSuccess(parser, ID_DESC_BAGEL + COUNT_DESC_BAGEL,
                new AddCommand(expectedDescriptor));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {

        // zero tags;
        ItemDescriptor expectedDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_BAGEL)
                .withCount(VALID_COUNT_BAGEL)
                .build();

        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + COUNT_DESC_BAGEL,
                new AddCommand(expectedDescriptor));

        // no count (count should be defaulted to 1)
        expectedDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_BAGEL)
                .withCount(1)
                .build();
        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL,
                new AddCommand(expectedDescriptor));

    }

    @Test
    public void parse_noNameOrId_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // both name and id prefix missing
        assertParseFailure(parser, COUNT_DESC_BAGEL, expectedMessage);
    }

    @Test
    public void parse_countZero_failure() {
        assertParseFailure(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + INVALID_COUNT_ZERO,
                Messages.MESSAGE_INVALID_COUNT_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_SPECIAL_CHAR + ID_DESC_BAGEL + COUNT_DESC_BAGEL
                + TAG_DESC_POPULAR + TAG_DESC_BAKED, Name.MESSAGE_CONSTRAINTS);

        // invalid id with negative number
        assertParseFailure(parser, VALID_NAME_BAGEL + INVALID_ID_NEGATIVE_NUMBER + COUNT_DESC_BAGEL
                + TAG_DESC_POPULAR + TAG_DESC_BAKED, Messages.MESSAGE_INVALID_ID_LENGTH_AND_SIGN);

        // invalid tag
        assertParseFailure(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + COUNT_DESC_BAGEL
                + INVALID_TAG_DESC + VALID_TAG_BAKED, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_SPECIAL_CHAR + ID_DESC_BAGEL + COUNT_DESC_BAGEL
                + INVALID_TAG_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid count format
        assertParseFailure(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + INVALID_COUNT_LETTER + TAG_DESC_BAKED,
                Messages.MESSAGE_INVALID_COUNT_FORMAT);

        // invalid count value
        assertParseFailure(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + INVALID_COUNT_NEGATIVE_VALUE + TAG_DESC_BAKED,
                Messages.MESSAGE_INVALID_COUNT_FORMAT);
    }


}
