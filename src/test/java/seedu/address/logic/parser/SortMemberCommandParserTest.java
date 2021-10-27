package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SORT_ORDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SORT_ORDER_DESC_NAME;
import static seedu.address.logic.commands.CommandTestUtil.SORT_ORDER_DESC_TAG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_ORDER_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortMemberCommand;
import seedu.address.model.sort.SortOrder;

public class SortMemberCommandParserTest {
    private SortMemberCommandParser parser = new SortMemberCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        SortOrder expectedSortOrder = new SortOrder(VALID_SORT_ORDER_NAME);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SORT_ORDER_DESC_NAME,
                new SortMemberCommand(expectedSortOrder));

        // multiple sort orders - last sort order accepted
        assertParseSuccess(parser, SORT_ORDER_DESC_TAG + SORT_ORDER_DESC_NAME,
                new SortMemberCommand(expectedSortOrder));

        // invalid sort order followed by valid sort order
        assertParseSuccess(parser, INVALID_SORT_ORDER_DESC + SORT_ORDER_DESC_NAME,
                new SortMemberCommand(expectedSortOrder));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortMemberCommand.MESSAGE_USAGE);
        // missing sort order prefix
        assertParseFailure(parser, VALID_SORT_ORDER_NAME, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid sort order
        assertParseFailure(parser, INVALID_SORT_ORDER_DESC, SortOrder.MESSAGE_CONSTRAINTS);

        // valid sort order followed by invalid sort order
        assertParseFailure(parser, SORT_ORDER_DESC_NAME + INVALID_SORT_ORDER_DESC, SortOrder.MESSAGE_CONSTRAINTS);
    }
}
