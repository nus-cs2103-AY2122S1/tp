package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.util.sort.SortByAppointment;
import seedu.fast.commons.util.sort.SortByName;
import seedu.fast.commons.util.sort.SortByPriority;
import seedu.fast.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(
                parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(
                parser, "nam", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        // Different Sort Commands
        SortCommand expectedSortCommandName =
                new SortCommand(new SortByName(), SortByName.KEYWORD);
        SortCommand expectedSortCommandAppointment =
                new SortCommand(new SortByAppointment(), SortByAppointment.KEYWORD);
        SortCommand expectedSortCommandPriority =
                new SortCommand(new SortByPriority(), SortByPriority.KEYWORD);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "name", expectedSortCommandName);

        assertParseSuccess(parser, "appointment", expectedSortCommandAppointment);

        assertParseSuccess(parser, "priority", expectedSortCommandPriority);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n name \n", expectedSortCommandName);

        assertParseSuccess(parser, " \t name \n", expectedSortCommandName);
    }

}
