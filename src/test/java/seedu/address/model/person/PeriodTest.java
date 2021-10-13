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
        Period singleTestPeriod = new Period(TEST_DATE);
        Period testPeriodAfterSingle = new Period(PAST_TEST_DATE, TEST_DATE);
        Period testPeriodBeforeSingle = new Period(TEST_DATE, BEFORE_TEST_DATE);
        Period overLargePeriod = new Period(PAST_TEST_DATE, BEFORE_TEST_DATE);
        assertTrue(singleTestPeriod.contains(TEST_DATE));
        assertTrue(overLargePeriod.contains(TEST_DATE));
        assertTrue(overLargePeriod.contains(PAST_TEST_DATE));
        assertTrue(overLargePeriod.contains(BEFORE_TEST_DATE));

        //tests with period
        assertTrue(overLargePeriod.contains(testPeriodAfterSingle));
        assertTrue(overLargePeriod.contains(testPeriodBeforeSingle));
        assertTrue(testPeriodBeforeSingle.contains(singleTestPeriod));
        assertFalse(testPeriodAfterSingle.contains(overLargePeriod));
        assertFalse(testPeriodBeforeSingle.contains(testPeriodAfterSingle));
    }

    @Test
    public void test_contains_failure() {
        Period testPeriodAfterMid = new Period(PAST_TEST_DATE, TEST_DATE);
        assertFalse(testPeriodAfterMid.contains(BEFORE_TEST_DATE));

        Period testPeriodBeforeMid = new Period(TEST_DATE, BEFORE_TEST_DATE);
        assertFalse(testPeriodBeforeMid.contains(PAST_TEST_DATE));



    }




    @Test
    public void equals() {
        Period testPeriodAfterMid = new Period(PAST_TEST_DATE, TEST_DATE);
        Period testPeriodBeforeMid = new Period(TEST_DATE, BEFORE_TEST_DATE);
        Period testPeriodAtMid = new Period(TEST_DATE);
        Period alternateMid = new Period(TEST_DATE, TEST_DATE);
        assertTrue(testPeriodAfterMid.equals(testPeriodAfterMid));
        assertTrue(testPeriodBeforeMid.equals(testPeriodBeforeMid));
        assertTrue(testPeriodAtMid.equals(testPeriodAtMid));
        assertTrue(testPeriodAtMid.equals(alternateMid));
        assertFalse(testPeriodAfterMid.equals(testPeriodBeforeMid));
        assertFalse(testPeriodAfterMid.equals(testPeriodAtMid));
    }

}

