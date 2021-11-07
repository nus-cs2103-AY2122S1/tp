package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.Name;
import seedu.address.testutil.ItemDescriptorBuilder;

public class DeleteCommandParserTest {
    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_nameAndId_success() {
        ItemDescriptor expectedDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();
        assertParseSuccess(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL, new DeleteCommand(expectedDescriptor));
    }

    @Test
    public void parse_nameOnly_success() {
        ItemDescriptor expectedDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).build();

        assertParseSuccess(parser, VALID_NAME_BAGEL, new DeleteCommand(expectedDescriptor));
    }

    @Test
    public void parse_idOnly_success() {
        ItemDescriptor expectedDescriptor = new ItemDescriptorBuilder()
                .withId(VALID_ID_BAGEL).build();

        assertParseSuccess(parser, ID_DESC_BAGEL, new DeleteCommand(expectedDescriptor));
    }

    @Test
    public void parse_noNameNorId_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);

        // both name and id prefix missing
        assertParseFailure(parser, COUNT_DESC_BAGEL, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_SPECIAL_CHAR + ID_DESC_BAGEL, Name.MESSAGE_CONSTRAINTS);

        // invalid id with negative number
        assertParseFailure(parser, VALID_NAME_BAGEL + INVALID_ID_NEGATIVE_NUMBER,
                Messages.MESSAGE_INVALID_ID_LENGTH_AND_SIGN);
    }

    @Test
    public void parse_extraFlag_failure() {
        // extra Flag
        assertParseFailure(parser, VALID_NAME_BAGEL + ID_DESC_BAGEL + COSTPRICE_DESC_BAGEL,
                DeleteCommandParser.EXTRA_FLAGS_PRESENT);
    }
}
