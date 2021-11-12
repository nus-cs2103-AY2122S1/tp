package seedu.address.model.friend;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DayOfWeek;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.time.exceptions.InvalidHourOfDayException;

/**
 * Test-unreachable code in isValidDay().
 */
class DayTest {

    @Test
    public void isTimeSlotHourFree_invalidHourOfDay_throwsCorrectException() {
        Day day = new Day(DayOfWeek.of(7));

        assertThrows(InvalidHourOfDayException.class, () -> day.isTimeSlotHourFree(-24));
        assertThrows(InvalidHourOfDayException.class, () -> day.isTimeSlotHourFree(-1));

        assertThrows(InvalidHourOfDayException.class, () -> day.isTimeSlotHourFree(25));
        assertThrows(InvalidHourOfDayException.class, () -> day.isTimeSlotHourFree(64));
    }

    @Test
    public void isTimeSlotHourFree_freeTimeSlots_returnsTrue() {
        Day day = new Day(DayOfWeek.of(7));

        try {
            day.setTime("10", "12", true);
            assertTrue(day.isTimeSlotHourFree(10));
            assertTrue(day.isTimeSlotHourFree(11));
        } catch (InvalidDayTimeException | InvalidHourOfDayException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void isTimeSlotHourFree_notFreeTimeSlots_returnsFalse() {
        Day day = new Day(DayOfWeek.of(7));

        try {
            day.setTime("10", "12", true);

            // end time is not included
            assertFalse(day.isTimeSlotHourFree(12));

            assertFalse(day.isTimeSlotHourFree(9));
            assertFalse(day.isTimeSlotHourFree(13));
        } catch (InvalidDayTimeException | InvalidHourOfDayException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void setTime_validTime() {
        Day day = new Day(DayOfWeek.of(1));

        try {
            // Valid timeslots
            day.setTime("2", "12", true);
            day.setTime("8", "23", false);

            // Edge case of endTime being 0000
            day.setTime("10", "24", true);
            day.setTime("0", "24", false);
        } catch (InvalidDayTimeException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void setTime_invalidTime_throwsCorrectException() {
        Day day = new Day(DayOfWeek.of(1));

        //Invalid timeslots
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("0100", "0200", true));
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("avb", "0200", true));

        // End time before start time
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("12", "10", true));

        // Same time
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("12", "12", true));
    }

    @Test
    public void getGroupedTimeSlots() {
        Day day = new Day(DayOfWeek.of(1));

        try {
            day.setTime("3", "18", true);
            day.setTime("9", "24", true);
            ArrayList<String[]> expected = new ArrayList<>();
            // Timeslots combined to 0300 - 2400
            expected.add(new String[]{"0300", "2400"});
            assertArrayEquals(expected.toArray(), day.getGroupedTimeSlots().toArray());

            day.setTime("10", "12", false);
            expected = new ArrayList<>();
            // Timeslots split into two slots 0300 - 1000 and 1200 - 0000
            expected.add(new String[]{"0300", "1000"});
            expected.add(new String[]{"1200", "2400"});
            assertArrayEquals(expected.toArray(), day.getGroupedTimeSlots().toArray());
        } catch (InvalidDayTimeException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void equals() {
        Day monday = new Day(DayOfWeek.of(1));
        Day mondayTwo = new Day(DayOfWeek.of(1));
        Day tuesday = new Day(DayOfWeek.of(2));

        try {
            // same object
            assertTrue(monday.equals(monday));

            // null
            assertFalse(monday.equals(null));

            // different type
            assertFalse(monday.equals("String"));

            // different object with same identity values
            assertTrue(monday.equals(mondayTwo));

            // Different day
            assertFalse(monday.equals(tuesday));
            // Same day but different free timeslots
            mondayTwo.setTime("3", "18", true);
            assertFalse(monday.equals(mondayTwo));
        } catch (InvalidDayTimeException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void toString_correctString() {
        Day day = new Day(DayOfWeek.of(3));

        try {
            day.setTime("3", "5", true);
            day.setTime("9", "13", true);
        } catch (InvalidDayTimeException ex) {
            System.out.println(ex.getMessage());
            fail();
        }

        String expectedString = "Day{timeSlots=[false, false, false, true, true, false, false, false, false, true, "
                + "true, true, true, false, false, false, false, false, false, false, false, false, false, false], "
                + "dayOfWeek=WEDNESDAY}";

        assertEquals(expectedString, day.toString());
    }
}
