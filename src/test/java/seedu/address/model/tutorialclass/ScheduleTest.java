package seedu.address.model.tutorialclass;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ScheduleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule(null));
    }

    @Test
    public void constructor_invalidSchedule_throwsIllegalArgumentException() {
        String invalidSchedule = "";
        assertThrows(IllegalArgumentException.class, () -> new Schedule(invalidSchedule));
    }

    @Test
    public void isValidSchedule() {
        // null Schedule
        assertThrows(NullPointerException.class, () -> Schedule.isValidSchedule(null));

        // invalid schedules
        assertFalse(Schedule.isValidSchedule(""));
        assertFalse(Schedule.isValidSchedule("  "));
        assertFalse(Schedule.isValidSchedule("Tues 10:00am to 12:00pm"));
        assertFalse(Schedule.isValidSchedule("Tues 10:00am to 12:00pm,"));

        // valid schedule
        assertTrue(Schedule.isValidSchedule("Tues 10:00am to 12:00pm, Fri 10:00am to 12:00pm"));
        assertTrue(Schedule.isValidSchedule("Mon 12:00pm to 2:00pm, Thurs 12:00pm to 2:00pm"));

    }
}
