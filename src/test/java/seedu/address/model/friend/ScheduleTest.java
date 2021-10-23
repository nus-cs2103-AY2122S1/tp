package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;

class ScheduleTest {

    @Test
    void setScheduleDay_invalidDays_throwsInvalidDayTimeException() {
        Schedule schedule = new Schedule();
        // Invalid day numbers (Only from 1 - 7 allowed)
        assertThrows(InvalidDayTimeException.class, () ->
                schedule.setScheduleDay(0, "0000", "1200", true));
        assertThrows(InvalidDayTimeException.class, () ->
                schedule.setScheduleDay(8, "0000", "1200", true));
    }

    @Test
    void equals() throws InvalidDayTimeException {
        Schedule scheduleOne = new Schedule();
        Schedule scheduleTwo = new Schedule();

        // Same object
        assertTrue(scheduleOne.equals(scheduleOne));
        // Same schedule
        assertTrue(scheduleOne.equals(scheduleTwo));

        // Different day schedules
        scheduleOne.setScheduleDay(1, "0000", "1200", true);
        assertFalse(scheduleOne.equals(scheduleTwo));
    }

    @Test
    void getSchedule() {
        Schedule schedule = new Schedule();
        // Unmodifiable
        assertThrows(UnsupportedOperationException.class, () -> schedule.getSchedule().remove(1));
    }
}
