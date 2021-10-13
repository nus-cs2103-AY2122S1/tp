package seedu.address.model.tutorialclass;

import org.junit.jupiter.api.Test;
import seedu.address.model.student.Name;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
    public void isValidName() {
        // null Schedule
        assertThrows(NullPointerException.class, () -> Schedule.isValidSchedule(null));

        // invalid schedules
        assertFalse(Schedule.isValidSchedule(""));
        assertFalse(Schedule.isValidSchedule("  "));

        // valid schedule
        assertTrue(Schedule.isValidSchedule("Tues 10am-12pm"));
        assertTrue(Schedule.isValidSchedule("Tues 10am-12pm, Fri 10am-12pm"));
        assertTrue(Schedule.isValidSchedule("Mon 12pm - 2pm, Thurs 12pm - 2pm"));

    }
}
