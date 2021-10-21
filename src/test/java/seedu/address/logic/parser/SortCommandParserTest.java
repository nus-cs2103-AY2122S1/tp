package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.comparator.NameComparator;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        // no leading and trailing spaces
        SortCommand expectedSortCommand = new SortCommand(new NameComparator());
        assertParseSuccess(parser, " n/   ", expectedSortCommand);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " \t n/ \n", expectedSortCommand);
    }

    @Test
    public void parse_invalidArgs_failure() {
        // more than one comparator
        assertParseFailure(parser, " n/ t/ ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // repeated comparator
        assertParseFailure(parser, " n/ n/ ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
