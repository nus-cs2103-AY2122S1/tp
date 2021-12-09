package seedu.times.logic.parser;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.times.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.times.logic.commands.SortCommand;
import seedu.times.logic.parser.exceptions.ParseException;



class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_tooManyArgs_throwParseException() {
        assertParseFailure(parser, "name asc desc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "timing desc asc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "name timing desc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "timing name asc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwParseException() {
        assertParseFailure(parser, "name ascending",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.INVALID_DIRECTIONOFSORT));

        assertParseFailure(parser, "timing descending",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.INVALID_DIRECTIONOFSORT));

        assertParseFailure(parser, "classes desc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.INVALID_SORTBY));

        assertParseFailure(parser, "students asc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.INVALID_SORTBY));

    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        assertParseSuccess(parser, "name asc",
                new SortCommand("name", "asc"));

        assertParseSuccess(parser, "name desc",
                new SortCommand("name", "desc"));

        assertParseSuccess(parser, "timing asc",
                new SortCommand("timing", "asc"));

        assertParseSuccess(parser, "timing desc",
                new SortCommand("timing", "desc"));
    }

    @Test
    void checkSortBy_invalidInput_throwsParseException() {
        assertThrows(NullPointerException.class, () -> parser.checkSortBy(null));
        assertThrows(ParseException.class, () -> parser.checkSortBy("null")); // invalid keyword
        assertThrows(ParseException.class, () -> parser.checkSortBy("names")); // invalid keyword
        assertThrows(ParseException.class, () -> parser.checkSortBy("timings")); // invalid keyword
    }

    @Test
    void checkDirectionOfSort_invalidInput_throwsParseException() {
        assertThrows(NullPointerException.class, () -> parser.checkDirectionOfSort(null));
        assertThrows(ParseException.class, () -> parser.checkDirectionOfSort("null")); // invalid keyword
        assertThrows(ParseException.class, () -> parser.checkDirectionOfSort("ascending")); // invalid keyword
        assertThrows(ParseException.class, () -> parser.checkDirectionOfSort("descending")); // invalid keyword
    }
}
