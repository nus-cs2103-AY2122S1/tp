package seedu.address.logic.parser;

import static seedu.address.logic.commands.SortCommand.MESSAGE_INVALID_COMMAND_FORMAT_PREFIX_ABSENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.comparator.NameComparator;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT_PREFIX_ABSENT,
                SortCommand.MESSAGE_USAGE));
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
        String errorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT_PREFIX_ABSENT, SortCommand.MESSAGE_USAGE);

        // more than one comparator
        assertParseFailure(parser, " n/ t/ ", errorMessage);

        // repeated comparator
        assertParseFailure(parser, " n/ n/ ", errorMessage);

        // correct first comparator followed by wrong comparator
        assertParseFailure(parser, " n/   ab/ ", errorMessage);

        // wrong first comparator followed by correct comparator
        assertParseFailure(parser, " ab/  t/ ", errorMessage);
    }
}
