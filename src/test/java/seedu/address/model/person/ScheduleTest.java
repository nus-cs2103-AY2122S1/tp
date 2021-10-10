package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ScheduleTest {

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
        // null address
        assertThrows(NullPointerException.class, () -> Schedule.isValidSchedule(null));

        // invalid addresses
        assertFalse(Schedule.isValidSchedule("mon-1-")); // invalid string
        assertFalse(Schedule.isValidSchedule("abcde")); // invalid string

        // valid addresses
        assertTrue(Schedule.isValidSchedule("monday-morning tuesday-afternoon"));
        assertTrue(Schedule.isValidSchedule("")); //empty schedule
    }
}
