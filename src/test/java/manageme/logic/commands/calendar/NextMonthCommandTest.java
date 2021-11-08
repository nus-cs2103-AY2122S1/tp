package manageme.logic.commands.calendar;

import static manageme.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageme.logic.commands.calendar.NextMonthCommand.COMMAND_WORD;
import static manageme.logic.commands.calendar.NextMonthCommand.SHOWING_NEXT_MONTH;

import org.junit.jupiter.api.Test;

import manageme.model.Model;
import manageme.model.ModelManager;

public class NextMonthCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_nextMonth_success() {
        CalendarCommandResult calendarCommandResult = new CalendarCommandResult(SHOWING_NEXT_MONTH, COMMAND_WORD);
        assertCommandSuccess(new NextMonthCommand(), model, calendarCommandResult, expectedModel);
    }
}
