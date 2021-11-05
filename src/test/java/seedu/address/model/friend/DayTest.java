package seedu.address.model.friend;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.time.exceptions.InvalidHourOfDayException;

class DayTest {

    @Test
    void setTime() throws InvalidDayTimeException {
        Day day = new Day(DayOfWeek.of(1));

        // Valid timeslots
        day.setTime("2", "12", true);
        day.setTime("8", "23", false);
        // Edge case of endTime being 0000
        day.setTime("10", "24", true);
        day.setTime("0", "24", false);

        //Invalid timeslots
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("0100", "0200", true));
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("avb", "0200", true));
        // End time before start time
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("12", "10", true));
        // Same time
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("12", "12", true));
    }

    @Test
    void isTimeSlotHourFree_freeTimeSlots_returnsTrue() throws InvalidDayTimeException, InvalidHourOfDayException {
        Day day = new Day(DayOfWeek.of(7));

        day.setTime("10", "12", true);
        assertTrue(day.isTimeSlotHourFree(10));
        assertTrue(day.isTimeSlotHourFree(11));

        // end time is not included
        assertFalse(day.isTimeSlotHourFree(12));

        assertFalse(day.isTimeSlotHourFree(9));
        assertFalse(day.isTimeSlotHourFree(13));
    }

    @Test
    void getGroupedTimeSlots() throws InvalidDayTimeException {
        Day day = new Day(DayOfWeek.of(1));
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
    }

    @Test
    void testEquals() throws InvalidDayTimeException {
        Day monday = new Day(DayOfWeek.of(1));
        Day mondayTwo = new Day(DayOfWeek.of(1));
        Day tuesday = new Day(DayOfWeek.of(2));
        // Same day
        assertTrue(monday.equals(mondayTwo));
        assertTrue(monday.equals(monday));

        // Different day
        assertFalse(monday.equals(tuesday));
        // Same day but different free timeslots
        mondayTwo.setTime("3", "18", true);
        assertFalse(monday.equals(mondayTwo));
    }

}
