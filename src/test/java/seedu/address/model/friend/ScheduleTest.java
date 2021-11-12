package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.time.exceptions.InvalidHourOfDayException;

/**
 * Test-unreachable code present in Schedule(Schedule schedule), isValidSchedule() and toString();
 */
class ScheduleTest {

    @Test
    void setScheduleDay_invalidDays_throwsInvalidDayTimeException() {
        Schedule schedule = new Schedule();
        // Invalid day numbers (Only from 1 - 7 allowed)
        assertThrows(InvalidDayTimeException.class, () ->
                schedule.setScheduleDay(0, "0", "12", true));
        assertThrows(InvalidDayTimeException.class, () ->
                schedule.setScheduleDay(8, "0", "12", true));
    }

    @Test
    void isTimeSlotAvailable_validTimeSlots_returnsAccordingly() {
        Schedule schedule = new Schedule();

        try {
            schedule.setScheduleDay(1, "10", "12", true);

            assertTrue(schedule.isTimeSlotAvailable(10, 1));
            assertTrue(schedule.isTimeSlotAvailable(11, 1));

            assertFalse(schedule.isTimeSlotAvailable(5, 1));
            assertFalse(schedule.isTimeSlotAvailable(10, 2));
        } catch (InvalidDayTimeException | InvalidHourOfDayException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    void isTimeSlotAvailable_invalidTimeSlots_throwsCorrectException() {
        Schedule schedule = new Schedule();

        try {
            schedule.setScheduleDay(1, "10", "12", true);
        } catch (InvalidDayTimeException ex) {
            System.out.println(ex.getMessage());
            fail();
        }

        assertThrows(InvalidDayTimeException.class, () -> schedule.isTimeSlotAvailable(10, -24));
        assertThrows(InvalidDayTimeException.class, () -> schedule.isTimeSlotAvailable(10, 24));

        assertThrows(InvalidHourOfDayException.class, () -> schedule.isTimeSlotAvailable(-24, 1));
        assertThrows(InvalidHourOfDayException.class, () -> schedule.isTimeSlotAvailable(2424, 2));
    }

    @Test
    public void getSchedule_returnsCorrectType() {
        Schedule schedule = new Schedule();
        assertEquals(7, schedule.getSchedule().size());
    }

    @Test
    public void equals() throws InvalidDayTimeException {
        Schedule scheduleOne = new Schedule();
        Schedule scheduleTwo = new Schedule();

        // Same object
        assertTrue(scheduleOne.equals(scheduleOne));

        // null
        assertNotEquals(scheduleOne, null);

        // different types
        assertNotEquals(scheduleOne, "String");

        // Same schedule
        assertEquals(scheduleOne, scheduleTwo);

        // same edited schedule
        scheduleTwo.setScheduleDay(1, "10", "12", true);
        scheduleOne.setScheduleDay(1, "10", "12", true);
        assertEquals(scheduleOne, scheduleTwo);

        // Different day schedules
        scheduleOne.setScheduleDay(1, "0", "12", true);
        assertNotEquals(scheduleOne, scheduleTwo);
    }

}
