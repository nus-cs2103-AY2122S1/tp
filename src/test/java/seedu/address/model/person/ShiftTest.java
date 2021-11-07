package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.PersonTestUtil.createPeriod;
import static seedu.address.model.person.PersonTestUtil.createRecurrencePeriod;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ShiftTest {

    private static final LocalTime DIFFERENT_SHIFT_TIME = LocalTime.of(10, 10);
    private static final LocalTime DIFFERENT_SHIFT_TIME_END = LocalTime.of(11, 11);

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
        //one week
        assertTrue(toTest.isWorking(createPeriod(1, 7)));
        //as the first shift is a monday
        assertFalse(toTest.isWorking(createPeriod(2, 2)));

        //testing the time
        assertTrue(toTest.isWorking(DIFFERENT_SHIFT_TIME, createPeriod(1, 1)));
        assertFalse(toTest.isWorking(DIFFERENT_SHIFT_TIME, createPeriod(3, 3)));


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

    @Test
    public void add_successTest() throws Exception {
        Shift toTest = firstShift.add(LocalDate.of(1, 1, 1),
                LocalDate.of(1, 1, 20));
        Shift expected = new Shift(DayOfWeek.MONDAY, Slot.MORNING, List.of(createRecurrencePeriod(1, 20)));
        assertEquals(toTest, expected);

        toTest = toTest.add(LocalDate.of(1, 1, 1), LocalDate.of(1, 1, 25));
        expected = new Shift(DayOfWeek.MONDAY, Slot.MORNING, List.of(createRecurrencePeriod(1, 25)));
        assertEquals(toTest, expected);

        //testing the interaction with time
        toTest = toTest.setTime(DIFFERENT_SHIFT_TIME, DIFFERENT_SHIFT_TIME_END, Slot.MORNING.getOrder(),
                LocalDate.of(1, 1, 1),
                LocalDate.of(1, 1, 7));
        expected = new Shift(DayOfWeek.MONDAY, Slot.MORNING,
                List.of(createRecurrencePeriod(1, 7, DIFFERENT_SHIFT_TIME, DIFFERENT_SHIFT_TIME_END),
                        createRecurrencePeriod(8, 25)));
        assertEquals(toTest, expected);

    }
}
