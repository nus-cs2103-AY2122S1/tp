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
        // leading and trailing whitespaces will be trimmed
        SortCommand expectedSortCommand = new SortCommand(new NameComparator());
        assertParseSuccess(parser, " n/   ", expectedSortCommand);

        // leading and trailing whitespaces will be trimmed
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

        // correct first comparator followed by wrong comparator
        assertParseFailure(parser, " n/   ab/ ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // wrong first comparator followed by correct comparator
        assertParseFailure(parser, " ab/  t/ ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
