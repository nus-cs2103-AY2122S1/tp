package nurseybook.logic.parser;

import static nurseybook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nurseybook.commons.core.Messages.MESSAGE_INVALID_PASSED_DATE;
import static nurseybook.commons.core.Messages.MESSAGE_VIEWSCHEDULE_DAYS_SUPPORTED;
import static nurseybook.logic.commands.ViewScheduleCommand.MESSAGE_USAGE;
import static nurseybook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nurseybook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static nurseybook.model.task.DateTime.MESSAGE_DATE_CONSTRAINTS;
import static nurseybook.model.task.UniqueTaskList.MAX_DAYS_SCHEDULE_AHEAD;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import nurseybook.logic.commands.ViewScheduleCommand;
import nurseybook.model.task.DateTimeContainsDatePredicate;

public class ViewScheduleCommandParserTest {

    private ViewScheduleCommandParser parser = new ViewScheduleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewScheduleCommand() {
        // no leading and trailing whitespaces
        LocalDate keyDate = LocalDate.now();
        String keyDateString = keyDate.toString();
        ViewScheduleCommand expectedViewScheduleCommand =
                new ViewScheduleCommand(new DateTimeContainsDatePredicate(keyDate), keyDate);
        assertParseSuccess(parser, keyDateString, expectedViewScheduleCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_DATE_CONSTRAINTS);
    }


    @Test
    public void parse_outOfBoundsKeyDate_throwsParseException() {
        LocalDate todayDate = LocalDate.now();
        LocalDate outOfBoundsDate = todayDate.plusDays(MAX_DAYS_SCHEDULE_AHEAD + 1);
        String outOfBoundsDateString = outOfBoundsDate.toString();
        ViewScheduleCommand expectedViewScheduleCommand =
                new ViewScheduleCommand(new DateTimeContainsDatePredicate(outOfBoundsDate), outOfBoundsDate);
        assertParseFailure(parser, outOfBoundsDateString, MESSAGE_VIEWSCHEDULE_DAYS_SUPPORTED);
    }

    @Test
    public void parse_invalidDateBeforeCurrentDate_throwsParseException() {
        LocalDate todayDate = LocalDate.now();
        LocalDate expiredDate = todayDate.minusDays(1);
        String expiredDateString = expiredDate.toString();
        assertParseFailure(parser, expiredDateString, MESSAGE_INVALID_PASSED_DATE);
    }
}
