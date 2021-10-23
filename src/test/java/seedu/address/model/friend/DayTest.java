package seedu.address.model.friend;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;

class DayTest {

    @Test
    void setTime() throws InvalidDayTimeException {
        Day day = new Day(DayOfWeek.of(1));

        // Valid timeslots
        day.setTime("0200", "1200", true);
        day.setTime("0800", "2300", false);
        // Edge case of endTime being 0000
        day.setTime("1000", "0000", true);
        day.setTime("0000", "0000", false);

        //Invalid timeslots
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("01", "02", true));
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("avb", "200!", true));
        // End time before start time
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("1200", "1000", true));
        // Same time
        assertThrows(InvalidDayTimeException.class, () -> day.setTime("1200", "1200", true));
    }

    @Test
    void getGroupedTimeSlots() throws InvalidDayTimeException {
        Day day = new Day(DayOfWeek.of(1));
        day.setTime("0300", "1800", true);
        day.setTime("0900", "0000", true);
        ArrayList<String[]> expected = new ArrayList<>();
        // Timeslots combined to 0300 - 0000
        expected.add(new String[]{"0300", "0000"});
        assertArrayEquals(expected.toArray(), day.getGroupedTimeSlots().toArray());

        day.setTime("1000", "1200", false);
        expected = new ArrayList<>();
        // Timeslots split into two slots 0300 - 1000 and 1200 - 0000
        expected.add(new String[]{"0300", "1000"});
        expected.add(new String[]{"1200", "0000"});
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
        mondayTwo.setTime("0300", "1800", true);
        assertFalse(monday.equals(mondayTwo));
    }

}
