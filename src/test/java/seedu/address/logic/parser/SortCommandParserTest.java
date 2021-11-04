package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.util.SortUtil.SORT_BY_NEXT_VISIT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.util.SortUtil;

public class SortCommandParserTest {
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parser_compulsoryFieldPresent_success() {
        SortCommand sortByLastVisitCommand = new SortCommand(SortUtil.SORT_BY_LAST_VISIT, false);
        SortCommand sortByNextVisitCommand = new SortCommand(SORT_BY_NEXT_VISIT, true);

        assertParseSuccess(parser, String.format(" %s", PREFIX_LAST_VISIT), sortByLastVisitCommand);
        assertParseSuccess(parser, String.format(" %s", PREFIX_VISIT), sortByNextVisitCommand);
    }

    @Test
    public void parser_invalidFieldPresent_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        // invalid flag
        assertParseFailure(parser, " e/", expectedMessage);

        // invalid value although correct flag
        assertParseFailure(parser, " v/random", expectedMessage);
        assertParseFailure(parser, " v/lv/", expectedMessage);

        // both flags
        assertParseFailure(parser, String.format(" %s %s", PREFIX_LAST_VISIT, PREFIX_VISIT), expectedMessage);
    }

    @Test
    public void parser_missingCompulsoryFieldPresent_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, "", expectedMessage);
    }
}
