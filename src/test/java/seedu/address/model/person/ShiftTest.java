package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.PersonTestUtil.createPeriod;

import java.time.DayOfWeek;
import java.time.LocalDate;

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

    @Test
    public void isWorkingShiftTest() {
        Shift toTest = firstShift.add(LocalDate.of(1, 1, 1),
                LocalDate.of(1, 1, 7));
        assertTrue(toTest.isWorking(createPeriod(1, 7)));


    }

    @Test
    public void getWorkingHour_test() {
        Period toRead = createPeriod(1, 14);
        Period less = createPeriod(1, 7);
        Period more = createPeriod(1, 20);
        Shift toTest = firstShift.add(LocalDate.of(1, 1, 1),
                LocalDate.of(1, 1, 14));
        assertEquals(12, toTest.getWorkingHour(toRead));
        assertEquals(6, toTest.getWorkingHour(less));
        assertEquals(12, toTest.getWorkingHour(more));
    }

    @Test
    public void isWorkingExactWithin() {
        Period exact = createPeriod(1, 14);
        Shift toTest = firstShift.add(LocalDate.of(1, 1, 1),
                LocalDate.of(1, 1, 14));
        assertTrue(toTest.isWorkingExactWithin(exact));

        Period notExactButWithin = createPeriod(1, 7);
        assertTrue(toTest.isWorkingExactWithin(notExactButWithin));

        Period moreThanNotExact = createPeriod(1, 28);
        assertFalse(toTest.isWorkingExactWithin(moreThanNotExact));


    }


    @Test
    public void remove_successTest() {
        //EP: same period
        Shift toTest = firstShift.add(LocalDate.of(1, 1, 1),
                LocalDate.of(1, 1, 30));
        Shift result = toTest.remove(LocalDate.of(1, 1, 1),
                LocalDate.of(1, 1, 30));
        assertTrue(result.isEmpty());

        toTest = firstShift.add(LocalDate.of(2021, 1, 1),
                LocalDate.of(2022, 1, 1));
        result = toTest.remove(LocalDate.of(2021, 1, 1),
                LocalDate.of(2022, 1, 1));
        assertTrue(result.isEmpty());

    }
}
