package manageme.logic.commands.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CalendarCommandResultTest {
    @Test
    public void getFeedbackToSystem_validCalendarCommandResult_success() {
        CalendarCommandResult calendarCommandResult = new CalendarCommandResult("feedback", "feedbackToSystem");
        assertEquals("feedbackToSystem", calendarCommandResult.getFeedbackToSystem());
    }

    @Test
    public void isCalendarCommand_validCalendarCommandResult_success() {
        CalendarCommandResult calendarCommandResult = new CalendarCommandResult("feedback", "feedbackToSystem");
        assertEquals(true, calendarCommandResult.isCalendarCommand());
    }

    @Test
    public void equals() {
        CalendarCommandResult calendarCommandResult = new CalendarCommandResult("feedback", "feedbackToSystem");

        // same values -> returns true
        assertTrue(calendarCommandResult.equals(new CalendarCommandResult("feedback", "feedbackToSystem")));

        // same object -> returns true
        assertTrue(calendarCommandResult.equals(calendarCommandResult));

        // null -> returns false
        assertFalse(calendarCommandResult.equals(null));

        // different types -> returns false
        assertFalse(calendarCommandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(calendarCommandResult.equals(new CalendarCommandResult("different", "feedbackToSystem")));

        // different feedbackToSystem value -> returns false
        assertFalse(calendarCommandResult.equals(new CalendarCommandResult("feedback", "different")));
    }

}
