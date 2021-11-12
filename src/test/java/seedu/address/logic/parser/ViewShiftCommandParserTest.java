package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewShiftCommand;
import seedu.address.model.person.Period;

public class ViewShiftCommandParserTest {

    private static final String COMMAND_WORD = ViewShiftCommand.COMMAND_WORD;
    private static final ViewShiftCommandParser parser = new ViewShiftCommandParser();
    private static final Period CURRENT_PERIOD = new Period(LocalDate.now(), LocalDate.now().plusDays(7));

    @Test
    public void parse_emptyArg() {
        // an empty ViewShiftCommand outputs the help message with the staff currently working
        String userInput = "  ";
        ViewShiftCommand expectedOutput = new ViewShiftCommand(DayOfWeek.from(LocalDate.now()),
                ViewShiftCommand.INVALID_SLOT_NUMBER_INDICATING_EMPTY_PREFIXES,
                LocalTime.now().truncatedTo(ChronoUnit.MINUTES), CURRENT_PERIOD);
        assertParseSuccess(parser, userInput, expectedOutput);
    }

    @Test
    public void test_viewShift_bySlot() {
        String userInput1 = " " + PREFIX_DASH_DAY_SHIFT + " saturday-0";
        ViewShiftCommand expectedOutput1 = new ViewShiftCommand(DayOfWeek.SATURDAY, 0, null, CURRENT_PERIOD);
        assertParseSuccess(parser, userInput1, expectedOutput1);

        String userInput2 = " " + PREFIX_DASH_DAY_SHIFT + " SUNDAY-1";
        ViewShiftCommand expectedOutput2 = new ViewShiftCommand(DayOfWeek.SUNDAY, 0, null, CURRENT_PERIOD);
        assertParseSuccess(parser, userInput2, expectedOutput2);
    }

    @Test
    public void test_viewShift_byTime() {
        String userInput1 = " " + PREFIX_DASH_TIME + " saturday-12:00";
        ViewShiftCommand expectedOutput1 = new ViewShiftCommand(DayOfWeek.SATURDAY,
                ViewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.NOON, CURRENT_PERIOD);
        assertParseSuccess(parser, userInput1, expectedOutput1);

        String userInput2 = " " + PREFIX_DASH_TIME + " SUNDAY-17:00";
        ViewShiftCommand expectedOutput2 = new ViewShiftCommand(DayOfWeek.SUNDAY,
                ViewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.of(17, 0), CURRENT_PERIOD);
        assertParseSuccess(parser, userInput2, expectedOutput2);
    }

    @Test
    public void test_failures() {
        String userInput1 = " saturday-12:00";
        assertParseFailure(parser, userInput1, ViewShiftCommandParser.INVALID_VIEW_SHIFT_COMMAND);

        String userInput2 = " " + PREFIX_DASH_TIME + " " + PREFIX_DASH_DAY_SHIFT + " saturday-12:00";
        assertParseFailure(parser, userInput2, ViewShiftCommandParser.INVALID_VIEW_SHIFT_COMMAND);

        String userInput4 = " -d monday-12:00";
        assertParseFailure(parser, userInput4, ViewShiftCommandParser.INVALID_VIEW_SHIFT_COMMAND);

        String userInput5 = " -t WEDNESDAY-0";
        assertParseFailure(parser, userInput5, ViewShiftCommandParser.INVALID_VIEW_SHIFT_COMMAND);
    }
}
