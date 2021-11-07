package seedu.address.logic.parser.vendor;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_CARL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VENDOR_ID_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VENDOR_ID_DESC_ELLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.vendor.FilterVendorCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.VendorPredicate;

public class FilterVendorCommandParserTest {

    private FilterVendorCommandParser parser = new FilterVendorCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterVendorCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongArguments_throwsParseException() {
        assertParseFailure(parser,
                FilterVendorCommand.COMMAND_WORD + " " + VENDOR_ID_DESC_DANIEL + " " + VENDOR_ID_DESC_ELLE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterVendorCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreWrongArguments_throwsParseException() {
        assertParseFailure(parser,
                FilterVendorCommand.COMMAND_WORD + " " + PASSPORT_NUMBER_DESC_ALICE + " " + PASSPORT_NUMBER_DESC_CARL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterVendorCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_prefixButNoArg_throwsParseException() {
        assertParseFailure(parser, FilterVendorCommand.COMMAND_WORD
                        + " " + PREFIX_TAG,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterVendorCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterVendorCommand expectedFilterCommand =
                new FilterVendorCommand(
                        new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                                Optional.of(Set.of(new Tag("Vip"))), Optional.empty(), Optional.empty(),
                                Optional.empty(), Optional.empty()));
        assertParseSuccess(parser, TAG_DESC_ALICE, expectedFilterCommand);

        // whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_TAG + "     " + VALID_TAG_ALICE, expectedFilterCommand);
    }

}
