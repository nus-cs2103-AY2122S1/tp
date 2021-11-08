package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNT_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_preamblePresent_throwsParseException() {
        assertParseFailure(
            parser, "apple " + PREFIX_NAME,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_invalidFlag_throwsParseException() {
        assertParseFailure(
            parser, PREFIX_TAG.toString(),
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_multipleFlags_throwsParseException() {
        assertParseFailure(
            parser, PREFIX_COUNT + " " + PREFIX_NAME,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_filledFlags_throwsParseException() {
        assertParseFailure(
            parser, PREFIX_NAME + VALID_NAME_BAGEL,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SortCommand.MESSAGE_USAGE)
        );

        assertParseFailure(
            parser, PREFIX_COUNT + VALID_COUNT_BAGEL,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SortCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_validFlag_returnsSortCommand() {
        // sortOrder == BY_NAME
        SortCommand expectedSortCommand = new SortCommand(SortCommand.SortOrder.BY_NAME);
        assertParseSuccess(parser, " " + PREFIX_NAME, expectedSortCommand);

        // sortOrder == BY_COUNT
        expectedSortCommand = new SortCommand(SortCommand.SortOrder.BY_COUNT);
        assertParseSuccess(parser, " " + PREFIX_COUNT, expectedSortCommand);
    }
}
