package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class PeriodTest {
    private static final LocalDate TEST_DATE = LocalDate.of(1999, 5, 14);
    private static final LocalDate PAST_TEST_DATE = LocalDate.of(1999, 6, 14);
    private static final LocalDate BEFORE_TEST_DATE = LocalDate.of(1999, 4, 14);

    @Test
    public void test_contains_success() {
        //tests with local date time
        Period same = new Period(TEST_DATE);
        Period after = new Period(PAST_TEST_DATE, TEST_DATE);
        Period before = new Period(TEST_DATE, BEFORE_TEST_DATE);
        Period overLargePeriod = new Period(PAST_TEST_DATE, BEFORE_TEST_DATE);
        assertTrue(same.contains(TEST_DATE));
        assertTrue(overLargePeriod.contains(TEST_DATE));
        assertTrue(overLargePeriod.contains(PAST_TEST_DATE));
        assertTrue(overLargePeriod.contains(BEFORE_TEST_DATE));

        //tests with period
        assertTrue(overLargePeriod.contains(after));
        assertTrue(overLargePeriod.contains(before));
        assertTrue(before.contains(same));
        assertFalse(after.contains(overLargePeriod));
        assertFalse(before.contains(after));
    }

    @Test
    public void test_contains_failure() {
        Period after = new Period(PAST_TEST_DATE, TEST_DATE);
        assertFalse(after.contains(BEFORE_TEST_DATE));

        Period before = new Period(TEST_DATE, BEFORE_TEST_DATE);
        assertFalse(before.contains(PAST_TEST_DATE));



    }




    @Test
    public void equals() {
        Period after = new Period(PAST_TEST_DATE, TEST_DATE);
        Period before = new Period(TEST_DATE, BEFORE_TEST_DATE);
        Period same = new Period(TEST_DATE);
        assertTrue(after.equals(after));
        assertTrue(before.equals(before));
        assertTrue(same.equals(same));
        assertFalse(after.equals(before));
        assertFalse(after.equals(same));

    }

}

