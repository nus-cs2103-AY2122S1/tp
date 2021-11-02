package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDERING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortOrdersCommand;
import seedu.address.model.sort.SortDescriptor;
import seedu.address.model.sort.SortField;
import seedu.address.model.sort.SortOrdering;

public class SortOrdersCommandParserTest {
    private SortOrdersCommandParser parser = new SortOrdersCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortOrdersCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        SortDescriptor descriptor =
                new SortDescriptor(new SortField("amount"), new SortOrdering("desc"));
        SortOrdersCommand expectedSortOrdersCommand = new SortOrdersCommand(descriptor);

        assertParseSuccess(parser, " " + PREFIX_SORT_FIELD + "amount " + PREFIX_SORT_ORDERING + "desc",
            expectedSortOrdersCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + PREFIX_SORT_FIELD + "amount "
                        + "\n \t " + PREFIX_SORT_ORDERING + "desc " + "\t", expectedSortOrdersCommand);
    }
}
