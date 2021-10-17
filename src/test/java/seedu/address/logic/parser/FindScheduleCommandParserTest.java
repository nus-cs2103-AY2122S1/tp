package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindScheduleCommand;


public class FindScheduleCommandParserTest {

    private static final String COMMAND_WORD = FindScheduleCommand.COMMAND_WORD;
    private static final FindScheduleCommandParser parser = new FindScheduleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        String userInput = COMMAND_WORD;
        assertParseFailure(parser, userInput, FindScheduleCommandParser.INVALID_FIND_SCHEDULE_COMMAND);
    }

    @Test
    public void test_findSchedule_bySlot() {
        String userInput1 = " " + PREFIX_DASH_DAY_SHIFT + " saturday-0";
        FindScheduleCommand expectedOutput1 = new FindScheduleCommand(DayOfWeek.SATURDAY, 0, null);
        assertParseSuccess(parser, userInput1, expectedOutput1);

        String userInput2 = " " + PREFIX_DASH_DAY_SHIFT + " SUNDAY-1";
        FindScheduleCommand expectedOutput2 = new FindScheduleCommand(DayOfWeek.SUNDAY, 0, null);
        assertParseSuccess(parser, userInput2, expectedOutput2);
    }

    @Test
    public void test_findSchedule_byTime() {
        String userInput1 = " " + PREFIX_DASH_TIME + " saturday-12:00";
        FindScheduleCommand expectedOutput1 = new FindScheduleCommand(DayOfWeek.SATURDAY,
                FindScheduleCommand.INVALID_SLOT_NUMBER, LocalTime.NOON);
        assertParseSuccess(parser, userInput1, expectedOutput1);

        String userInput2 = " " + PREFIX_DASH_TIME + " SUNDAY-17:00";
        FindScheduleCommand expectedOutput2 = new FindScheduleCommand(DayOfWeek.SUNDAY,
                FindScheduleCommand.INVALID_SLOT_NUMBER, LocalTime.of(17, 0));
        assertParseSuccess(parser, userInput2, expectedOutput2);
    }

    @Test
    public void test_failures() {
        String userInput1 = " saturday-12:00";
        assertParseFailure(parser, userInput1, FindScheduleCommandParser.INVALID_FIND_SCHEDULE_COMMAND);

        String userInput2 = " " + PREFIX_DASH_TIME + " " + PREFIX_DASH_DAY_SHIFT + " saturday-12:00";
        assertParseFailure(parser, userInput2, FindScheduleCommandParser.INVALID_FIND_SCHEDULE_COMMAND);

        String userInput3 = " ";
        assertParseFailure(parser, userInput3, FindScheduleCommandParser.INVALID_FIND_SCHEDULE_COMMAND);

        String userInput4 = " -d monday-12:00";
        assertParseFailure(parser, userInput4, FindScheduleCommandParser.INVALID_FIND_SCHEDULE_COMMAND);

        String userInput5 = " -t WEDNESDAY-0";
        assertParseFailure(parser, userInput3, FindScheduleCommandParser.INVALID_FIND_SCHEDULE_COMMAND);
    }
}
