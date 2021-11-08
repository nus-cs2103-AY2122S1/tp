package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SORT_ORDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SORT_BY_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SORT_BY_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SORT_BY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SORT_BY_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SORT_DESC_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.SORT_DESC_DESCENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_ORDER_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_ORDER_DESCENDING;
import static seedu.address.logic.commands.SupplierCommandTestUtil.INVALID_SORT_BY_DESC;
import static seedu.address.logic.commands.SupplierCommandTestUtil.SORT_BY_DELIVERY_DETAILS_DESC;
import static seedu.address.logic.commands.SupplierCommandTestUtil.SORT_BY_SUPPLY_TYPE_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortSupplierCommand;
import seedu.address.model.person.SortOrder;
import seedu.address.model.person.supplier.SortBySupplier;
import seedu.address.model.person.supplier.SupplierComparator;

public class SortSupplierCommandParserTest {

    private SortSupplierCommandParser parser = new SortSupplierCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SORT_BY_NAME_DESC + SORT_DESC_ASCENDING,
                new SortSupplierCommand(SupplierComparator.getNameComparator(true),
                        "name", "ascending"));

        // multiple sort by's - last sort by accepted
        assertParseSuccess(parser, SORT_BY_NAME_DESC + SORT_DESC_DESCENDING + SORT_BY_DELIVERY_DETAILS_DESC,
                new SortSupplierCommand(SupplierComparator.getDeliveryDetailsComparator(false),
                        "delivery details", "descending"));

        // multiple sorting orders - last sorting order accepted
        assertParseSuccess(parser, SORT_BY_SUPPLY_TYPE_DESC + SORT_DESC_DESCENDING + SORT_DESC_ASCENDING,
                new SortSupplierCommand(SupplierComparator.getSupplyTypeComparator(true),
                        "supply type", "ascending"));

        // parse sort by with address in ascending order
        assertParseSuccess(parser, SORT_BY_ADDRESS_DESC + SORT_DESC_ASCENDING,
                new SortSupplierCommand(SupplierComparator.getSupplyTypeComparator(true),
                        "address", "ascending"));

        // parse sort by with email in decreasing order
        assertParseSuccess(parser, SORT_BY_EMAIL_DESC + SORT_DESC_DESCENDING,
                new SortSupplierCommand(SupplierComparator.getSupplyTypeComparator(true),
                        "email", "descending"));

        // parse sort by with phone in descending order
        assertParseSuccess(parser, SORT_BY_PHONE_DESC + SORT_DESC_DESCENDING,
                new SortSupplierCommand(SupplierComparator.getSupplyTypeComparator(true),
                        "phone", "descending"));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortSupplierCommand.MESSAGE_USAGE);

        // missing sort by prefix
        assertParseFailure(parser, VALID_SORT_BY_NAME + SORT_DESC_DESCENDING, expectedMessage);

        // missing sorting order prefix
        assertParseFailure(parser, SORT_BY_PHONE_DESC + VALID_SORT_ORDER_DESCENDING, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_SORT_BY_ADDRESS + VALID_SORT_ORDER_ASCENDING, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid sort by
        assertParseFailure(parser, INVALID_SORT_BY_DESC + SORT_DESC_ASCENDING,
                SortBySupplier.MESSAGE_CONSTRAINTS);

        // invalid sorting order
        assertParseFailure(parser, SORT_BY_EMAIL_DESC + INVALID_SORT_ORDER_DESC,
                SortOrder.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value will be reported
        assertParseFailure(parser, INVALID_SORT_BY_DESC + INVALID_SORT_ORDER_DESC,
                SortBySupplier.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + SORT_BY_ADDRESS_DESC + SORT_DESC_DESCENDING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortSupplierCommand.MESSAGE_USAGE));
    }
}
