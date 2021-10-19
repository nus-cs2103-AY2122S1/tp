package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.viewShiftCommand;


public class viewShiftCommandParserTest {

    private static final String COMMAND_WORD = seedu.address.logic.commands.viewShiftCommand.COMMAND_WORD;
    private static final viewShiftCommandParser parser = new viewShiftCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        String userInput = COMMAND_WORD;
        assertParseFailure(parser, userInput, viewShiftCommandParser.INVALID_FIND_SCHEDULE_COMMAND);
    }

    @Test
    public void test_viewShift_bySlot() {
        String userInput1 = " " + PREFIX_DASH_DAY_SHIFT + " saturday-0";
        viewShiftCommand expectedOutput1 = new viewShiftCommand(DayOfWeek.SATURDAY, 0, null);
        assertParseSuccess(parser, userInput1, expectedOutput1);

        String userInput2 = " " + PREFIX_DASH_DAY_SHIFT + " SUNDAY-1";
        viewShiftCommand expectedOutput2 = new viewShiftCommand(DayOfWeek.SUNDAY, 0, null);
        assertParseSuccess(parser, userInput2, expectedOutput2);
    }

    @Test
    public void test_viewShift_byTime() {
        String userInput1 = " " + PREFIX_DASH_TIME + " saturday-12:00";
        viewShiftCommand expectedOutput1 = new viewShiftCommand(DayOfWeek.SATURDAY,
                seedu.address.logic.commands.viewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.NOON);
        assertParseSuccess(parser, userInput1, expectedOutput1);

        String userInput2 = " " + PREFIX_DASH_TIME + " SUNDAY-17:00";
        viewShiftCommand expectedOutput2 = new viewShiftCommand(DayOfWeek.SUNDAY,
                seedu.address.logic.commands.viewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.of(17, 0));
        assertParseSuccess(parser, userInput2, expectedOutput2);
    }

    @Test
    public void test_failures() {
        String userInput1 = " saturday-12:00";
        assertParseFailure(parser, userInput1, viewShiftCommandParser.INVALID_FIND_SCHEDULE_COMMAND);

        String userInput2 = " " + PREFIX_DASH_TIME + " " + PREFIX_DASH_DAY_SHIFT + " saturday-12:00";
        assertParseFailure(parser, userInput2, viewShiftCommandParser.INVALID_FIND_SCHEDULE_COMMAND);

        String userInput3 = " ";
        assertParseFailure(parser, userInput3, viewShiftCommandParser.INVALID_FIND_SCHEDULE_COMMAND);

        String userInput4 = " -d monday-12:00";
        assertParseFailure(parser, userInput4, viewShiftCommandParser.INVALID_FIND_SCHEDULE_COMMAND);

        String userInput5 = " -t WEDNESDAY-0";
        assertParseFailure(parser, userInput3, viewShiftCommandParser.INVALID_FIND_SCHEDULE_COMMAND);
    }
}
