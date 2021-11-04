package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.fast.logic.commands.SortCommand;
import seedu.fast.logic.parser.sort.SortByAppointment;
import seedu.fast.logic.parser.sort.SortByName;
import seedu.fast.logic.parser.sort.SortByPriority;

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
        String nameKeyword = "name";
        String appointmentKeyword = "appointment";
        String priorityKeyword = "priority";
        // Different Sort Commands
        SortCommand expectedSortCommandName =
                new SortCommand(new SortByName(), SortByName.KEYWORD);
        SortCommand expectedSortCommandAppointment =
                new SortCommand(new SortByAppointment(), SortByAppointment.KEYWORD);
        SortCommand expectedSortCommandPriority =
                new SortCommand(new SortByPriority(), SortByPriority.KEYWORD);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, nameKeyword, expectedSortCommandName);

        assertParseSuccess(parser, appointmentKeyword, expectedSortCommandAppointment);

        assertParseSuccess(parser, priorityKeyword, expectedSortCommandPriority);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n name \n", expectedSortCommandName);

        assertParseSuccess(parser, " \t name \n", expectedSortCommandName);
    }

}
