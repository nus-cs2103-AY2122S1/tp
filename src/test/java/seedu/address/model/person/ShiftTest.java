package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

public class ShiftTest {

    private Shift firstShift = new Shift(DayOfWeek.MONDAY, Slot.MORNING);
    private Shift secondShift = new Shift(DayOfWeek.TUESDAY, Slot.AFTERNOON);

    @Test
    public void isMorningTest() {
        assertTrue(firstShift.isInMorning());
        assertFalse(secondShift.isInMorning());
    }

    @Test
    public void isAfternoonTest() {
        assertTrue(secondShift.isInAfternoon());
        assertFalse(firstShift.isInAfternoon());
    }

    @Test
    public void isValidDayOfWeekTest() {
        assertTrue(Shift.isValidDayOfWeek("monday"));
        assertTrue(Shift.isValidDayOfWeek("Monday"));
        assertTrue(Shift.isValidDayOfWeek("MONDAY"));
        assertFalse(Shift.isValidDayOfWeek("mon"));
    }

    @Test
    public void isValidShiftTest() {
        assertTrue(Shift.isValidShift("monday-morning-10:00-12:00"));
        assertTrue(Shift.isValidShift("Monday-morning-19:00-20:00"));
        assertFalse(Shift.isValidShift("Monday-morning-9:00-10:00"));
        assertFalse(Shift.isValidShift("mon-morning"));
        assertFalse(Shift.isValidShift("monday-1"));
        assertFalse(Shift.isValidShift(""));
    }
}
