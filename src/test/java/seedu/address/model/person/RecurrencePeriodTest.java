package seedu.address.model.person;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.PersonTestUtil.createPeriod;
import static seedu.address.model.person.PersonTestUtil.createRecurrencePeriod;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.RecurrencePeriod;

public class RecurrencePeriodTest {
    private static final LocalTime START_DEFAULT = LocalTime.of(16, 10);
    private static final LocalTime END_DEFAULT = LocalTime.of(22, 10);
    private static final LocalTime START_DIFF = LocalTime.of(14, 10);
    private static final LocalTime END_DIFF = LocalTime.of(22, 30);

    @Test
    public void test_unionBy_duration() {
        RecurrencePeriod test = createRecurrencePeriod(4, 4);
        Collection<RecurrencePeriod> periods =
                List.of(createRecurrencePeriod(1, 3), createRecurrencePeriod(5, 6));
        Collection<RecurrencePeriod> result = test.unionByDuration(periods);
        Collection<RecurrencePeriod> expected = List.of(createRecurrencePeriod(1, 6));
        assertTrue(expected.containsAll(result));

    }

    @Test
    public void test_complementWithInformation() {
        //test intersect
        RecurrencePeriod testPeriod = createRecurrencePeriod(1, 6);
        Period toRemove = createPeriod(2, 8);
        Collection<RecurrencePeriod> expected = List.of(createRecurrencePeriod(1, 1));
        assertEquals(expected, testPeriod.complementWithInformation(toRemove));

        //test complete
        toRemove = createPeriod(1, 6);
        expected = List.of();
        assertEquals(expected, testPeriod.complementWithInformation(toRemove));

        //test within
        toRemove = createPeriod(3, 5);
        expected = List.of(createRecurrencePeriod(1, 2), createRecurrencePeriod(6, 6));
        assertEquals(expected, testPeriod.complementWithInformation(toRemove));

    }

    @Test
    public void test_withinSlotPeriod() {
        RecurrencePeriod period = createRecurrencePeriod(1, 10, START_DEFAULT, END_DEFAULT);
        assertTrue(period.isWithinSlotPeriod(START_DEFAULT));
        assertTrue(period.isWithinSlotPeriod(END_DEFAULT));
        assertFalse(period.isWithinSlotPeriod(START_DIFF));
        assertFalse(period.isWithinSlotPeriod(END_DIFF));

    }



    @Test
    public void equals() {
        RecurrencePeriod period1 = createRecurrencePeriod(1, 4);
        RecurrencePeriod period2 = createRecurrencePeriod(3, 10);
        assertEquals(period1, period1);
        assertEquals(period2, period2);
        assertNotEquals(period1, period2);

        //test stored information
        RecurrencePeriod period3 = createRecurrencePeriod(1, 4, START_DIFF, END_DIFF);
        RecurrencePeriod period4 = createRecurrencePeriod(1, 4, START_DEFAULT, END_DEFAULT);
        assertNotEquals(period3, period4);
        assertEquals(period3, period3);
        assertEquals(period4, period4);

    }

    @Test
    public void test_toString() {
        String test = createRecurrencePeriod(1, 3).toString();
        String expected = createPeriod(1, 3).toString();
        assertEquals(expected, test);


    }

}
