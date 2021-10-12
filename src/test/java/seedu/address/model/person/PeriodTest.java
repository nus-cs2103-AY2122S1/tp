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
        Period same = new Period(TEST_DATE);
        assertTrue(same.contains(TEST_DATE));

        Period overLargePeriod = new Period(PAST_TEST_DATE, BEFORE_TEST_DATE);
        assertTrue(overLargePeriod.contains(TEST_DATE));
        assertTrue(overLargePeriod.contains(PAST_TEST_DATE));
        assertTrue(overLargePeriod.contains(BEFORE_TEST_DATE));
    }

    @Test
    public void test_contains_failure() {
        Period after = new Period(PAST_TEST_DATE, TEST_DATE);
        assertFalse(after.contains(BEFORE_TEST_DATE));

        Period before = new Period(TEST_DATE, BEFORE_TEST_DATE);
        assertFalse(before.contains(PAST_TEST_DATE));
    }


}

