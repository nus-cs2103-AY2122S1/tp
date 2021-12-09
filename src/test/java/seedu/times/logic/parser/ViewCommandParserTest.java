package seedu.times.logic.parser;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.times.logic.commands.ViewCommand;
import seedu.times.ui.TabName;

class ViewCommandParserTest {

    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyArg_throwParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_tooManyArgs_throwParseException() {
        assertParseFailure(parser, "timetable two",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "classes cat",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "students seat",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "students timetable classes",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwParseException() {
        assertParseFailure(parser, "timetables",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.INVALID_TAB));

        assertParseFailure(parser, "class",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.INVALID_TAB));

        assertParseFailure(parser, "student",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.INVALID_TAB));
    }

    @Test
    public void parse_validArgs_returnsViewCommand() {
        assertParseSuccess(parser, "timetable",
                new ViewCommand(TabName.TIMETABLE));

        assertParseSuccess(parser, "classes",
               new ViewCommand(TabName.CLASSES));

        assertParseSuccess(parser, "students",
                new ViewCommand(TabName.STUDENTS));
    }
}
