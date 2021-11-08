package manageme.logic.commands.calendar;

import static manageme.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageme.logic.commands.calendar.PreviousMonthCommand.COMMAND_WORD;
import static manageme.logic.commands.calendar.PreviousMonthCommand.SHOWING_PREVIOUS_MONTH;

import org.junit.jupiter.api.Test;

import manageme.model.Model;
import manageme.model.ModelManager;

public class PreviousMonthCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_prevMonth_success() {
        CalendarCommandResult calendarCommandResult = new CalendarCommandResult(SHOWING_PREVIOUS_MONTH, COMMAND_WORD);
        assertCommandSuccess(new PreviousMonthCommand(), model, calendarCommandResult, expectedModel);
    }
}
