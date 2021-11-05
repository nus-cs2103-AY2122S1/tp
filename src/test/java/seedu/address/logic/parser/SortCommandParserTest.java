package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SortCommand.MESSAGE_INVALID_PREFIX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXTMEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.client.SortDirection.SORT_ASCENDING;
import static seedu.address.model.client.SortDirection.SORT_DESCENDING;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.mapper.PrefixMapper;
import seedu.address.logic.commands.SortCommand;
import seedu.address.model.client.SortByAttribute;

public class SortCommandParserTest {
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, " t/asc", String.format(MESSAGE_INVALID_PREFIX, PrefixMapper.getName(PREFIX_TAG)));
        assertParseFailure(parser, " p/dsc e/asc t/dsx", String.format(MESSAGE_INVALID_PREFIX,
                PrefixMapper.getName(PREFIX_TAG)));
    }

    @Test
    public void parse_invalidPreamble_throwsParseException() {
        assertParseFailure(parser, "ffe a/asc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSearchCommand() {
        SortByAttribute sorter = new SortByAttribute(PREFIX_LASTMET, SORT_DESCENDING);
        SortCommand expectedSortCommand = new SortCommand(sorter);

        SortByAttribute multipleSorter = new SortByAttribute(
                List.of(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NEXTMEETING),
                List.of(SORT_ASCENDING, SORT_DESCENDING, SORT_ASCENDING));
        SortCommand expectedMultipleSortCommand = new SortCommand(multipleSorter);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " l/dsc", expectedSortCommand);

        // multiple prefix
        assertParseSuccess(parser, " p/asc e/dsc m/asc", expectedMultipleSortCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n p/asc\t e/dsc\n m/asc", expectedMultipleSortCommand);
    }
}
